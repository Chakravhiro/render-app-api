package com.expenseManager.expensemanager.Services;

import com.expenseManager.expensemanager.Enitity.Expense;
import com.expenseManager.expensemanager.Enitity.User;
import com.expenseManager.expensemanager.Repository.ExpenseRepository;
import com.expenseManager.expensemanager.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {
    @Autowired
    public ExpenseRepository expenseRepository;

    @Autowired
    public UserRepository userRepository;

    public List<Expense> GetAllData(){
        return expenseRepository.findAll();
    }

    public List<Expense> GetDataByName(String name){
        return expenseRepository.findByPaidBy(name);
    }

    public List<Expense> GetDataByCategories(String name){
        return expenseRepository.findByExpenseType(name);
    }

    public List<Expense> GetDataByDate(Date Fromdate, Date toDate){
        return expenseRepository.findByDateBetween(Fromdate,toDate);
    }

    @Transactional
    public void setData(Expense expense) {
        expenseRepository.save(expense);
        userRepository.updateExpenseAmount(expense.getPaidBy(), expense.getAmount());
        float share = expense.getAmount() / expense.getSharedBy().size();

        List<User> userList = userRepository.findAll();
        userList.stream().forEach((x) -> {
            expense.getSharedBy().stream().filter(y -> y.equals(x.getUsername())).forEach((y) -> {
                userRepository.updateShareAmountwhenadd(y, share, 0, 0);
            });

        });
    }

    @Transactional
    public void DeleteData(UUID uuid){
        Expense expense = expenseRepository.findById(uuid).get();
        userRepository.updateExpenseRemoveAmount(expense.getPaidBy(),expense.getAmount());
        float share = expense.getAmount()/expense.getSharedBy().size();
        List<User> userList = userRepository.findAll();
        userList.stream().forEach((x) -> {
            expense.getSharedBy().stream().filter(y -> y.equals(x.getUsername())).forEach((y) -> {
                userRepository.updateShareAmountwhenRemove(y, share, 0, 0);
            });

            expenseRepository.deleteByExpenseId(uuid);
        });

    }



}

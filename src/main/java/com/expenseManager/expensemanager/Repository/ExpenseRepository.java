package com.expenseManager.expensemanager.Repository;

import com.expenseManager.expensemanager.Enitity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense,UUID> {


    @Query("select e from Expense e where e.PaidBy = ?1")
    List<Expense> findByPaidBy(String PaidBy);

    @Query("select e from Expense e where e.ExpenseType = ?1")
    List<Expense> findByExpenseType(String ExpenseType);

    @Query("select e from Expense e where e.date between ?1 and ?2")
    List<Expense> findByDateBetween(Date dateStart, Date dateEnd);

    @Transactional
    @Modifying
    @Query("delete from Expense e where e.ExpenseId = ?1")
    int deleteByExpenseId(UUID ExpenseId);


}

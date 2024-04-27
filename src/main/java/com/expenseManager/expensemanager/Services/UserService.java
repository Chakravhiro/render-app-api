package com.expenseManager.expensemanager.Services;

import com.expenseManager.expensemanager.Enitity.User;
import com.expenseManager.expensemanager.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> getAllData() {
        UpdateData();
        return userRepository.findAll();
    }

    @Transactional
    public void UpdateData(){

        List<User> userList = userRepository.findAll();

        userList.stream().forEach((x) ->{
            if(x.getShare()!=0 && x.getAmountPaid()>x.getShare()){
                float excessamount = x.getAmountPaid()-x.getShare();
                float dueamount = 0;
                userRepository.updateUserBalance(dueamount,excessamount,x.getUsername());
            }
            else if(x.getShare()!=0 && x.getAmountPaid()<x.getShare()){
                float excessamount =0;
                float dueamount = x.getShare()-x.getAmountPaid();
                userRepository.updateUserBalance(dueamount,excessamount,x.getUsername());
            }
        });

    }

    public void setData(User user){
        userRepository.save(user);
    }

    public void deleteUser(long id){
        userRepository.deleteByUserID(id);
    }

    public User getUserByName(String username){
        return userRepository.findUserByName(username).get();
    }
}

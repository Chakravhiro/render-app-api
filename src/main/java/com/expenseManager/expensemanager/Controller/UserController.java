package com.expenseManager.expensemanager.Controller;

import com.expenseManager.expensemanager.Enitity.User;
import com.expenseManager.expensemanager.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/users")
    public List<User> getUser(){
        return userService.getAllData();
    }

    @PutMapping("/users")
    public  void setUser(@RequestBody User user){
        userService.setData(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable long expenseId) {
        userService.deleteUser(expenseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Expense deleted successfully");
    }

}

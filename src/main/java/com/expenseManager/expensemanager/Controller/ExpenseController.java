package com.expenseManager.expensemanager.Controller;

import com.expenseManager.expensemanager.Enitity.Expense;
import com.expenseManager.expensemanager.Enitity.User;
import com.expenseManager.expensemanager.Services.ExpenseService;
import com.expenseManager.expensemanager.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT, RequestMethod.POST})
public class ExpenseController {

    @Autowired
    public ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getUser(){
        return expenseService.GetAllData();
    }

    @GetMapping("/expenses/byname/{name}")
    public List<Expense> getUser(@PathVariable String name){
        return expenseService.GetDataByName(name);
    }

    @GetMapping("/expenses/bycategories/{name}")
    public List<Expense> getUserByCategories(@PathVariable String name){
        return expenseService.GetDataByCategories(name);
    }

    @GetMapping("/expenses/bydate")
    public List<Expense> getUserByDate(
            @RequestParam("FromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date fromdate,
            @RequestParam(value ="ToDate",required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date todate){
        return expenseService.GetDataByDate(fromdate,todate);
    }
    @PutMapping("/expenses")
    public  void setUser(@RequestBody Expense expense){
        expenseService.setData(expense);
    }

    @DeleteMapping("/expenses/{expenseId}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable UUID expenseId) {
        expenseService.DeleteData(expenseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Expense deleted successfully");
    }
}

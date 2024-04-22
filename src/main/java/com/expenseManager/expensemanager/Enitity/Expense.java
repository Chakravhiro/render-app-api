package com.expenseManager.expensemanager.Enitity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Expense")
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ExpenseId;

    @Column
    private  String PaidBy;

    @Column
    private float Amount;

    @Column
    private List<String> SharedBy;

    @Column
    private String ExpenseName;

    @Column
    private String ExpenseType;

    @Column
    private Date date;



}

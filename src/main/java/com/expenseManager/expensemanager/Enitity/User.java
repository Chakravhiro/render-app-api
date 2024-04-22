package com.expenseManager.expensemanager.Enitity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String Username;

    @Column
    private float AmountPaid;

    @Column
    private double ExcessAmount;

    @Column
    private double DueAmount;

    @Column
    private float Share;





}

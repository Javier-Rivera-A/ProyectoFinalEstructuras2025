package co.edu.uniquindio.proyectofinal.model;

import java.util.List;

public class Account {

    private String accountID;
    private double balance;
    private AccountType accountType;
    private Customer owner;
    private List<Transaction> transactions;
    private List<Wallet> subWallets;
}

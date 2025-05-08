package co.edu.uniquindio.proyectofinal.model;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Transaction {
    private String id;
    private LocalDateTime date;
    private double amount;
    private Account fromAccount,toAccount;
    private Wallet fromWallet,toWallet;
    private Customer customer;

}

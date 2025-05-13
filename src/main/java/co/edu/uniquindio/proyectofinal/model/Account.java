package co.edu.uniquindio.proyectofinal.model;

import java.util.List;

public class Account {

    private String accountID;
    private double balance;
    private AccountType accountType;
    private Customer owner;
    private List<Transaction> transactions;
    private List<Wallet> subWallets;

    public Account(String accountID, double balance, AccountType accountType, Customer owner, List<Transaction> transactions, List<Wallet> subWallets) {
        this.accountID = accountID;
        this.balance = balance;
        this.accountType = accountType;
        this.owner = owner;
        this.transactions = transactions;
        this.subWallets = subWallets;
    }

    public Account() {
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Wallet> getSubWallets() {
        return subWallets;
    }

    public void setSubWallets(List<Wallet> subWallets) {
        this.subWallets = subWallets;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", owner=" + owner +
                ", transactions=" + transactions +
                ", subWallets=" + subWallets +
                '}';
    }

}

package co.edu.uniquindio.proyectofinal.model;

import java.util.List;

public class Customer {
    private String name,id,email,phone;
    private Rank rank;
    private int totalPoints;
    private List<Wallet> wallets;
    private List<Account> accounts;
    private List<Transaction> transactions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Customer(String name, String id, String email, String phone, Rank rank, int totalPoints, List<Wallet> wallets, List<Account> accounts, List<Transaction> transactions) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.rank = rank;
        this.totalPoints = totalPoints;
        this.wallets = wallets;
        this.accounts = accounts;
        this.transactions = transactions;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", rank=" + rank +
                ", totalPoints=" + totalPoints +
                ", wallets=" + wallets +
                ", accounts=" + accounts +
                ", transactions=" + transactions +
                '}';
    }
}

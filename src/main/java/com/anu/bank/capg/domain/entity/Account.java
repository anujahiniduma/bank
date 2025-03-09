package com.anu.bank.capg.domain.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private Long id;
    private Long customerId;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(Long id, Long customerId, double balance) {
        this.setId(id);
        this.setCustomerId(customerId);
        this.setBalance(balance);
    }

    public abstract String getAccountType();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

package com.anu.bank.capg.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private List<Account> accounts = new ArrayList<>();

    public Customer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

package com.anu.bank.capg.domain.entity;

public class CurrentAccount extends Account{

    public CurrentAccount(Long id, Long customerId, double balance) {
        super(id, customerId, balance);
    }

    @Override
    public String getAccountType() {
        return "Current Account";
    }

    public Long getId() {
        return super.getId();
    }
}

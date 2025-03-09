package com.anu.bank.capg.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountCreationRequestDTO {

    @JsonProperty("initialCredit")
    private double initialCredit;


    public double getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(double initialCredit) {
        this.initialCredit = initialCredit;
    }
}

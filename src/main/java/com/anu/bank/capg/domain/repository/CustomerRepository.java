package com.anu.bank.capg.domain.repository;

import com.anu.bank.capg.domain.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(Long id);

    void save(Customer customer);
}

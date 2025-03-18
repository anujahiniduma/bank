package com.anu.bank.capg.infrastructure;

import com.anu.bank.capg.domain.entity.Customer;
import com.anu.bank.capg.domain.repository.CustomerRepository;
import com.anu.bank.capg.infrastructure.generic.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository extends InMemoryRepository<Customer, Long> implements CustomerRepository {
}


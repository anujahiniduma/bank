package com.anu.bank.capg.web.controller;

import com.anu.bank.capg.application.service.AccountService;
import com.anu.bank.capg.domain.entity.Account;
import com.anu.bank.capg.domain.entity.Customer;
import com.anu.bank.capg.domain.repository.CustomerRepository;
import com.anu.bank.capg.web.dto.AccountCreationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
    @RequestMapping("/api/${api.version}/customers")
    public class CustomerController {

        @Autowired
        private final AccountService accountService;

        @Autowired
        private final CustomerRepository customerRepository;

        public CustomerController(AccountService accountService,CustomerRepository customerRepository) {
            this.accountService = accountService;
            this.customerRepository = customerRepository;
        }

        @PostMapping("/{customerId}/accounts")
        public ResponseEntity<Account> createAccount(
                @PathVariable Long customerId,
                @RequestBody AccountCreationRequestDTO requestDTO) {
            return ResponseEntity.ok(accountService.createAccount(customerId, requestDTO.getInitialCredit()));
        }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerDetails(@PathVariable Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    }



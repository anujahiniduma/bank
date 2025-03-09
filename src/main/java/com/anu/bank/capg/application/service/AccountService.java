package com.anu.bank.capg.application.service;

import com.anu.bank.capg.domain.entity.Account;
import com.anu.bank.capg.domain.entity.CurrentAccount;
import com.anu.bank.capg.domain.entity.Customer;
import com.anu.bank.capg.domain.entity.Transaction;
import com.anu.bank.capg.domain.repository.AccountRepository;
import com.anu.bank.capg.domain.repository.CustomerRepository;
import com.anu.bank.capg.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(Long customerId, double initialCredit) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            throw new RuntimeException("Customer not found");
        }
        Customer customer = customerOpt.get();
        Account account = new CurrentAccount(System.currentTimeMillis(), customerId, initialCredit);
        accountRepository.save(account);

        if (initialCredit > 0) {
            Transaction transaction = new Transaction();
            transaction.setId(System.currentTimeMillis());
            transaction.setAccountId(account.getId());
            transaction.setAmount(initialCredit);
            transactionRepository.save(transaction);
            account.getTransactions().add(transaction);
        }
        customer.getAccounts().add(account);
        return account;
    }
}

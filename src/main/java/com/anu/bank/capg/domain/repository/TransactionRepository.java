package com.anu.bank.capg.domain.repository;

import com.anu.bank.capg.domain.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);

    List<Transaction> findByAccountId(Long accountId);
}

package com.anu.bank.capg.infrastructure;

import com.anu.bank.capg.domain.entity.Transaction;
import com.anu.bank.capg.domain.repository.TransactionRepository;
import com.anu.bank.capg.infrastructure.generic.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryTransactionRepository extends InMemoryRepository<Transaction,Long> implements TransactionRepository {
    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return storage.values().stream().filter(transaction -> transaction.getAccountId().equals(accountId)).collect(Collectors.toList());
    }
}

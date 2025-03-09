package com.anu.bank.capg.domain.repository;

import com.anu.bank.capg.domain.entity.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(Long id);
    void save(Account account);
}

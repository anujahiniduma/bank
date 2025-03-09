package com.anu.bank.capg.infrastructure;

import com.anu.bank.capg.domain.entity.Account;
import com.anu.bank.capg.domain.repository.AccountRepository;
import com.anu.bank.capg.infrastructure.generic.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryAccountRepository extends InMemoryRepository<Account, Long> implements AccountRepository {
}

package com.anu.bank.capg;

import com.anu.bank.capg.application.service.AccountService;
import com.anu.bank.capg.domain.entity.Account;
import com.anu.bank.capg.domain.entity.CurrentAccount;
import com.anu.bank.capg.domain.entity.Customer;
import com.anu.bank.capg.domain.entity.Transaction;
import com.anu.bank.capg.domain.repository.AccountRepository;
import com.anu.bank.capg.domain.repository.CustomerRepository;
import com.anu.bank.capg.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTests {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private TransactionRepository transactionRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(1L, "John", "Doe");
    }

    @Test
    void shouldCreateAccountForExistingCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Account account = accountService.createAccount(1L, 100.0);
        assertNotNull(account);
        assertEquals(100.0, account.getBalance());
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> accountService.createAccount(1L, 100.0));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void shouldNotCreateTransactionWhenInitialCreditIsZero() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Account account = accountService.createAccount(1L, 0.0);
        assertNotNull(account);
        assertEquals(0.0, account.getBalance());
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(transactionRepository, times(0)).save(any());
    }

    @Test
    void shouldCreateTransactionWhenInitialCreditIsPositive() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Account account = accountService.createAccount(1L, 200.0);
        assertNotNull(account);
        assertEquals(200.0, account.getBalance());
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void shouldRetrieveSavedAccount() {
        Account account = new CurrentAccount(101L, 1L, 500.0);
        when(accountRepository.findById(101L)).thenReturn(Optional.of(account));

        Optional<Account> retrievedAccount = accountRepository.findById(101L);
        assertTrue(retrievedAccount.isPresent());
        assertEquals(500.0, retrievedAccount.get().getBalance());
    }

    @Test
    void shouldRetrieveTransactionsForAccount() {
        Account account = new CurrentAccount(101L, 1L, 500.0);
        Transaction transaction1 = new Transaction();
        transaction1.setId(201L);
        transaction1.setAccountId(101L);
        transaction1.setAmount(100);
        Transaction transaction2 = new Transaction();
        transaction2.setId(201L);
        transaction2.setAccountId(101L);
        transaction2.setAmount(50);

        when(transactionRepository.findByAccountId(101L)).thenReturn(List.of(transaction1, transaction2));

        List<Transaction> transactions = transactionRepository.findByAccountId(101L);
        assertEquals(2, transactions.size());
        assertEquals(100.0, transactions.get(0).getAmount());
        assertEquals(50.0, transactions.get(1).getAmount());
    }
}

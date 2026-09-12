package com.banking.app;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankingService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankingService(
            CustomerRepository customerRepository,
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Account createAccount(Long customerId) {

        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow();

        Account account = new Account(
                "ACC" + System.currentTimeMillis(),
                BigDecimal.ZERO,
                customer
        );

        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public Account deposit(Long id, BigDecimal amount) {

        Account account = getAccount(id);

        account.setBalance(
                account.getBalance().add(amount)
        );

        accountRepository.save(account);

        transactionRepository.save(
                new BankTransaction("DEPOSIT", amount, account)
        );

        return account;
    }

    public Account withdraw(Long id, BigDecimal amount) {

        Account account = getAccount(id);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(
                account.getBalance().subtract(amount)
        );

        accountRepository.save(account);

        transactionRepository.save(
                new BankTransaction("WITHDRAW", amount, account)
        );

        return account;
    }

    public List<BankTransaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
package com.banking.app;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BankingController {

    private final BankingService bankingService;

    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping("/customers")
    public Customer createCustomer(
            @RequestBody Customer customer) {

        return bankingService.createCustomer(customer);
    }

    @PostMapping("/accounts")
    public Account createAccount(
            @RequestParam Long customerId) {

        return bankingService.createAccount(customerId);
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(
            @PathVariable Long id) {

        return bankingService.getAccount(id);
    }

    @PostMapping("/accounts/{id}/deposit")
    public Account deposit(
            @PathVariable Long id,
            @RequestParam BigDecimal amount) {

        return bankingService.deposit(id, amount);
    }

    @PostMapping("/accounts/{id}/withdraw")
    public Account withdraw(
            @PathVariable Long id,
            @RequestParam BigDecimal amount) {

        return bankingService.withdraw(id, amount);
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<BankTransaction> transactions(
            @PathVariable Long id) {

        return bankingService.getTransactions(id);
    }
}
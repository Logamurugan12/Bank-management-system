package com.example.BankManagementSystem.controller;

import com.example.BankManagementSystem.entity.Account;
import com.example.BankManagementSystem.entity.Transaction;
import com.example.BankManagementSystem.repository.TransactionRepository;
import com.example.BankManagementSystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Double amount = Double.valueOf(body.get("amount").toString());
        String desc = body.containsKey("description") ? body.get("description").toString() : "Deposit";
        return ResponseEntity.ok(accountService.deposit(id, amount, desc));
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        try {
            Double amount = Double.valueOf(body.get("amount").toString());
            String desc = body.containsKey("description") ? body.get("description").toString() : "Withdrawal";
            return ResponseEntity.ok(accountService.withdraw(id, amount, desc));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getAccountTransactions(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return transactionRepository.findByAccountNumberOrderByTimestampDesc(account.getAccountNumber());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recent-transactions")
    public List<Transaction> getRecentTransactions() {
        return transactionRepository.findTop10ByOrderByTimestampDesc();
    }
}

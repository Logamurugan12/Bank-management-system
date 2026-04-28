package com.example.BankManagementSystem.service;

import com.example.BankManagementSystem.entity.Account;
import com.example.BankManagementSystem.entity.Transaction;
import com.example.BankManagementSystem.repository.AccountRepository;
import com.example.BankManagementSystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
    }

    @Transactional
    public Account createAccount(Account account) {
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(account.getBalance() != null ? account.getBalance() : 0.0);
        return accountRepository.save(account);
    }

    @Transactional
    public Account deposit(Long id, Double amount, String description) {
        Account account = getAccountById(id);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction tx = Transaction.builder()
                .accountNumber(account.getAccountNumber())
                .type("DEPOSIT")
                .amount(amount)
                .balanceAfter(account.getBalance())
                .description(description != null ? description : "Deposit")
                .build();
        transactionRepository.save(tx);

        return account;
    }

    @Transactional
    public Account withdraw(Long id, Double amount, String description) {
        Account account = getAccountById(id);
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance!");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction tx = Transaction.builder()
                .accountNumber(account.getAccountNumber())
                .type("WITHDRAWAL")
                .amount(amount)
                .balanceAfter(account.getBalance())
                .description(description != null ? description : "Withdrawal")
                .build();
        transactionRepository.save(tx);

        return account;
    }

    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    private String generateAccountNumber() {
        Random random = new Random();
        String accNum;
        do {
            accNum = "ACC" + String.format("%08d", random.nextInt(100000000));
        } while (accountRepository.existsByAccountNumber(accNum));
        return accNum;
    }
}

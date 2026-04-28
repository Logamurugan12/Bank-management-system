package com.example.BankManagementSystem.repository;

import com.example.BankManagementSystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountNumberOrderByTimestampDesc(String accountNumber);
    List<Transaction> findTop10ByOrderByTimestampDesc();
}

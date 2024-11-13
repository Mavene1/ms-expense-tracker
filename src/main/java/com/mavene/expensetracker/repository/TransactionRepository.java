package com.mavene.expensetracker.repository;

import com.mavene.expensetracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}

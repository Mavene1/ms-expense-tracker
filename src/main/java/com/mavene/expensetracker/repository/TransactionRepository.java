package com.mavene.expensetracker.repository;

import com.mavene.expensetracker.entity.Category;
import com.mavene.expensetracker.entity.Transaction;
import com.mavene.expensetracker.entity.User;
import com.mavene.expensetracker.exception.EtResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserIdAndCategoryId(User userId, Category categoryId) throws EtResourceNotFoundException;
    void deleteAllByCategoryId(Category category);
}

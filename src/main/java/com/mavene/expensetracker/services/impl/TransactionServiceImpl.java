package com.mavene.expensetracker.services.impl;

import com.mavene.expensetracker.dto.TransactionDto;
import com.mavene.expensetracker.repository.CategoryRepository;
import com.mavene.expensetracker.repository.TransactionRepository;
import com.mavene.expensetracker.repository.UserRepository;
import com.mavene.expensetracker.services.CategoryService;
import com.mavene.expensetracker.services.TransactionService;
import com.mavene.expensetracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public TransactionDto createTransaction(Integer userId, Integer categoryId, TransactionDto transactionDto) {
        return null;
    }

    @Override
    public List<TransactionDto> getAllTransactions(Integer userId, Integer categoryId) {
        return List.of();
    }

    @Override
    public TransactionDto getTransactionById(Integer userId, Integer transactionId) {
        return null;
    }

    @Override
    public TransactionDto updateTransaction(Integer userId, Integer transactionId, TransactionDto transactionDto) {
        return null;
    }

    @Override
    public void deleteTransaction(Integer userId, Integer transactionId) {

    }
}

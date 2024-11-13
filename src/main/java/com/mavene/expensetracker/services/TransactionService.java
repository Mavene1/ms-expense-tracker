package com.mavene.expensetracker.services;

import com.mavene.expensetracker.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAllTransactions(Integer userId, Integer categoryId);
    TransactionDto createTransaction(Integer userId, Integer categoryId, TransactionDto transactionDto);
    TransactionDto getTransactionById(Integer userId, Integer transactionId);
    TransactionDto updateTransaction(Integer userId, Integer transactionId, TransactionDto transactionDto);
    void deleteTransaction(Integer userId, Integer transactionId);
}

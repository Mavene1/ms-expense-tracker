package com.mavene.expensetracker.services;

import com.mavene.expensetracker.dto.TransactionDto;
import com.mavene.expensetracker.exception.EtBadRequestException;
import com.mavene.expensetracker.exception.EtResourceNotFoundException;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAllTransactions(Integer userId, Integer categoryId);
    TransactionDto createTransaction(Integer userId, Integer categoryId, TransactionDto transactionDto) throws EtBadRequestException;
    TransactionDto getTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
    TransactionDto updateTransaction(Integer userId, Integer categoryId, Integer transactionId, TransactionDto transactionDto) throws EtBadRequestException;
    void deleteTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
}

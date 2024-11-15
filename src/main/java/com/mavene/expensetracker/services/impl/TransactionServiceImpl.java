package com.mavene.expensetracker.services.impl;

import com.mavene.expensetracker.dto.TransactionDto;
import com.mavene.expensetracker.entity.Category;
import com.mavene.expensetracker.entity.Transaction;
import com.mavene.expensetracker.entity.User;
import com.mavene.expensetracker.exception.EtBadRequestException;
import com.mavene.expensetracker.exception.EtResourceNotFoundException;
import com.mavene.expensetracker.mapper.TransactionMapper;
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        Category category = categoryRepository.findById(categoryId)
                .filter(category1 -> category1.getUserId().equals(user))
                .orElseThrow(() -> new EtBadRequestException("Category not found with id: " + categoryId));
        Transaction transaction = TransactionMapper.toTransaction(transactionDto);
        transaction.setCategoryId(category);
        transaction.setUserId(user);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.toTransactionDto(savedTransaction);
    }

    @Override
    public List<TransactionDto> getAllTransactions(Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        Category category = categoryRepository.findById(categoryId)
                .filter(category1 -> category1.getUserId().equals(user))
                .orElseThrow(() -> new EtBadRequestException("Category not found with id: " + categoryId));
        List<Transaction> transactions = transactionRepository.findAllByUserIdAndCategoryId(user, category);
        return transactions.stream().map(TransactionMapper::toTransactionDto).toList();
    }

    @Override
    public TransactionDto getTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        Category category = categoryRepository.findById(categoryId)
                .filter(category1 -> category1.getUserId().equals(user))
                .orElseThrow(() -> new EtResourceNotFoundException("Category not found with id: " + categoryId));
        Transaction transaction = transactionRepository.findById(transactionId)
                .filter(transaction1 -> transaction1.getCategoryId().equals(category))
                .orElseThrow(() -> new EtResourceNotFoundException("Transaction not found with id: " + transactionId));
        return TransactionMapper.toTransactionDto(transaction);
    }

    @Override
    public TransactionDto updateTransaction(Integer userId, Integer categoryId, Integer transactionId, TransactionDto transactionDto) throws EtBadRequestException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        Category category = categoryRepository.findById(categoryId)
                .filter(category1 -> category1.getUserId().equals(user))
                .orElseThrow(() -> new EtBadRequestException("Category not found with id: " + categoryId));
        Transaction transaction = transactionRepository.findById(transactionId)
                .filter(transaction1 -> transaction1.getCategoryId().equals(category))
                .orElseThrow(() -> new EtBadRequestException("Transaction not found with id: " + transactionId));
        transaction.setAmount(transactionDto.getAmount());
        transaction.setNote(transactionDto.getNote());
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.toTransactionDto(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        Category category = categoryRepository.findById(categoryId)
                .filter(category1 -> category1.getUserId().equals(user))
                .orElseThrow(() -> new EtResourceNotFoundException("Category not found with id: " + categoryId));
        Transaction transaction = transactionRepository.findById(transactionId)
                .filter(transaction1 -> transaction1.getCategoryId().equals(category))
                .orElseThrow(() -> new EtResourceNotFoundException("Transaction not found with id: " + transactionId));
        transactionRepository.deleteById(transactionId);
    }
}

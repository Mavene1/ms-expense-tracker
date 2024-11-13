package com.mavene.expensetracker.mapper;

import com.mavene.expensetracker.dto.TransactionDto;
import com.mavene.expensetracker.entity.Category;
import com.mavene.expensetracker.entity.Transaction;
import com.mavene.expensetracker.entity.User;

public class TransactionMapper {
        // Method to convert Transaction entity to TransactionDto
    public static TransactionDto toTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getCategoryId().getCategoryId(),
                transaction.getUserId().getUserId(),
                transaction.getAmount(),
                transaction.getNote(),
                transaction.getTransactionDate()
        );
    }

    // Method to convert TransactionDto to Transaction entity (optional, based on needs)
    public static Transaction toTransaction(TransactionDto transactionDto) {
        // Create Category and User objects using the IDs from TransactionDto
        Category category = new Category();
        category.setCategoryId(transactionDto.getCategoryId());

        User user = new User();
        user.setUserId(transactionDto.getUserId());

        // Return the Transaction entity with category and user set correctly
        return new Transaction(
                transactionDto.getTransactionId(),
                category,           // Set the Category object
                user,               // Set the User object
                transactionDto.getAmount(),
                transactionDto.getNote(),
                transactionDto.getTransactionDate()
        );
    }
}

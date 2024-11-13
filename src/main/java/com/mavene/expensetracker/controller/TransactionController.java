package com.mavene.expensetracker.controller;

import com.mavene.expensetracker.dto.TransactionDto;
import com.mavene.expensetracker.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(Integer userId, Integer categoryId) {
        return new ResponseEntity<>(transactionService.getAllTransactions(userId, categoryId), HttpStatus.OK);
    }

}

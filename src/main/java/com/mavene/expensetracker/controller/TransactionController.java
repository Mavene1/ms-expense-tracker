package com.mavene.expensetracker.controller;

import com.mavene.expensetracker.dto.TransactionDto;
import com.mavene.expensetracker.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories/{categoryId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    //createTransaction
    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionDto> createTransaction(HttpServletRequest request, @PathVariable Integer categoryId, @RequestBody TransactionDto transactionDto) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (transactionDto.getTransactionDate() == null) {
            transactionDto.setTransactionDate(LocalDate.now().toString()); // Default to current date
        }
        return new ResponseEntity<>(transactionService.createTransaction(userId, categoryId, transactionDto), HttpStatus.OK);
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(HttpServletRequest request, @PathVariable Integer categoryId) {
        Integer userId = (Integer) request.getAttribute("userId");
        return new ResponseEntity<>(transactionService.getAllTransactions(userId, categoryId), HttpStatus.OK);
    }

    @GetMapping("/getTransactionById/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(HttpServletRequest request, @PathVariable Integer categoryId, @PathVariable Integer transactionId) {
        Integer userId = (Integer) request.getAttribute("userId");
        return new ResponseEntity<>(transactionService.getTransactionById(userId, categoryId, transactionId), HttpStatus.OK);
    }

    @PutMapping("/updateTransaction/{transactionId}")
    public ResponseEntity<TransactionDto> updateTransaction(HttpServletRequest request, @PathVariable Integer categoryId, @PathVariable Integer transactionId, @RequestBody TransactionDto transactionDto) {
        Integer userId = (Integer) request.getAttribute("userId");
        return new ResponseEntity<>(transactionService.updateTransaction(userId, categoryId, transactionId, transactionDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTransaction/{transactionId}")
    public ResponseEntity<String> deleteTransaction(HttpServletRequest request, @PathVariable Integer categoryId, @PathVariable Integer transactionId) {
        Integer userId = (Integer) request.getAttribute("userId");
        transactionService.deleteTransaction(userId, categoryId, transactionId);
        return new ResponseEntity<>("Transaction deleted successfully", HttpStatus.OK);
    }

}

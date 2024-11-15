package com.mavene.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Integer transactionId;
    private Integer categoryId;
    private Integer userId;
    private Double amount;
    private String note;
    private String transactionDate;
}

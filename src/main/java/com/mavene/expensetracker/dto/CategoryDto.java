package com.mavene.expensetracker.dto;

import com.mavene.expensetracker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    private Integer userId;
    private String title;
    private String description;
//    private Double totalExpense;

}

package com.mavene.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
		System.out.println("Expense Tracker Application Started...");
	}
}
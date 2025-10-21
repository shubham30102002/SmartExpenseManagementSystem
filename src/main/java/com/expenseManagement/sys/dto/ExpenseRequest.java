package com.expenseManagement.sys.dto;

import java.time.LocalDate;

public class ExpenseRequest {
	private String title;
	private String category;
	private double amount;
	private String description;
	private LocalDate expenseDate;
}

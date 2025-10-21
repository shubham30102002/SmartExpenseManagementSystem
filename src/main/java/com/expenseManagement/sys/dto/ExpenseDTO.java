package com.expenseManagement.sys.dto;

import java.time.LocalDate;

public class ExpenseDTO {

	private long id;
	private String title;
	private String category;
	private double amount;
	private LocalDate date;
	
	public ExpenseDTO(Long id, String title, String category, double amount, LocalDate date) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.amount = amount;
		this.date = date;
	}
	
	public ExpenseDTO() {
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	
}

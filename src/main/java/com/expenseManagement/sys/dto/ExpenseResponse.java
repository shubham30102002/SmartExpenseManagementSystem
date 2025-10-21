package com.expenseManagement.sys.dto;

import java.time.LocalDate;

public class ExpenseResponse {
    private Long id;
    private String title;
    private String category;
    private double amount;
    private String description;
    private String status;
    private LocalDate expenseDate;
    private String submittedBy;

}

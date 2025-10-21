package com.expenseManagement.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenseManagement.sys.dto.ExpenseDTO;
import com.expenseManagement.sys.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
@CacheConfig(cacheNames = "expenses")
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;

	 @PostMapping
	    public ResponseEntity<?> addExpense(@RequestBody ExpenseDTO expenseDTO) {
	        expenseService.addExpense(expenseDTO);
	        return ResponseEntity.ok("Expense added successfully.");
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
	        expenseService.updateExpense(id, expenseDTO);
	        return ResponseEntity.ok("Expense updated successfully.");
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
	        expenseService.deleteExpense(id);
	        return ResponseEntity.ok("Expense deleted successfully.");
	    }

	    @GetMapping
	    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
	        List<ExpenseDTO> expenses = expenseService.getAllExpenses();
	        return ResponseEntity.ok(expenses);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
	        ExpenseDTO expense = expenseService.getExpenseById(id);
	        if (expense != null) {
	            return ResponseEntity.ok(expense);
	        } else {
	            return ResponseEntity.badRequest().body("Expense not found.");
	        }
	    }
}

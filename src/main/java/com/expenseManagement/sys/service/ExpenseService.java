package com.expenseManagement.sys.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.expenseManagement.sys.dto.ExpenseDTO;
import com.expenseManagement.sys.entity.Expense;
import com.expenseManagement.sys.repo.ExpenseRepository;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;
	
	/**
	 * Add a new expense
	 * @param expenseDTO
	 */
	public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {
		Expense expense = new Expense();
		expense.setTitle(expenseDTO.getTitle());
		expense.setCategory(expenseDTO.getCategory());
		expense.setAmount(expenseDTO.getAmount());
		expense.setDate(expenseDTO.getDate());
		expenseRepository.save(expense);
		return expenseDTO;
	}
	
	
	/**
	 * Return all expenses and cache it for further call
	 * @return
	 */
	@Cacheable(value = "expenses")
	public List<ExpenseDTO> getAllExpenses(){
		return expenseRepository.findAll().stream()
				.map(e -> new ExpenseDTO(e.getId(), e.getTitle(), e.getCategory(), e.getAmount(), e.getDate()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Get single expense by id
	 * @param id
	 * @return
	 */
	@Cacheable(value = "expenses",  key = "#id")
	public ExpenseDTO getExpenseById(Long id) {
		Expense e = expenseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Expense not found"));
        return new ExpenseDTO(e.getId(), e.getTitle(), e.getCategory(), e.getAmount(), e.getDate());
	}
	
	
	/**
	 * Update expense by id
	 * @param id
	 * @param expenseDTO
	 * @return
	 */
	@CachePut(value = "expenses", key = "#id")
	public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
		Expense expense = expenseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Expense not found"));
		
		expense.setTitle(expenseDTO.getTitle());
		expense.setCategory(expenseDTO.getCategory());
		expense.setAmount(expenseDTO.getAmount());
		expense.setDate(expenseDTO.getDate());
		expenseRepository.save(expense);
		return expenseDTO;
	}
	
	/**
	 * Delete expense by id
	 * @param id
	 */
	@CacheEvict(value = "expenses", key = "#id")
	public void deleteExpense(Long id) {
		expenseRepository.deleteById(id);
	}
	
	
	
}

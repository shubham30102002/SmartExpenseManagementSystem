package com.expenseManagement.sys.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenseManagement.sys.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}

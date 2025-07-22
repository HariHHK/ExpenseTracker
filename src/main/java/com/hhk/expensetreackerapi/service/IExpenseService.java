package com.hhk.expensetreackerapi.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hhk.expensetreackerapi.entity.Expense;

public interface IExpenseService {

	Expense saveExpenseDetails(Expense expense);

	Page<Expense> getAllExpenses(Pageable page);

	Expense getExpenseById(Long id);

	void deleteExpenseById(Long id);

	Expense updateExpenseDetails(Long id, Expense expense);

	List<Expense> readByCategory(String category, Pageable page);

	List<Expense> readByName(String keyword, Pageable page);

	List<Expense> readByDate(Date startDate, Date endDate, Pageable page);

}

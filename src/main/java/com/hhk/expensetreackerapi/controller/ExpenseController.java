package com.hhk.expensetreackerapi.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hhk.expensetreackerapi.entity.Expense;
import com.hhk.expensetreackerapi.service.impl.ExpenseServiceImpl;

import jakarta.validation.Valid;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseServiceImpl expenseService;

	@PostMapping("/expenses")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
		return expenseService.saveExpenseDetails(expense);
	}

	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(Pageable page) {
//		int number = 1;
//		calculateFactorial(number);
		return expenseService.getAllExpenses(page).toList();
	}

	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable("id") Long id) {
		return expenseService.getExpenseById(id);
	}

	@DeleteMapping("/expenses")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteExpenseById(@RequestParam("id") Long id) {
		expenseService.deleteExpenseById(id);
	}

	@PutMapping("/expenses/{id}")
	public Expense updateExpenseDetails(@PathVariable("id") Long id, @RequestBody Expense expense) {
		return expenseService.updateExpenseDetails(id, expense);
	}

//	public int calculateFactorial(int number) {
//		return number * calculateFactorial(number - 1);
//	}

	@GetMapping("/expenses/category")
	public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
		return expenseService.readByCategory(category, page);

	}

	@GetMapping("/expenses/name")
	public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page) {
		return expenseService.readByName(keyword, page);

	}

	@GetMapping("/expenses/date")
	public List<Expense> getExpensesByDate(@RequestParam(required = false) Date starDate,
											@RequestParam(required = false) Date endDate, 
											Pageable page) {
		return expenseService.readByDate(starDate, endDate, page);
	}
}

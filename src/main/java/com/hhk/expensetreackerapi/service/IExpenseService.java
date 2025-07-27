package com.hhk.expensetreackerapi.service;

import com.hhk.expensetreackerapi.dto.ExpenseDTO;
import com.hhk.expensetreackerapi.entity.Expense;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface IExpenseService {

    ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO);

    List<ExpenseDTO> getAllExpenses(Pageable page);

    ExpenseDTO getExpenseById(String expenseId);

    void deleteExpenseById(String expenseId);

    ExpenseDTO updateExpenseDetails(String expenseId, ExpenseDTO expenseDTO);

    List<ExpenseDTO> readByCategory(String category, Pageable page);

    List<ExpenseDTO> readByName(String keyword, Pageable page);

    List<ExpenseDTO> readByDate(Date startDate, Date endDate, Pageable page);

}

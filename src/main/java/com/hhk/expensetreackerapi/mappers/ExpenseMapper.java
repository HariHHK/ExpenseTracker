package com.hhk.expensetreackerapi.mappers;

import com.hhk.expensetreackerapi.dto.ExpenseDTO;
import com.hhk.expensetreackerapi.entity.Expense;
import com.hhk.expensetreackerapi.io.ExpenseRequest;
import com.hhk.expensetreackerapi.io.ExpenseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    @Mapping(target = "category", source = "expenseDTO.categoryDTO")
    ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO);

    Expense mapToExpenseEntity(ExpenseDTO expenseDTO);

    @Mapping(target = "categoryDTO", source = "expense.category")
    ExpenseDTO mapToExpenseDTO(Expense expense);

    ExpenseDTO mapToExpenseDTO(ExpenseRequest request);

}

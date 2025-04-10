package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.model.Expense;
import com.example.InstaPay_Travel_Tours.model.Income;
import com.example.InstaPay_Travel_Tours.repo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public boolean deleteExpense(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Optional<Expense> existingIncomeOpt = expenseRepository.findById(id);
        if (existingIncomeOpt.isPresent()) {
            Expense existingIncome = existingIncomeOpt.get();
            existingIncome.setDescription(expenseDetails.getDescription());
            existingIncome.setAmount(expenseDetails.getAmount());
            return expenseRepository.save(existingIncome);
        }
        return null;
    }
}

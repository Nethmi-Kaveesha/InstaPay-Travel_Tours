package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.model.Expense;
import com.example.InstaPay_Travel_Tours.service.ExpenseService;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        BigDecimal totalAmount = expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("expenses",expenses);
        model.addAttribute("totalAmount",totalAmount);
        return "index.html";
    }

    @GetMapping("/addExpense")
    public String showAddExpensePage(Model model) {
        Expense expense = new Expense();
        model.addAttribute("expense",expense);
        return "add-expense";
    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute("expense") Expense expense, Model model) {
        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());  // Default date if none is provided
        }
        expenseService.saveExpense(expense);
        return "redirect:/";
    }

    @GetMapping("editExpense/{id}")
    public String showUpdateExpensePage(@PathVariable("id") long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense",expense);
        return "update-expense";
    }



    @PostMapping("/updateExpense/{id}")
    public String updateExpense(@PathVariable("id") long id, @ModelAttribute("expense") Expense expense) {
        Expense existingExpense = expenseService.getExpenseById(id);

        // Update fields
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setAmount(expense.getAmount());

        // Ensure the date is updated correctly
        if (expense.getDate() != null) {
            existingExpense.setDate(expense.getDate());
        }

        expenseService.saveExpense(existingExpense);
        return "redirect:/";
    }



    @GetMapping("/deleteExpense/{id}")
    public String deleteExpense(@PathVariable("id") long id) {
        expenseService.deleteExpenseById(id);
        return "redirect:/";
    }
}

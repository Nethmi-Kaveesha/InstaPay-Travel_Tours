package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.model.Expense;
import com.example.InstaPay_Travel_Tours.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
@CrossOrigin(origins = "http://localhost:63342")  // Update with the correct frontend URL
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Fetch all expenses
    @GetMapping("/")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // Save a new expense
    @PostMapping("/saveExpense")
    public Expense saveExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable("id") Long id) {
        try {
            boolean isDeleted = expenseService.deleteExpense(id);
            if (isDeleted) {
                return ResponseEntity.ok("Expense deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting expense: " + e.getMessage());
        }
    }

}

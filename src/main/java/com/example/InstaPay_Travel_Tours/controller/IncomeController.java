package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.model.Income;
import com.example.InstaPay_Travel_Tours.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/income")
@CrossOrigin(origins = "http://localhost:63342")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/")
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @PostMapping("/saveIncome")
    public Income saveIncome(@RequestBody Income income) {
        return incomeService.saveIncome(income);
    }

    @DeleteMapping("/deleteIncome/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable("id") Long id) {
        try {
            boolean isDeleted = incomeService.deleteIncome(id);
            if (isDeleted) {
                return ResponseEntity.ok("Income deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting income: " + e.getMessage());
        }
    }

    // Add this method for updating income
    @PutMapping("/updateIncome/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable("id") Long id, @RequestBody Income incomeDetails) {
        Income updatedIncome = incomeService.updateIncome(id, incomeDetails);

        if (updatedIncome != null) {
            return ResponseEntity.ok(updatedIncome);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

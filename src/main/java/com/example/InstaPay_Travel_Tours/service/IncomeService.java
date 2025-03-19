package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.model.Income;
import com.example.InstaPay_Travel_Tours.repo.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    // Method to get all income records
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    // Method to save an income record
    public Income saveIncome(Income income) {
        return incomeRepository.save(income);
    }

    // Method to delete an income record by its ID
    public boolean deleteIncome(Long id) {
        if (incomeRepository.existsById(id)) {
            incomeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

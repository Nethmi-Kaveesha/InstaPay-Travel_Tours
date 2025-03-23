package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.model.Income;
import com.example.InstaPay_Travel_Tours.repo.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Income saveIncome(Income income) {
        return incomeRepository.save(income);
    }

    public boolean deleteIncome(Long id) {
        if (incomeRepository.existsById(id)) {
            incomeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Add this method for updating income
    public Income updateIncome(Long id, Income incomeDetails) {
        Optional<Income> existingIncomeOpt = incomeRepository.findById(id);

        if (existingIncomeOpt.isPresent()) {
            Income existingIncome = existingIncomeOpt.get();

            // Update fields
            existingIncome.setDescription(incomeDetails.getDescription());
            existingIncome.setAmount(incomeDetails.getAmount());

            // Save updated income
            return incomeRepository.save(existingIncome);
        }
        return null;  // Return null if the income with the specified ID doesn't exist
    }
}

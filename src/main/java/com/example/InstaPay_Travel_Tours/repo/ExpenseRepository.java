package com.example.InstaPay_Travel_Tours.repo;


import com.example.InstaPay_Travel_Tours.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}

package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // This annotation marks the interface as a Spring Data repository.
public interface IncomeRepository extends JpaRepository<Income, Long> {
    // You can add custom query methods if needed, e.g., find by description, amount, etc.
}

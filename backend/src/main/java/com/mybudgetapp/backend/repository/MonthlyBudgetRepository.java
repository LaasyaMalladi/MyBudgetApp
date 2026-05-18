package com.mybudgetapp.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybudgetapp.backend.entity.MonthlyBudget;

@Repository
public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
    Optional<MonthlyBudget> findByUserIdAndMonthAndYear(Long userId, Integer month, Integer year);
}

package com.mybudgetapp.backend.service;

import org.springframework.stereotype.Service;

import com.mybudgetapp.backend.repository.MonthlyBudgetRepository;
import com.mybudgetapp.backend.entity.MonthlyBudget;
import java.util.Optional;

@Service
public class MonthlyBudgetService {
    private final MonthlyBudgetRepository monthlyBudgetRepository;

    public MonthlyBudgetService(MonthlyBudgetRepository monthlyBudgetRepository) {
        this.monthlyBudgetRepository = monthlyBudgetRepository;
    }

    public Optional<MonthlyBudget> getBudgetByMonth(Long userId, Integer month, Integer year) {
        return monthlyBudgetRepository.findByUserIdAndMonthAndYear(userId, month, year);
    }

    public MonthlyBudget createOrUpdateBudget(MonthlyBudget monthlyBudget) {
        return monthlyBudgetRepository.save(monthlyBudget);
    }
}

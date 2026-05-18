package com.mybudgetapp.backend.service;

import org.springframework.stereotype.Service;

import com.mybudgetapp.backend.repository.IncomeRepository;
import com.mybudgetapp.backend.entity.Income;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public List<Income> getIncomeByUser(Long userId) {
        return incomeRepository.findByUserId(userId);
    }

    public List<Income> getIncomeByMonth(Long user, java.time.LocalDate start, java.time.LocalDate end) {
        return incomeRepository.findByUserIdAndDateBetween(user, start, end);
    }

    public Income createIncome(Income income) {
        return incomeRepository.save(income);
    }

    public Optional<Income> getIncomeById(Long incomeId) {
        return incomeRepository.findById(incomeId);
    }

    public void deleteIncome(Long incomeId) {
        incomeRepository.deleteById(incomeId);
    }
}

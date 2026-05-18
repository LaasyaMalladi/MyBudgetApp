package com.mybudgetapp.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybudgetapp.backend.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{
    List<Income> findByUserId(Long userId);
    List<Income> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}

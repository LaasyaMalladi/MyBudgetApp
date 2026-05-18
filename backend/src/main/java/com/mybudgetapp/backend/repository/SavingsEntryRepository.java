package com.mybudgetapp.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybudgetapp.backend.entity.SavingsEntry;

@Repository
public interface SavingsEntryRepository extends JpaRepository<SavingsEntry, Long>{
    List<SavingsEntry> findByUserId(Long userId);
    List<SavingsEntry> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}

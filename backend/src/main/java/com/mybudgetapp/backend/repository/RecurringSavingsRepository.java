package com.mybudgetapp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybudgetapp.backend.entity.RecurringSavings;

@Repository
public interface RecurringSavingsRepository extends JpaRepository<RecurringSavings, Long>{
    List<RecurringSavings> findByUserId(Long userId);
    List<RecurringSavings> findByUserIdAndActive(Long userId, Boolean active);
}

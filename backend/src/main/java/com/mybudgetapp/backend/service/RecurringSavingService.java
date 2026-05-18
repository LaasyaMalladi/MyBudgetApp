package com.mybudgetapp.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybudgetapp.backend.entity.RecurringSavings;
import com.mybudgetapp.backend.repository.RecurringSavingsRepository;

@Service
public class RecurringSavingService {

    private final RecurringSavingsRepository recurringSavingsRepository;

    public RecurringSavingService(RecurringSavingsRepository recurringSavingsRepository) {
        this.recurringSavingsRepository = recurringSavingsRepository;
    }

    public List<RecurringSavings> getRecurringSavingsByUser(Long userId) {
        return recurringSavingsRepository.findByUserId(userId);
    }

    public List<RecurringSavings> getActiveRecurringSavings(Long userId) {
        return recurringSavingsRepository.findByUserIdAndActive(userId, true);
    }

    public RecurringSavings createRecurringSaving(RecurringSavings recurringSaving) {
        return recurringSavingsRepository.save(recurringSaving);
    }

    public Optional<RecurringSavings> getRecurringSavingById(Long id) {
        return recurringSavingsRepository.findById(id);
    }

    public RecurringSavings updateRecurringSaving(RecurringSavings recurringSaving) {
        return recurringSavingsRepository.save(recurringSaving);
    }

    public void deleteRecurringSaving(Long id) {
        recurringSavingsRepository.deleteById(id);
    }
}

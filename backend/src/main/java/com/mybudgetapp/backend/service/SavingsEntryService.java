package com.mybudgetapp.backend.service;

import org.springframework.stereotype.Service;
import com.mybudgetapp.backend.repository.SavingsEntryRepository;
import com.mybudgetapp.backend.entity.SavingsEntry;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SavingsEntryService {

    private final SavingsEntryRepository savingsEntryRepository;

    public SavingsEntryService(SavingsEntryRepository savingsEntryRepository) {
        this.savingsEntryRepository = savingsEntryRepository;
    }

    public List<SavingsEntry> getSavingsEntriesByUser(Long userId) {
        return savingsEntryRepository.findByUserId(userId);
    }

    public List<SavingsEntry> getSavingsEntriesByMonth(Long userId, LocalDate start, LocalDate end) {
        return savingsEntryRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    public SavingsEntry createSavings(SavingsEntry savingsEntry) {
        return savingsEntryRepository.save(savingsEntry);
    }

    public SavingsEntry updateSavings(SavingsEntry savingsEntry) {
        return savingsEntryRepository.save(savingsEntry);
    }

    public Optional<SavingsEntry> getSavingsEntryById(Long id) {
        return savingsEntryRepository.findById(id);
    }

    public void deleteSavingsEntry(Long id) {
        savingsEntryRepository.deleteById(id);
    }
}

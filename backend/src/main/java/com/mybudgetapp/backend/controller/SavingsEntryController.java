package com.mybudgetapp.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybudgetapp.backend.entity.SavingsEntry;
import com.mybudgetapp.backend.service.SavingsEntryService;

@RestController
@RequestMapping("/api/savings")
public class SavingsEntryController {

    private final SavingsEntryService savingsEntryService;

    public SavingsEntryController(SavingsEntryService savingsEntryService) {
        this.savingsEntryService = savingsEntryService;
    }

    @GetMapping("/user/{userId}")
    public List<SavingsEntry> getSavingsEntriesByUser(@PathVariable Long userId) {
        return savingsEntryService.getSavingsEntriesByUser(userId);
    }

    @GetMapping("/user/{userId}/month")
    public List<SavingsEntry> getSavingsEntriesByMonth(@PathVariable Long userId, @RequestParam LocalDate start, @RequestParam LocalDate end) {
        return savingsEntryService.getSavingsEntriesByMonth(userId, start, end);
    }

    @PostMapping
    public ResponseEntity<SavingsEntry> createSavingsEntry(@RequestBody SavingsEntry savingsEntry) {
        SavingsEntry createdSavingsEntry = savingsEntryService.createSavings(savingsEntry);
        return ResponseEntity.ok(createdSavingsEntry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavingsEntry> updateSavingsEntry(@PathVariable Long id, @RequestBody SavingsEntry savingsEntry) {
        savingsEntry.setId(id);
        return ResponseEntity.ok(savingsEntryService.updateSavings(savingsEntry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavingsEntry(@PathVariable Long id) {
        savingsEntryService.deleteSavingsEntry(id);
        return ResponseEntity.noContent().build();
    }

}

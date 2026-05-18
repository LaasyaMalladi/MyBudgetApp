package com.mybudgetapp.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybudgetapp.backend.entity.RecurringSavings;
import com.mybudgetapp.backend.service.RecurringSavingService;

@RestController
@RequestMapping("/api/recurring-savings")
public class RecurringSavingsController {

    private final RecurringSavingService recurringSavingService;

    public RecurringSavingsController(RecurringSavingService recurringSavingService) {
        this.recurringSavingService = recurringSavingService;
    }

    @GetMapping("/user/{userId}")
    public List<RecurringSavings> getRecurringSavingsByUser(@PathVariable Long userId) {
        return recurringSavingService.getRecurringSavingsByUser(userId);
    }

    @GetMapping("/user/{userId}/active")
    public List<RecurringSavings> getActiveRecurringSavings(@PathVariable Long userId) {
        return recurringSavingService.getActiveRecurringSavings(userId);
    }

    @PostMapping
    public ResponseEntity<RecurringSavings> createRecurringSaving(@RequestBody RecurringSavings recurringSaving) {
        RecurringSavings createdRecurringSaving = recurringSavingService.createRecurringSaving(recurringSaving);
        return ResponseEntity.ok(createdRecurringSaving);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecurringSavings> updateRecurringSaving(@PathVariable Long id, @RequestBody RecurringSavings recurringSaving) {
        recurringSaving.setId(id);
        return ResponseEntity.ok(recurringSavingService.updateRecurringSaving(recurringSaving));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurringSaving(@PathVariable Long id) {
        recurringSavingService.deleteRecurringSaving(id);
        return ResponseEntity.noContent().build();
    }
}

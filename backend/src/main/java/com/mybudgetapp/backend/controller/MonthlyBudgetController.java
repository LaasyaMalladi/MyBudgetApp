package com.mybudgetapp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybudgetapp.backend.entity.MonthlyBudget;
import com.mybudgetapp.backend.service.MonthlyBudgetService;

@RestController
@RequestMapping("/api/budgets")
public class MonthlyBudgetController {

    private final MonthlyBudgetService monthlyBudgetService;

    public MonthlyBudgetController(MonthlyBudgetService monthlyBudgetService) {
        this.monthlyBudgetService = monthlyBudgetService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<MonthlyBudget> getMonthlyBudgetsByUserId(@PathVariable Long userId, @RequestParam Integer month, @RequestParam Integer year) {
        return monthlyBudgetService.getBudgetByMonth(userId, month, year).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MonthlyBudget> createOrUpdateMonthlyBudget(@RequestBody MonthlyBudget monthlyBudget) {
        MonthlyBudget createdBudget = monthlyBudgetService.createOrUpdateBudget(monthlyBudget);
        return ResponseEntity.ok(createdBudget);
    }

}

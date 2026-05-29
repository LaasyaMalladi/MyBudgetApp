package com.mybudgetapp.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybudgetapp.backend.dto.DashboardResponse;
import com.mybudgetapp.backend.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("user/{userId}")
    public DashboardResponse getDashboardData(@PathVariable Long userId, @RequestParam Integer month, @RequestParam Integer year) {
        return dashboardService.getDashboardData(userId, month, year);
    }
}

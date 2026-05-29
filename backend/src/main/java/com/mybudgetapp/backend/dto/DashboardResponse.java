package com.mybudgetapp.backend.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class DashboardResponse {

    private BigDecimal expectedIncome;
    private BigDecimal totalSaved;
    private BigDecimal availableBudget;
    private BigDecimal totalSpent;
    private BigDecimal remainingBudget;
    private Integer daysLeftInMonth;
    private BigDecimal creditCardDue;
    private List<CategorySummary> topCategories;

    @Data
    public static class CategorySummary {
        private String categoryName;
        private BigDecimal totalSpent;
        private Double percentage;
    }
}

package com.mybudgetapp.backend.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mybudgetapp.backend.dto.DashboardResponse;
import com.mybudgetapp.backend.entity.Transaction;
import com.mybudgetapp.backend.repository.IncomeRepository;
import com.mybudgetapp.backend.repository.MonthlyBudgetRepository;
import com.mybudgetapp.backend.repository.RecurringSavingsRepository;
import com.mybudgetapp.backend.repository.SavingsEntryRepository;
import com.mybudgetapp.backend.repository.TransactionRepository;

@Service
public class DashboardService {

    private final TransactionRepository transactionRepository;
    private final IncomeRepository incomeRepository;
    private final RecurringSavingsRepository recurringSavingsRepository;
    private final SavingsEntryRepository savingsEntryRepository;
    private final MonthlyBudgetRepository monthlyBudgetRepository;

    public DashboardService(TransactionRepository transactionRepository, IncomeRepository incomeRepository,
                            RecurringSavingsRepository recurringSavingsRepository, SavingsEntryRepository savingsEntryRepository,
                            MonthlyBudgetRepository monthlyBudgetRepository) {
        this.transactionRepository = transactionRepository;
        this.incomeRepository = incomeRepository;
        this.recurringSavingsRepository = recurringSavingsRepository;
        this.savingsEntryRepository = savingsEntryRepository;
        this.monthlyBudgetRepository = monthlyBudgetRepository;
    }

    public DashboardResponse getDashboardData(Long userId, Integer month, Integer year) {
        // Implement logic to calculate expected income, total saved, available budget, total spent, remaining budget, days left in month, credit card due, and top categories
        // This will involve querying the repositories and performing calculations based on the user's data
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // Expected Income for the month
        BigDecimal expectedIncome = monthlyBudgetRepository.findByUserIdAndMonthAndYear(userId, month, year)
                .map(b -> b.getExpectedIncome())
                .orElseGet(() -> {
                        // no budget set for month, then get it from last month
                        int lastMonth = month == 1 ? 12 : month - 1;
                        int lastMonthYear = month == 1 ? year - 1 : year;

                        return monthlyBudgetRepository.findByUserIdAndMonthAndYear(userId, lastMonth, lastMonthYear)
                                .map(b -> b.getExpectedIncome())
                                .orElse(BigDecimal.ZERO);
                });
        
        // Total Recurring Savings
        BigDecimal totalRecurringSavings = recurringSavingsRepository.findByUserIdAndActive(userId, true)
                .stream()
                .filter(r -> !r.getStartDate().isAfter(start))
                .map(r -> r.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Total one time Savings for the month
        BigDecimal totalOneTimeSavings = savingsEntryRepository.findByUserIdAndDateBetween(userId, start, end)
                .stream()
                .map(s -> s.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Total savings this month
        BigDecimal totalSaved = totalRecurringSavings.add(totalOneTimeSavings);
              
        // Available Budget = Expected Income - Total savings (recurring + one-time)
        BigDecimal availableBudget = expectedIncome.subtract(totalSaved);

        // transaction of the month
        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(userId, start, end);

        // Total Spent this month
        BigDecimal totalSpent = transactions.stream()
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Remaining Budget = Available Budget - Total Spent
        BigDecimal remainingBudget = availableBudget.subtract(totalSpent);

        // Days left in month
        int daysLeft = end.getDayOfMonth() - LocalDate.now().getDayOfMonth();

        // Credit care due
        BigDecimal creditCardDue = transactions.stream()
                .filter(t -> t.getPaymentType().equalsIgnoreCase("CREDIT_CARD"))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Top 3 categories by spending
        Map<String, BigDecimal> categoryTotals = transactions.stream()
                .filter(t -> t.getCategory() != null)
                .collect(Collectors.groupingBy(t -> t.getCategory().getName(),
                        Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)));

        List<DashboardResponse.CategorySummary> topCategories = categoryTotals.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(3)
                .map(e -> {
                    DashboardResponse.CategorySummary summary = new DashboardResponse.CategorySummary();
                    summary.setCategoryName(e.getKey());
                    summary.setTotalSpent(e.getValue());
                    summary.setPercentage(availableBudget.compareTo(BigDecimal.ZERO) > 0 
                              ? e.getValue()
                              .divide(availableBudget, 4, RoundingMode.HALF_UP)
                              .multiply(BigDecimal.valueOf(100))
                              .doubleValue() 
                            : 0.0);
                    return summary;
                })
                .collect(Collectors.toList());



        DashboardResponse response = new DashboardResponse();
        // Set the calculated values in the response object
        response.setExpectedIncome(expectedIncome);
        response.setTotalSaved(totalSaved);
        response.setAvailableBudget(availableBudget);
        response.setTotalSpent(totalSpent);
        response.setRemainingBudget(remainingBudget);
        response.setDaysLeftInMonth(daysLeft);
        response.setCreditCardDue(creditCardDue);
        response.setTopCategories(topCategories);
        return response;
    }
}

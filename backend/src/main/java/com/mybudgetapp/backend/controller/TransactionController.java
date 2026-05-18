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

import com.mybudgetapp.backend.entity.Transaction;
import com.mybudgetapp.backend.service.TransactionsService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionsService transactionService;

    public TransactionController(TransactionsService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getTransactionByUser(userId);
    }

    @GetMapping("/user/{userId}/month")
    public List<Transaction> getTransactionsByMonth(@PathVariable Long userId, @RequestParam LocalDate month, @RequestParam LocalDate year) {
        return transactionService.getTransactionByMonth(userId, month, year);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return ResponseEntity.ok(transactionService.updateTransaction(transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

}

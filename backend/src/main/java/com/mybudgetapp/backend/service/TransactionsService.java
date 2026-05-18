package com.mybudgetapp.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybudgetapp.backend.entity.Transaction;
import com.mybudgetapp.backend.repository.TransactionRepository;

@Service
public class TransactionsService {

    private final TransactionRepository transactionRepository;

    public TransactionsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getTransactionByMonth(Long user, LocalDate start, LocalDate end) {
        return transactionRepository.findByUserIdAndDateBetween(user, start, end);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public Transaction updateTransaction(Transaction updatedTransaction) {
        return transactionRepository.save(updatedTransaction);
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}

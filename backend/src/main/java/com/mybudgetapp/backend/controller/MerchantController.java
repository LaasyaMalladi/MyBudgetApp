package com.mybudgetapp.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybudgetapp.backend.entity.Merchant;
import com.mybudgetapp.backend.service.MerchantService;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/user/{userId}")
    public List<Merchant> getMerchantsByUserId(@PathVariable Long userId) {
        return merchantService.getMerchantsByUser(userId);
    }

    @GetMapping("/search")
    public List<Merchant> searchMerchants(@RequestParam Long userId, @RequestParam String name) {
        return merchantService.searchMerchants(userId, name);
    }

    @PostMapping
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        Merchant createdMerchant = merchantService.createMerchant(merchant);
        return ResponseEntity.ok(createdMerchant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable Long id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }
  }
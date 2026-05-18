package com.mybudgetapp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybudgetapp.backend.entity.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    List<Merchant> findByUserId(Long userId);
    List<Merchant> findByUserIdAndNameContainingIgnoreCase(Long userId, String name);
}

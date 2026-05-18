package com.mybudgetapp.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybudgetapp.backend.entity.Merchant;
import com.mybudgetapp.backend.repository.MerchantRepository;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public List<Merchant> getMerchantsByUser(Long userId) {
        return merchantRepository.findByUserId(userId);
    }

    public List<Merchant> searchMerchants(Long userId, String name) {
        return merchantRepository.findByUserIdAndNameContainingIgnoreCase(userId, name);
    }

    public Merchant createMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }
    
    public void deleteMerchant(Long merchantId) {
        merchantRepository.deleteById(merchantId);
    }
}

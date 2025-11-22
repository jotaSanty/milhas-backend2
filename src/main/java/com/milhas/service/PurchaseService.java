package com.milhas.service;

import com.milhas.entity.Purchase;
import com.milhas.entity.User;
import com.milhas.repository.PurchaseRepository;
import com.milhas.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    public Purchase save(Purchase p, String userEmail) {
        Optional<User> u = userRepository.findByEmail(userEmail);
        u.ifPresent(p::setUser);
        return purchaseRepository.save(p);
    }
}

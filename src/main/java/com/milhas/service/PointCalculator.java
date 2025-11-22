package com.milhas.service;

import com.milhas.entity.CardBrand;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PointCalculator {

    public static long calculatePoints(BigDecimal amount, CardBrand brand, String cardType) {
        if (amount == null) return 0L;
        BigDecimal multiplier = BigDecimal.ONE;
        if (brand != null) {
            String b = brand.getName() == null ? "" : brand.getName().toLowerCase();
            if (b.contains("visa")) multiplier = multiplier.add(BigDecimal.valueOf(0.1));
            if (b.contains("master")) multiplier = multiplier.add(BigDecimal.valueOf(0.05));
            if (b.contains("elo")) multiplier = multiplier.add(BigDecimal.valueOf(0.15));
        }
        if (cardType != null && cardType.equalsIgnoreCase("PLATINUM")) {
            multiplier = multiplier.add(BigDecimal.valueOf(0.5));
        }
        BigDecimal pts = amount.multiply(multiplier);
        return pts.setScale(0, RoundingMode.DOWN).longValue();
    }
}

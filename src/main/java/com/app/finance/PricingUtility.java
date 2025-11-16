package com.app.finance;

import java.math.BigDecimal;

public class PricingUtility {
    private static final double TAX_RATE = 0.05; // 5% tax

    // 1. ORIGINAL: Returns double.
    public double calculateDiscount(double price, double percentage) {
        return price * (percentage / 100.0);
    }

    // 2. ORIGINAL: Simple tax rate getter.
    public double getTaxRate() {
        return TAX_RATE;
    }

    // 3. DEAD CODE: This method is never called by dependents.
    public String getProductCodePrefix() {
        return "PRD_V1_";
    }
}
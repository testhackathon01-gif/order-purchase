package com.app.order;

import com.app.finance.PricingUtility;

public class OrderProcessor {

    public double processOrder(double total, double discountPct) {
        PricingUtility pricing = new PricingUtility();

        // !!! SYNTACTIC BREAK !!!
        // Compiler error: Cannot convert 'BigDecimal' to 'double' without casting.
        double appliedDiscount = pricing.calculateDiscount(total, discountPct);

        return total - appliedDiscount;
    }
}
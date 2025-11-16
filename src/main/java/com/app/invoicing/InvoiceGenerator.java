package com.app.invoicing;

import com.app.finance.PricingUtility;

public class InvoiceGenerator {

    public double calculateTotalWithTax(double subtotal) {
        PricingUtility pricing = new PricingUtility();

        // This compiles, but the value of getTaxRate() has changed from 0.05 to 0.08.
        double rate = pricing.getTaxRate();

        // !!! SEMANTIC BREAK !!!
        // The final calculated tax amount is now incorrect for the business requirements.
        return subtotal * (1.0 + rate);
    }
}

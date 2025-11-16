package com.app.analytics;

import com.app.finance.PricingUtility;
import java.math.BigDecimal;

public class AnalyticsEngine {

    /**
     * Logs the calculated discount, demonstrating the integration point
     * where the change from 'double' to 'BigDecimal' increases runtime risk.
     * The code must now handle an object type (BigDecimal) instead of a primitive.
     */
    public void logDiscount(double price, double percentage) {
        PricingUtility pricing = new PricingUtility();

        // This call is successfully updated to handle BigDecimal,
        // BUT it now depends on the integrity of the object.
        BigDecimal discount = pricing.calculateDiscount(price, percentage);

        // !!! RUNTIME RISK SCENARIO !!!
        // The check 'if (discount != null)' is necessary when dealing with objects,
        // unlike primitives. If the upstream calculation were faulty and returned null
        // (or if conversion failed), this code would throw a NullPointerException.
        // The analyzer flags the structural change that introduced this risk.
        if (discount != null) {
            // Use toPlainString() to avoid scientific notation, common in BigDecimal logging.
            System.out.println("Logged discount: " + discount.toPlainString());
        } else {
            System.err.println("Error: Discount calculation returned null.");
        }
    }

    // Optional: Example of a method not calling the changed dependency
    public boolean checkAnalysisStatus() {
        return true;
    }
}
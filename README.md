💰 Pricing Utility Service (Module A)
This repository hosts the core financial and utility logic for the Order Purchase Service. It contains critical methods used across other modules for calculating discounts, applying tax rates, and handling numerical precision.

📝 Key Functionality
The PricingUtility class provides shared, foundational methods for order processing:

calculateDiscount(double price, double percentage): Calculates a discount amount based on a given price and percentage.

getTaxRate(): Returns the standard, configured tax rate applied to all orders.

Precision Handling: Currently uses Java's built-in BigDecimal to ensure high precision for financial calculations.

⚠️ Integration Notes
This module is a critical dependency. Changes to method signatures or core constants (like the tax rate) will directly impact dependent modules, particularly those in the Order Processing and Invoicing layers.

💻 Code Snippet (PricingUtility.java)
Java

package com.com.app.finance;

import java.math.BigDecimal;

public class PricingUtility {
private static final double TAX_RATE = 0.08; // Current tax rate

    public BigDecimal calculateDiscount(double price, double percentage) {
        // ... BigDecimal implementation ...
    }

    public double getTaxRate() {
        return TAX_RATE;
    }
}
📁 README.md for Module B Repository
Repo Name: order-purchase-processor (Conceptual) Contains: OrderProcessor.java

🛒 Order Processor Service (Module B)
This repository contains the primary business logic for processing and finalizing customer orders. It orchestrates calls to various financial utilities to determine the final order total.

📝 Key Functionality
The OrderProcessor module handles:

Discount Application: Retrieves discount values from the Pricing Utility.

Total Calculation: Computes the final cost of an order after applying discounts.

Flow Control: Manages the steps required to move an order from creation to fulfillment.

⚠️ Dependencies
This module directly depends on the Pricing Utility Service (Module A) for obtaining discount values via the calculateDiscount method.

💻 Code Snippet (OrderProcessor.java)
Java

package com.com.app.order;

import com.com.app.finance.PricingUtility;
import java.math.BigDecimal; // Note: Must handle BigDecimal from Module A

public class OrderProcessor {

    public double processOrder(double total, double discountPct) {
        PricingUtility pricing = new PricingUtility();
        
        // This variable type must match the dependency's return type.
        BigDecimal appliedDiscount = pricing.calculateDiscount(total, discountPct); 
        
        return total - appliedDiscount.doubleValue(); 
    }
}
📁 README.md for Module C Repository
Repo Name: order-purchase-invoicing (Conceptual) Contains: InvoiceGenerator.java

📄 Invoice Generator Service (Module C)
This repository is responsible for generating accurate financial documentation (invoices) for processed orders. Its logic is heavily reliant on fetching current financial constants.

📝 Key Functionality
The InvoiceGenerator module handles:

Tax Calculation: Fetches the current tax rate and applies it to the order subtotal.

Invoice Formatting: Structures the final total, tax, and subtotals for client display or archival.

Regulatory Compliance: Ensures tax application adheres to current system constants.

⚠️ Dependencies
This module depends on the Pricing Utility Service (Module A), specifically calling getTaxRate() to calculate the final amount owed. Changes to the underlying tax constant can lead to silent financial errors in generated invoices.

💻 Code Snippet (InvoiceGenerator.java)
Java

package com.com.app.invoicing;

import com.com.app.finance.PricingUtility;

public class InvoiceGenerator {

    public double calculateTotalWithTax(double subtotal) {
        PricingUtility pricing = new PricingUtility();
        
        // Relies on the constant value returned by Module A.
        double rate = pricing.getTaxRate(); 
        
        return subtotal * (1.0 + rate);
    }
}
📁 README.md for Module D Repository
Repo Name: order-purchase-analytics (Conceptual) Contains: AnalyticsEngine.java

📊 Analytics Engine Service (Module D)
This repository focuses on post-transaction processing, specifically logging and analysis of key financial metrics (like discounts and total sales) for business intelligence purposes.

📝 Key Functionality
The AnalyticsEngine module performs:

Metric Logging: Records discount amounts, transaction types, and other data points.

Data Preparation: Converts complex financial objects into simple logging formats (e.g., converting BigDecimal to a String).

Reporting Hook: Provides hooks for external monitoring and reporting tools.

⚠️ Dependencies
This module uses the Pricing Utility Service (Module A) to retrieve processed discount information via the calculateDiscount method for logging purposes. It must handle the object return type (BigDecimal) defensively to avoid runtime exceptions.

💻 Code Snippet (AnalyticsEngine.java)
Java

package com.com.app.analytics;

import com.com.app.finance.PricingUtility;
import java.math.BigDecimal;

public class AnalyticsEngine {

    public void logDiscount(double price, double percentage) {
        PricingUtility pricing = new PricingUtility();
        
        // Must handle the object type returned by the dependency.
        BigDecimal discount = pricing.calculateDiscount(price, percentage);
        
        // Defensive coding is required to prevent NullPointerException (NPE).
        if (discount != null) { 
            System.out.println("Logged discount: " + discount.toPlainString());
        }
    }
}

### Prerequisites

* **Java Development Kit (JDK) 17+**
* **Gradle** (or similar build tool used in your project setup)

### Installation

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/testhackathon01-gif/order-purchase.git
    cd order-purchase
    ```

2.  **Build the Project:**
    Compile the application using Gradle.
    ```bash
    ./gradlew clean build
    ```

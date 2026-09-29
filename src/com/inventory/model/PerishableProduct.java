package com.inventory.model;

import java.time.LocalDate;

/** Optional inheritance example: a product with an expiry date. */
public class PerishableProduct extends Product {
    private static final long serialVersionUID = 1L;

    private LocalDate expiryDate;

    public PerishableProduct(String id, String name, String category, double price,
                             int quantity, Supplier supplier, LocalDate expiryDate) {
        super(id, name, category, price, quantity, supplier);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public boolean isExpired() { return LocalDate.now().isAfter(expiryDate); }

    @Override
    public String toString() {
        return super.toString() + String.format(" [expires=%s, expired=%b]", expiryDate, isExpired());
    }
}
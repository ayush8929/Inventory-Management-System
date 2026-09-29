package com.inventory.model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private Supplier supplier;

    public Product(String id, String name, String category,
                   double price, int quantity, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Supplier getSupplier() { return supplier; }

    // Setters (id is immutable once created)
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    /** Total value of this product's stock. */
    public double getTotalValue() { return price * quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return Objects.equals(id, ((Product) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return String.format("Product[id=%s, name=%s, category=%s, price=%.2f, qty=%d, supplier=%s]",
                id, name, category, price, quantity,
                supplier != null ? supplier.getName() : "N/A");
    }
}

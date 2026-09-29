package com.inventory.model;

import java.time.LocalDate;

/** Temporary test driver for Phase 2. Delete or move to JUnit later. */
public class ModelTest {
    public static void main(String[] args) {
        Supplier s1 = new Supplier("S001", "Acme Traders", "9876543210", "acme@example.com");

        Product laptop = new Product("P001", "Laptop", "Electronics", 55000.0, 10, s1);
        Product milk = new PerishableProduct("P002", "Milk 1L", "Grocery", 60.0, 50, s1,
                LocalDate.now().plusDays(5));

        System.out.println(s1);
        System.out.println(laptop);
        System.out.println(milk);

        laptop.setQuantity(8);
        laptop.setPrice(52000.0);
        System.out.println("\nAfter update: " + laptop);
        System.out.println("Total stock value: " + laptop.getTotalValue());

        // equals/hashCode are based on id
        Product duplicate = new Product("P001", "Other", "X", 1, 1, s1);
        System.out.println("\nSame id treated as equal? " + laptop.equals(duplicate));
    }
}
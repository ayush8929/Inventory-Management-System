package com.inventory.service;

import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;
import com.inventory.model.Supplier;

import java.time.LocalDate;
import java.util.List;

/** Temporary test driver for Phase 3. */
public class ServiceTest {

    private static void print(String title, List<Product> list) {
        System.out.println("\n--- " + title + " ---");
        if (list.isEmpty()) {
            System.out.println("(none)");
        }
        for (Product p : list) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        InventoryService service = new InventoryService();

        Supplier s1 = new Supplier("S001", "Acme Traders", "9876543210", "acme@example.com");
        Supplier s2 = new Supplier("S002", "FreshFarm", "9123456780", "fresh@example.com");
        System.out.println("Add supplier S001: " + service.addSupplier(s1));
        System.out.println("Add supplier S002: " + service.addSupplier(s2));
        System.out.println("Add duplicate S001: " + service.addSupplier(s1));

        // CREATE
        service.addProduct(new Product("P001", "Laptop", "Electronics", 55000, 10, s1));
        service.addProduct(new Product("P002", "Mouse", "Electronics", 500, 100, s1));
        service.addProduct(new Product("P003", "Keyboard", "Electronics", 1500, 40, s1));
        service.addProduct(new PerishableProduct("P004", "Milk 1L", "Grocery", 60, 50, s2,
                LocalDate.now().plusDays(5)));

        boolean dup = service.addProduct(new Product("P001", "Fake", "X", 1, 1, s1));
        System.out.println("\nAdd duplicate P001: " + dup);

        // READ
        print("All products", service.viewAll());
        System.out.println("\nSearch by id P002: " + service.searchById("P002"));
        System.out.println("Search by id P999: " + service.searchById("P999"));
        print("Search name 'lap'", service.searchByName("lap"));
        print("Category 'electronics'", service.searchByCategory("electronics"));

        // UPDATE
        System.out.println("\nUpdate P002: "
                + service.updateProduct("P002", "Wireless Mouse", "Electronics", 700, 90));
        System.out.println("Update P999: "
                + service.updateProduct("P999", "Ghost", "X", 1, 1));
        System.out.println(service.searchById("P002"));

        // SORT
        print("Sorted by price", service.sortByPrice());
        print("Sorted by quantity", service.sortByQuantity());
        print("Sorted by name", service.sortByName());

        // DELETE
        System.out.println("\nDelete P003: " + service.deleteProduct("P003"));
        System.out.println("Delete P003 again: " + service.deleteProduct("P003"));
        System.out.println("Removed from Grocery: " + service.removeByCategory("Grocery"));

        print("Remaining products", service.viewAll());
        System.out.println("\nProduct count: " + service.getProductCount());
        System.out.println("Total inventory value: " + service.getTotalInventoryValue());
    }
}
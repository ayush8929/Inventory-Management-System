package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.model.Supplier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Holds all inventory data in memory and provides CRUD operations.
 * Phase 3: methods return boolean/null to signal failure.
 * Phase 4 will replace these with custom exceptions.
 */
public class InventoryService {

    // Product id -> Product (O(1) lookup by id)
    private final Map<String, Product> products = new HashMap<>();
    private final List<Supplier> suppliers = new ArrayList<>();

    // ---------------- Supplier operations ----------------

    public boolean addSupplier(Supplier supplier) {
        if (supplier == null || findSupplierById(supplier.getId()) != null) {
            return false;
        }
        suppliers.add(supplier);
        return true;
    }

    public Supplier findSupplierById(String id) {
        for (Supplier s : suppliers) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(suppliers); // copy, so callers can't modify our list
    }

    // ---------------- Product CRUD ----------------

    /** Create. Returns false if the product is null or the id already exists. */
    public boolean addProduct(Product product) {
        if (product == null || products.containsKey(product.getId())) {
            return false;
        }
        products.put(product.getId(), product);
        return true;
    }

    /** Update. Returns false if no product has this id. */
    public boolean updateProduct(String id, String name, String category,
                                 double price, int quantity) {
        Product p = products.get(id);
        if (p == null) {
            return false;
        }
        p.setName(name);
        p.setCategory(category);
        p.setPrice(price);
        p.setQuantity(quantity);
        return true;
    }

    /** Delete. Returns false if no product has this id. */
    public boolean deleteProduct(String id) {
        return products.remove(id) != null;
    }

    // ---------------- Search ----------------

    /** Returns the product, or null if not found. */
    public Product searchById(String id) {
        return products.get(id);
    }

    /** Case-insensitive "contains" search on the product name. */
    public List<Product> searchByName(String keyword) {
        String key = keyword.toLowerCase();
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }

    public List<Product> searchByCategory(String category) {
        return products.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // ---------------- View & sort ----------------

    public List<Product> viewAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> sortByPrice() {
        return products.values().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public List<Product> sortByQuantity() {
        return products.values().stream()
                .sorted(Comparator.comparingInt(Product::getQuantity))
                .collect(Collectors.toList());
    }

    public List<Product> sortByName() {
        return products.values().stream()
                .sorted(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    // ---------------- Extras ----------------

    /** Iterator demo: safely removes while looping. Returns how many were removed. */
    public int removeByCategory(String category) {
        int removed = 0;
        Iterator<Product> it = products.values().iterator();
        while (it.hasNext()) {
            if (it.next().getCategory().equalsIgnoreCase(category)) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    public double getTotalInventoryValue() {
        return products.values().stream().mapToDouble(Product::getTotalValue).sum();
    }

    public int getProductCount() {
        return products.size();
    }
}
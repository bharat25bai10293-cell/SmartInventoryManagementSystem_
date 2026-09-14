package inventory.service;

import inventory.model.Category;
import inventory.model.Product;

import java.util.*;

public class ReportService {
    private final InventoryService inventory;

    public ReportService(InventoryService inventory) {
        this.inventory = inventory;
    }

    public void printInventorySummary() {
        List<Product> products = inventory.getAll();
        int totalUnits = products.stream().mapToInt(Product::getQuantity).sum();
        double stockValue = products.stream()
                .mapToDouble(p -> p.getQuantity() * p.getPrice()).sum();

        System.out.println("\n========== INVENTORY SUMMARY ==========");
        System.out.println("Unique products : " + products.size());
        System.out.println("Total units     : " + totalUnits);
        System.out.printf("Stock value     : %.2f%n", stockValue);
        System.out.println("Low-stock items : " + inventory.lowStock().size());
    }

    public void printCategorySummary() {
        System.out.println("\n========== CATEGORY SUMMARY ==========");
        for (Category c : Category.values()) {
            int count = inventory.byCategory(c).size();
            int units = inventory.byCategory(c).stream()
                    .mapToInt(Product::getQuantity).sum();
            System.out.printf("%-15s Products: %3d | Units: %4d%n", c, count, units);
        }
    }

    public void printLowStock() {
        System.out.println("\n========== LOW STOCK ==========");
        List<Product> low = inventory.lowStock();
        if (low.isEmpty()) {
            System.out.println("No low-stock products.");
            return;
        }
        printTable(low);
    }

    public static void printTable(List<Product> products) {
        System.out.printf("%-5s %-22s %-14s %10s %8s %10s%n",
                "ID", "Name", "Category", "Price", "Stock", "Threshold");
        System.out.println("--------------------------------------------------------------------------");
        products.forEach(System.out::println);
    }
}

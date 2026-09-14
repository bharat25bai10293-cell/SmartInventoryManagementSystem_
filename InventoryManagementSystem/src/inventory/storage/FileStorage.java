package inventory.storage;

import inventory.exception.InventoryException;
import inventory.model.Bill;
import inventory.model.Category;
import inventory.model.Product;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileStorage {
    private final Path dataDir;
    private final Path productFile;
    private final Path salesFile;

    public FileStorage(String directory) {
        dataDir = Paths.get(directory);
        productFile = dataDir.resolve("products.csv");
        salesFile = dataDir.resolve("sales.csv");
        initialize();
    }

    private void initialize() {
        try {
            Files.createDirectories(dataDir);
            if (!Files.exists(productFile)) {
                Files.writeString(productFile, "id,name,category,price,quantity,lowStockThreshold\n");
            }
            if (!Files.exists(salesFile)) {
                Files.writeString(salesFile, "billId,dateTime,total\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to initialize data storage: " + e.getMessage());
        }
    }

    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(productFile);
            for (int i = 1; i < lines.size(); i++) {
                if (lines.get(i).isBlank()) continue;
                String[] p = lines.get(i).split(",", -1);
                if (p.length != 6) continue;

                try {
                    products.add(new Product(
                            Integer.parseInt(p[0]),
                            p[1],
                            Category.valueOf(p[2]),
                            Double.parseDouble(p[3]),
                            Integer.parseInt(p[4]),
                            Integer.parseInt(p[5])
                    ));
                } catch (Exception ignored) {
                    // Ignore malformed rows instead of crashing the application.
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load products: " + e.getMessage());
        }
        return products;
    }

    public void saveProducts(Collection<Product> products) {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("id,name,category,price,quantity,lowStockThreshold");
            for (Product p : products) lines.add(p.toCsv());
            Files.write(productFile, lines);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save products: " + e.getMessage());
        }
    }

    public void saveBill(Bill bill) {
        try {
            String line = String.format(Locale.US, "%d,%s,%.2f%n",
                    bill.getBillId(), bill.getDateTime(), bill.getTotal());
            Files.writeString(salesFile, line, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save bill: " + e.getMessage());
        }
    }
}

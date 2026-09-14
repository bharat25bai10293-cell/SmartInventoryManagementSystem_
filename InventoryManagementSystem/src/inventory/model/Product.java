package inventory.model;

import inventory.exception.InsufficientStockException;
import inventory.exception.InventoryException;

public class Product {
    private final int id;
    private String name;
    private Category category;
    private double price;
    private int quantity;
    private int lowStockThreshold;

    public Product(int id, String name, Category category, double price,
                   int quantity, int lowStockThreshold) throws InventoryException {
        if (id <= 0) throw new InventoryException("Product ID must be positive.");
        if (name == null || name.isBlank()) throw new InventoryException("Product name cannot be empty.");
        if (category == null) throw new InventoryException("Category is required.");
        if (price < 0) throw new InventoryException("Price cannot be negative.");
        if (quantity < 0) throw new InventoryException("Quantity cannot be negative.");
        if (lowStockThreshold < 0) throw new InventoryException("Low-stock threshold cannot be negative.");

        this.id = id;
        this.name = name.trim();
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.lowStockThreshold = lowStockThreshold;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getLowStockThreshold() { return lowStockThreshold; }

    public void update(String name, Category category, double price,
                       int lowStockThreshold) throws InventoryException {
        if (name == null || name.isBlank()) throw new InventoryException("Product name cannot be empty.");
        if (category == null) throw new InventoryException("Category is required.");
        if (price < 0 || lowStockThreshold < 0)
            throw new InventoryException("Price and threshold cannot be negative.");

        this.name = name.trim();
        this.category = category;
        this.price = price;
        this.lowStockThreshold = lowStockThreshold;
    }

    public void addStock(int amount) throws InventoryException {
        if (amount <= 0) throw new InventoryException("Stock addition must be positive.");
        quantity += amount;
    }

    public void removeStock(int amount) throws InventoryException {
        if (amount <= 0) throw new InventoryException("Stock removal must be positive.");
        if (amount > quantity)
            throw new InsufficientStockException("Insufficient stock for " + name + ".");
        quantity -= amount;
    }

    public boolean isLowStock() {
        return quantity <= lowStockThreshold;
    }

    public String toCsv() {
        return id + "," + escape(name) + "," + category + "," + price + "," + quantity + "," + lowStockThreshold;
    }

    private String escape(String value) {
        return value.replace(",", " ");
    }

    @Override
    public String toString() {
        return String.format("%-5d %-22s %-14s %10.2f %8d %10d",
                id, name, category, price, quantity, lowStockThreshold);
    }
}

package inventory.service;

import inventory.exception.InventoryException;
import inventory.model.Category;
import inventory.model.Product;
import inventory.storage.FileStorage;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryService {
    private final Map<Integer, Product> products = new LinkedHashMap<>();
    private final FileStorage storage;

    public InventoryService(FileStorage storage) {
        this.storage = storage;
        for (Product p : storage.loadProducts()) products.put(p.getId(), p);
    }

    public void addProduct(Product product) throws InventoryException {
        if (products.containsKey(product.getId()))
            throw new InventoryException("Product ID already exists.");
        products.put(product.getId(), product);
        save();
    }

    public void updateProduct(int id, String name, Category category,
                              double price, int threshold) throws InventoryException {
        Product product = requireProduct(id);
        product.update(name, category, price, threshold);
        save();
    }

    public void removeProduct(int id) throws InventoryException {
        requireProduct(id);
        products.remove(id);
        save();
    }

    public void addStock(int id, int amount) throws InventoryException {
        Product product = requireProduct(id);
        product.addStock(amount);
        save();
    }

    public void removeStock(int id, int amount) throws InventoryException {
        Product product = requireProduct(id);
        product.removeStock(amount);
        save();
    }

    public Product findById(int id) throws InventoryException {
        return requireProduct(id);
    }

    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> search(String keyword) {
        String k = keyword.toLowerCase();
        return products.values().stream()
                .filter(p -> String.valueOf(p.getId()).contains(k)
                        || p.getName().toLowerCase().contains(k)
                        || p.getCategory().name().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }

    public List<Product> byCategory(Category category) {
        return products.values().stream()
                .filter(p -> p.getCategory() == category)
                .collect(Collectors.toList());
    }

    public List<Product> lowStock() {
        return products.values().stream()
                .filter(Product::isLowStock)
                .collect(Collectors.toList());
    }

    public void save() {
        storage.saveProducts(products.values());
    }

    private Product requireProduct(int id) throws InventoryException {
        Product product = products.get(id);
        if (product == null) throw new InventoryException("Product not found: " + id);
        return product;
    }
}

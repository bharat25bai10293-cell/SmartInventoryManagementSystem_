package inventory;

import inventory.exception.InventoryException;
import inventory.model.Category;
import inventory.model.Product;
import inventory.service.BillingService;
import inventory.service.InventoryService;
import inventory.service.ReportService;
import inventory.storage.FileStorage;
import inventory.util.InputUtil;

import java.util.*;

public class Main {
    private final InputUtil input;
    private final InventoryService inventory;
    private final BillingService billing;
    private final ReportService reports;

    public Main() {
        Scanner scanner = new Scanner(System.in);
        input = new InputUtil(scanner);
        FileStorage storage = new FileStorage("data");
        inventory = new InventoryService(storage);
        billing = new BillingService(inventory, storage);
        reports = new ReportService(inventory);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("==============================================");
        System.out.println("      SMART INVENTORY MANAGEMENT SYSTEM       ");
        System.out.println("==============================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = input.nonNegativeInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> updateProduct();
                    case 3 -> removeProduct();
                    case 4 -> listProducts();
                    case 5 -> searchProducts();
                    case 6 -> stockOperation(true);
                    case 7 -> stockOperation(false);
                    case 8 -> createBill();
                    case 9 -> reports.printLowStock();
                    case 10 -> reports.printInventorySummary();
                    case 11 -> reports.printCategorySummary();
                    case 0 -> {
                        inventory.save();
                        running = false;
                        System.out.println("Data saved. Goodbye!");
                    }
                    default -> System.out.println("Invalid menu option.");
                }
            } catch (InventoryException | RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--------------- MENU ----------------");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. List All Products");
        System.out.println("5. Search Products");
        System.out.println("6. Add Stock");
        System.out.println("7. Remove Stock");
        System.out.println("8. Create Bill");
        System.out.println("9. Low-Stock Report");
        System.out.println("10. Inventory Summary");
        System.out.println("11. Category Summary");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------");
    }

    private void addProduct() throws InventoryException {
        int id = input.positiveInt("Product ID: ");
        String name = input.text("Product name: ");
        Category category = input.category();
        double price = input.nonNegativeDouble("Price: ");
        int quantity = input.nonNegativeInt("Initial quantity: ");
        int threshold = input.nonNegativeInt("Low-stock threshold: ");

        inventory.addProduct(new Product(id, name, category, price, quantity, threshold));
        System.out.println("Product added successfully.");
    }

    private void updateProduct() throws InventoryException {
        int id = input.positiveInt("Product ID: ");
        String name = input.text("New name: ");
        Category category = input.category();
        double price = input.nonNegativeDouble("New price: ");
        int threshold = input.nonNegativeInt("New low-stock threshold: ");

        inventory.updateProduct(id, name, category, price, threshold);
        System.out.println("Product updated successfully.");
    }

    private void removeProduct() throws InventoryException {
        int id = input.positiveInt("Product ID: ");
        inventory.removeProduct(id);
        System.out.println("Product deleted successfully.");
    }

    private void listProducts() {
        List<Product> products = inventory.getAll();
        if (products.isEmpty()) System.out.println("No products available.");
        else ReportService.printTable(products);
    }

    private void searchProducts() {
        String keyword = input.text("Search by ID, name or category: ");
        List<Product> results = inventory.search(keyword);
        if (results.isEmpty()) System.out.println("No matching products.");
        else ReportService.printTable(results);
    }

    private void stockOperation(boolean add) throws InventoryException {
        int id = input.positiveInt("Product ID: ");
        int amount = input.positiveInt("Quantity: ");
        if (add) inventory.addStock(id, amount);
        else inventory.removeStock(id, amount);
        System.out.println("Stock updated successfully.");
    }

    private void createBill() throws InventoryException {
        int id = input.positiveInt("Product ID: ");
        int quantity = input.positiveInt("Quantity sold: ");
        var bill = billing.createBill(id, quantity);
        System.out.println(bill.printBill());
    }
}

import inventory.exception.InventoryException;
import inventory.model.Category;
import inventory.model.Product;
import inventory.storage.FileStorage;
import inventory.service.InventoryService;

public class ValidationTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) throws Exception {
        testNegativePrice();
        testNegativeQuantity();
        testDuplicateId();
        testSearch();
        testLowStock();

        System.out.println("\nTests passed: " + passed);
        System.out.println("Tests failed: " + failed);

        if (failed > 0) System.exit(1);
    }

    private static void testNegativePrice() {
        try {
            new Product(1, "Test", Category.OTHER, -1, 2, 1);
            fail("Negative price should be rejected.");
        } catch (InventoryException e) {
            pass("Negative price validation");
        }
    }

    private static void testNegativeQuantity() {
        try {
            new Product(2, "Test", Category.OTHER, 10, -2, 1);
            fail("Negative quantity should be rejected.");
        } catch (InventoryException e) {
            pass("Negative quantity validation");
        }
    }

    private static void testDuplicateId() throws Exception {
        FileStorage storage = new FileStorage("data");
        InventoryService service = new InventoryService(storage);
        service.addProduct(new Product(99991, "Test A", Category.OTHER, 10, 5, 1));

        try {
            service.addProduct(new Product(99991, "Test B", Category.OTHER, 20, 5, 1));
            fail("Duplicate ID should be rejected.");
        } catch (InventoryException e) {
            pass("Duplicate ID validation");
        } finally {
            try { service.removeProduct(99991); } catch (Exception ignored) {}
        }
    }

    private static void testSearch() throws Exception {
        FileStorage storage = new FileStorage("data");
        InventoryService service = new InventoryService(storage);
        service.addProduct(new Product(99992, "SearchMarker", Category.STATIONERY, 5, 10, 2));

        boolean found = service.search("SearchMarker").stream()
                .anyMatch(p -> p.getId() == 99992);

        if (found) pass("Product search");
        else fail("Product search failed.");

        service.removeProduct(99992);
    }

    private static void testLowStock() throws Exception {
        Product p = new Product(99993, "LowStock", Category.OTHER, 10, 2, 3);
        if (p.isLowStock()) pass("Low-stock detection");
        else fail("Low-stock detection failed.");
    }

    private static void pass(String name) {
        passed++;
        System.out.println("[PASS] " + name);
    }

    private static void fail(String name) {
        failed++;
        System.out.println("[FAIL] " + name);
    }
}

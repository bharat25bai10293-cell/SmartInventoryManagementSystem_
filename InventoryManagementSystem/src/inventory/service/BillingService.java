package inventory.service;

import inventory.exception.InventoryException;
import inventory.model.Bill;
import inventory.model.BillItem;
import inventory.model.Product;
import inventory.storage.FileStorage;

import java.util.concurrent.atomic.AtomicInteger;

public class BillingService {
    private final InventoryService inventory;
    private final FileStorage storage;
    private final AtomicInteger nextBillId = new AtomicInteger(1001);

    public BillingService(InventoryService inventory, FileStorage storage) {
        this.inventory = inventory;
        this.storage = storage;
    }

    public Bill createBill(int productId, int quantity) throws InventoryException {
        Product product = inventory.findById(productId);
        product.removeStock(quantity);

        Bill bill = new Bill(nextBillId.getAndIncrement());
        bill.addItem(new BillItem(product.getId(), product.getName(), quantity, product.getPrice()));

        inventory.save();
        storage.saveBill(bill);
        return bill;
    }
}

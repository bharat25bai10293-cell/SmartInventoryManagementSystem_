package inventory.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private final int billId;
    private final LocalDateTime dateTime;
    private final List<BillItem> items;

    public Bill(int billId) {
        this.billId = billId;
        this.dateTime = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public void addItem(BillItem item) {
        items.add(item);
    }

    public int getBillId() { return billId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public List<BillItem> getItems() { return List.copyOf(items); }

    public double getTotal() {
        return items.stream().mapToDouble(BillItem::getSubtotal).sum();
    }

    public String printBill() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==================== BILL ====================\n");
        sb.append("Bill ID: ").append(billId).append("\n");
        sb.append("Date: ").append(dateTime).append("\n");
        sb.append("----------------------------------------------\n");
        sb.append(String.format("%-22s %5s %10s %10s%n", "Product", "Qty", "Price", "Subtotal"));
        for (BillItem item : items) sb.append(item).append("\n");
        sb.append("----------------------------------------------\n");
        sb.append(String.format("TOTAL: %.2f%n", getTotal()));
        sb.append("==============================================\n");
        return sb.toString();
    }
}

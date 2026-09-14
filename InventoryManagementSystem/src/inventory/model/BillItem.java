package inventory.model;

public class BillItem {
    private final int productId;
    private final String productName;
    private final int quantity;
    private final double unitPrice;

    public BillItem(int productId, String productName, int quantity, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    public double getSubtotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%-22s %5d %10.2f %10.2f",
                productName, quantity, unitPrice, getSubtotal());
    }
}

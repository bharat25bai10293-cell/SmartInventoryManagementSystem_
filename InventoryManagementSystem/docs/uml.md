# UML / Design Diagrams

## Use Case Diagram

```text
                 +----------------------------------+
                 | Smart Inventory System            |
                 |                                  |
Store User ----> | Manage Products                  |
Store User ----> | Manage Stock                    |
Store User ----> | Search Products                 |
Store User ----> | Generate Bills                  |
Store User ----> | View Reports                    |
                 +----------------------------------+
```

## Class Diagram

```text
Product
- id
- name
- category
- price
- quantity
- lowStockThreshold
        |
        v
Inventory
- products
+ addProduct()
+ updateProduct()
+ removeProduct()
+ search()

BillingService
+ createBill()

Bill
- billId
- items
- total

BillItem
- productId
- productName
- quantity
- unitPrice

FileStorage
+ loadProducts()
+ saveProducts()
+ loadBills()
+ saveBill()
```

## Sequence Diagram

```text
User -> Main: Select "Create Bill"
Main -> BillingService: createBill()
BillingService -> Inventory: findById()
Inventory --> BillingService: Product
BillingService -> Product: reduceStock()
BillingService -> FileStorage: saveProducts()
BillingService -> FileStorage: saveBill()
BillingService --> Main: Bill
Main --> User: Print bill
```

## Storage Design

### products.csv
`id,name,category,price,quantity,lowStockThreshold`

### sales.csv
`billId,dateTime,total`

### sale_items.csv
`billId,productId,productName,quantity,unitPrice`

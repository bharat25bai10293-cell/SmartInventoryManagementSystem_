# Smart Inventory Management System

A terminal-based Java project for managing products, stock, billing, and inventory reports.

## Problem

Small shops often track products manually, which can cause incorrect stock counts, difficulty finding products, and poor visibility of low-stock items.

## Solution

This application provides a menu-driven inventory system with:
- Product CRUD operations
- Stock addition/removal
- Search and category filtering
- Low-stock detection
- Billing
- Sales reporting
- CSV-based persistent storage
- Input validation and exception handling

## Major Modules

1. **Product & Inventory Management** - Add, update, delete, search, and list products.
2. **Stock Management** - Add/remove stock and identify low-stock products.
3. **Billing** - Create bills and automatically reduce inventory.
4. **Reports** - Inventory summary, category summary, and low-stock reporting.
5. **Persistence** - Data is stored in CSV files inside `data/`.

## Screenshots & Application Flow

> **Note:** Place your image files in a `docs/` or `assets/` folder in your project repository matching the paths below.

### 1. Adding a Product (Input Validation & Error Handling)
Demonstrates creating a new product (`snacks`), input validation on categories, and setting initial stock levels.
![Add Product]![alt text](<Screenshot 2026-09-14 151756.png>)

### 2. Listing All Products
Displays the full product list organized by ID, Name, Category, Price, Stock, and Low-Stock Threshold.
![List All Products]![alt text](<Screenshot 2026-09-14 151813.png>)

### 3. Updating & Deleting Products
* **Update Product Details:** Modifying product properties like category, price, and threshold.
![Update Product]![alt text](<Screenshot 2026-09-14 151813-1.png>)

* **Delete Product:** Removing an item by ID from the inventory.
![Delete Product]![alt text](<Screenshot 2026-09-14 151924.png>)

### 4. Searching Products
Searching inventory records dynamically by ID, name, or category.
![Search Products]![alt text](<Screenshot 2026-09-14 151957.png>)
### 5. Generating Bills
Creating customer bills and dynamically adjusting available stock quantities upon purchase.
![Create Bill]![alt text](<Screenshot 2026-09-14 152103.png>)

### 6. Reports & Summaries
* **Inventory Summary:** Aggregated view of unique products, total units, overall stock value, and low-stock counts.
![Inventory Summary]![alt text](<Screenshot 2026-09-14 152015.png>)

* **Category Summary:** Breakdown of product counts and total units per category.
![Category Summary]![alt text](<Screenshot 2026-09-14 152027.png>)

* **Low-Stock Report:** Filters and flags items that have fallen to or below their reorder threshold.
![Low Stock Report]![alt text](<Screenshot 2026-09-14 152041.png>)

---

## Java Concepts Demonstrated

- **OOP Fundamentals:** Classes, objects, encapsulation, inheritance, interfaces, polymorphism.
- **Collections Framework:** Use of `ArrayList` and `HashMap` for data lookup and state management.
- **Error Handling:** Custom exceptions and interactive terminal validation.
- **Data Persistence:** File I/O with CSV file handling.
- **Architecture:** Clean, modular package structure (`model`, `service`, `storage`, `util`).

## Requirements

- JDK 17 or later
- Terminal / Command Prompt / PowerShell
- No external libraries required

## Project Structure

```text
InventoryManagementSystem/
├── src/
│   └── inventory/
│       ├── Main.java
│       ├── model/
│       ├── service/
│       ├── storage/
│       ├── exception/
│       └── util/
├── tests/
├── data/
├── docs/
├── statement.md
└── README.md
```

## Run from Terminal

### Windows

From the project root:

```bat
javac -d out src\inventory\Main.java src\inventory\model\*.java src\inventory\service\*.java src\inventory\storage\*.java src\inventory\exception\*.java src\inventory\util\*.java
java -cp out inventory.Main
```

### Linux / macOS

```bash
javac -d out src/inventory/Main.java src/inventory/model/*.java src/inventory/service/*.java src/inventory/storage/*.java src/inventory/exception/*.java src/inventory/util/*.java
java -cp out inventory.Main
```

The program automatically generates `data/products.csv` and `data/sales.csv` upon run.

## Testing

Compile and run the validation unit tests:

### Windows

```bat
javac -d out tests\ValidationTest.java src\inventory\model\*.java src\inventory\service\*.java src\inventory\storage\*.java src\inventory\exception\*.java src\inventory\util\*.java
java -cp out ValidationTest
```

### Linux / macOS

```bash
javac -d out tests/ValidationTest.java src/inventory/model/*.java src/inventory/service/*.java src/inventory/storage/*.java src/inventory/exception/*.java src/inventory/util/*.java
java -cp out ValidationTest
```

## Notes

The application is intentionally dependency-free so it can be evaluated directly from any standard Java terminal without GUI or build tool configuration.

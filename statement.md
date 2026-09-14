# Project Statement

## Project Title
Smart Inventory Management System

## Problem Statement
Small retail businesses frequently manage products and stock manually. This makes it difficult to maintain accurate stock levels, find products quickly, identify low-stock items, and calculate bills consistently.

## Scope
The project covers product management, stock operations, searching, billing, sales recording, and inventory reporting. It is designed as a local command-line application and does not include online payments, multi-user networking, or a web interface.

## Target Users
- Small shop owners
- Store assistants
- Student project evaluators
- Anyone learning Java application development

## High-Level Features
- Add, update and delete products
- View all products
- Search by ID/name/category
- Add and remove stock
- Low-stock alerts
- Generate customer bills
- Automatic stock deduction after billing
- Inventory and sales reports
- CSV persistence
- Validation and exception handling

## Functional Requirements
1. The system shall allow creation of products.
2. The system shall allow modification and deletion of products.
3. The system shall allow stock to be increased or decreased.
4. The system shall support product search.
5. The system shall identify products at or below their low-stock threshold.
6. The system shall create bills containing multiple products.
7. The system shall deduct sold quantities from stock.
8. The system shall save products and sales to files.
9. The system shall produce summary reports.

## Non-Functional Requirements
- **Usability:** Menu-driven terminal interface with clear prompts.
- **Reliability:** Validation prevents invalid numeric values and insufficient-stock billing.
- **Maintainability:** Code is separated into model, service, storage, utility and exception packages.
- **Performance:** In-memory collections provide fast normal product searches for a small/medium inventory.
- **Resource efficiency:** No external framework or database is required.
- **Error handling:** Custom exceptions and controlled input validation prevent program crashes during normal invalid input.

## Constraints
- Local command-line application.
- CSV storage instead of a database.
- Single-user operation.

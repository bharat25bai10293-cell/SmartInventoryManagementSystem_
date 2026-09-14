# Project Report Content

Use this content as the basis of the portal PDF report.

## 1. Cover Page
**Smart Inventory Management System**  
Course: Java / Programming Course  
Student: [Your Name]  
Registration Number: [Your Registration Number]  
Institution: VIT Bhopal University  
Academic Year: 2026

## 2. Introduction
The project implements a terminal-based inventory management application using Java. It demonstrates object-oriented programming, collections, exception handling, file handling and modular software design.

## 3. Problem Statement
Manual inventory tracking can lead to incorrect stock quantities, slow product lookup, and billing errors. The proposed system centralizes these operations.

## 4. Functional Requirements
See `statement.md`.

## 5. Non-functional Requirements
See `statement.md`.

## 6. System Architecture
The application follows a layered design consisting of CLI, service, model and storage layers.

## 7. Design Diagrams
Include the diagrams from `docs/architecture.md` and `docs/uml.md`.

## 8. Design Decisions & Rationale
A terminal interface was selected because the evaluation requires command-line execution. CSV storage was selected because it keeps the project dependency-free and easy to inspect. A service layer separates business logic from user interaction.

## 9. Implementation Details
The model package represents products and bills. The service package contains inventory, billing and reporting logic. The storage package handles CSV persistence. Utility classes validate user input and exception classes represent domain errors.

## 10. Screenshots / Results
Add your own screenshots after running the program. Recommended screenshots:
1. Main menu
2. Product listing
3. Product search
4. Low-stock report
5. Generated bill
6. Inventory summary

## 11. Testing Approach
The project includes a Java validation test covering invalid price, invalid quantity, duplicate IDs, search and low-stock detection.

## 12. Challenges Faced
Typical implementation challenges included maintaining consistency between in-memory inventory and persistent CSV data, validating terminal input, and handling insufficient stock.

## 13. Learnings & Key Takeaways
The project demonstrates how Java classes, collections, exceptions, file handling and modular design can be combined into a practical application.

## 14. Future Enhancements
- Database integration with MySQL
- Login and role-based access
- Multi-item bills in one transaction
- GUI or web interface
- Barcode scanning
- Exportable PDF reports

## 15. References
- Oracle Java Documentation
- Java SE API documentation
- VITyarthi Build Your Own Project guidelines

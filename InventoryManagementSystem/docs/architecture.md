# System Architecture

```text
+-----------------------+
|     Main / CLI        |
+-----------+-----------+
            |
            v
+-----------------------+
|      Service Layer    |
| Inventory | Billing   |
| Reports   | Product   |
+-----------+-----------+
            |
            v
+-----------------------+
|      Model Layer      |
| Product | Bill       |
| BillItem| Category   |
+-----------+-----------+
            |
            v
+-----------------------+
|     Storage Layer     |
|      CSV Files        |
+-----------------------+
```

## Workflow

```text
Start
  |
Load saved data
  |
Display menu
  |
Choose operation
  |
Validate input
  |
Execute service operation
  |
Save changed data
  |
Show result
  |
Continue until Exit
```

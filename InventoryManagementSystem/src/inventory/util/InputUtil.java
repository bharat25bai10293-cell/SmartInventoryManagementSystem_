package inventory.util;

import inventory.model.Category;

import java.util.Scanner;

public class InputUtil {
    private final Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public String text(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be empty.");
        }
    }

    public int positiveInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) return value;
                System.out.println("Enter a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    public int nonNegativeInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= 0) return value;
                System.out.println("Value cannot be negative.");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    public double nonNegativeDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isFinite(value) && value >= 0) return value;
                System.out.println("Enter a valid non-negative number.");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    public Category category() {
        Category[] values = Category.values();
        System.out.println("Categories:");
        for (int i = 0; i < values.length; i++)
            System.out.println((i + 1) + ". " + values[i]);

        while (true) {
            int choice = nonNegativeInt("Choose category: ");
            if (choice >= 1 && choice <= values.length) return values[choice - 1];
            System.out.println("Invalid category.");
        }
    }
}

package com.expenseTracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private final String filePath = "expenses.txt"; // File to store expenses

    public ExpenseManager() {
        expenses = loadExpenses(); // Load expenses on initialization
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveExpenses(); // Save expenses after adding
    }

    public void saveExpenses() {
        try (FileWriter fw = new FileWriter(filePath, false)) { // Overwrite mode
            for (Expense expense : expenses) {
                fw.write(expense.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Expense expense = new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]);
                    expenses.add(expense);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public void removeExpense(String description) {
        expenses.removeIf(expense -> expense.getDescription().equalsIgnoreCase(description));
        saveExpenses(); // Save expenses after removal
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }
}

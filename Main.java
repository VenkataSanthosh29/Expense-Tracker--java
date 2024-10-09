package com.expenseTracker;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Remove Expense");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Date: ");
                    String date = scanner.nextLine();
                    Expense expense = new Expense(description, amount, date);
                    expenseManager.addExpense(expense);
                    break;
                case 2:
                    System.out.println("Expenses:");
                    expenseManager.viewExpenses(); // View all expenses
                    break;
                case 3:
                    System.out.print("Enter the description of the expense to remove: ");
                    String descToRemove = scanner.nextLine();
                    expenseManager.removeExpense(descToRemove);
                    System.out.println("Expense removed successfully.");
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

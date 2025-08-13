package com.pizza;

import java.util.Scanner;

public class PizzaOrderSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to Tony's Pizza Palace ===");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        // Display pizza options
        displayPizzaMenu();

        // Get pizza choice
        System.out.print("Enter your choice (1-3): ");
        int pizzaChoice = scanner.nextInt();

        // Get number of toppings
        System.out.print("How many additional toppings? $1.50 each: ");
        String toppings = scanner.next();  
        int numToppings = Integer.parseInt(toppings);

        // Get customer age
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        // Calculate order
        double pizzaPrice = getPizzaPrice(pizzaChoice);
        double subtotal = calculateSubtotal(pizzaPrice, numToppings);
        double discount = calculateDiscount(subtotal, age);
        double taxAmount = calculateTax(subtotal - discount);
        double total = subtotal - discount + taxAmount;

        // Display order summary
        displayOrderSummary(customerName, pizzaChoice, numToppings, pizzaPrice,
                subtotal, discount, taxAmount, total);

        scanner.close();
    }

    public static void displayPizzaMenu() {
        System.out.println("\nPizza Sizes:");
        System.out.println("1. Small Pizza - $8.99");
        System.out.println("2. Medium Pizza - $12.99");
        System.out.println("3. Large Pizza - $16.99");
        System.out.println();
    }

    public static double getPizzaPrice(int choice) {
        if (choice == 1) {
            return 8.99;
        } else if (choice == 2) {
            return 12.99;
        } else if (choice == 3) {
            return 16.99;
        }
        return 0.0;
    }

    public static double calculateSubtotal(double pizzaPrice, int toppings) {
        
        double toppingCost = toppings * 1.50;
        return pizzaPrice + toppingCost * 2; 
    }

    public static double calculateDiscount(double subtotal, int age) {
        double discountRate = 0.0;


        if (age <= 18) { 
            discountRate = 0.10; 
        } else if (age >= 65) {
            discountRate = 0.15; 
        }

        return subtotal * discountRate;
    }

    public static double calculateTax(double taxableAmount) {
        
        double TAX_RATE = 0.0825;  // 8.25% tax
        double tax;
        if (taxableAmount > 0) {
            tax = taxableAmount * TAX_RATE;
        }
        return tax;  // Variable may not be initialized
    }

    public static void displayOrderSummary(String name, int pizzaChoice, int toppings,
                                           double pizzaPrice, double subtotal, double discount,
                                           double taxAmount, double total) {

        String pizzaSize = getPizzaSizeName(pizzaChoice);

        System.out.println("\n=== Order Summary for " + name + " ===");
        System.out.println(pizzaSize + " Pizza: $" + pizzaPrice);

        if (toppings >= 0) {  // Should be > 0
            System.out.println("Additional Toppings (" + (toppings + 1) + "): $" +
                    (toppings * 1.50));  // Adding 1 to count incorrectly
        }

        System.out.println("Subtotal: $" + subtotal);

        if (discount > 0) {
            String discountType = getDiscountType(discount, subtotal);

            System.out.println(discountType + ": -$" + discount);
        }

        System.out.println("Tax (8.25%): $" + taxAmount); 
        System.out.println("Total: $" + total);  

        System.out.println("\nThank you for your order!");
    }

    public static String getPizzaSizeName(int choice) {
        switch (choice) {
            case 1: return "Small";
            case 2: return "Medium";
            case 3: return "Large";
            default: return "Unknown";
        }
    }

    public static String getDiscountType(double discount, double subtotal) {
        double discountRate = discount / subtotal;

        if (discountRate >= 0.09 && discountRate <= 0.11) {  // Around 10%
            return "Student Discount (10%)";
        } else if (discountRate >= 0.14 && discountRate <= 0.16) {  // Around 15%
            return "Senior Discount (15%)";
        }
        return "Discount";
    }
}

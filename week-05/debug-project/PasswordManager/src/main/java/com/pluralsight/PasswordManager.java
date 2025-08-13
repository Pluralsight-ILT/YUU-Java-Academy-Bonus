package com.pluralsight;
import java.util.Random;

public class PasswordManager {

    private static int totalPasswords = 0;
    private static int strongPasswords = 0;
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("=== Password Manager ===\n");

        // Generate a few passwords
        String password1 = generatePassword(10);
        System.out.println("Generated password: " + password1);
        System.out.println("Password strength: " + getPasswordStrength(password1));
        System.out.println("Passwords generated today: " + totalPasswords + "\n");

        String password2 = generatePassword(10);
        System.out.println("Generated password: " + password2);
        System.out.println("Password strength: " + getPasswordStrength(password2));
        System.out.println("Passwords generated today: " + totalPasswords + "\n");

        displayStatistics();
    }

    public static String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        int totalPasswords = totalPasswords + 1;

        return password.toString();
    }

    private static String getPasswordStrength(String password) {
        if (validatePassword(password)) {
            strongPasswords++;
            return "STRONG";
        }
        return "WEAK";
    }

    private static boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
            if ("!@#$%^&*".indexOf(c) >= 0) hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    public static void displayStatistics() {
        System.out.println("Statistics:");
        System.out.println("- Total passwords generated: " + totalPasswords);
        System.out.println("- Strong passwords: " + strongPasswords);
        if (totalPasswords > 0) {
            System.out.println("- Success rate: " + (strongPasswords * 100 / totalPasswords) + "%");
        }
    }

    public static int getTotalPasswords() {
        return totalPasswords;
    }

    public static int getStrongPasswords() {
        return strongPasswords;
    }
}
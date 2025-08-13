package com.pluralsight;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordManagerTest {

    public static void main(String[] args) {
        System.out.println("Running Password Manager Tests...\n");

        runAllTests();
    }

    public static void runAllTests() {
        int passed = 0;
        int total = 0;

        total++;
        if (testPasswordGeneration()) {
            System.out.println("✓ testPasswordGeneration - PASSED");
            passed++;
        } else {
            System.out.println("✗ testPasswordGeneration - FAILED");
        }

        total++;
        if (testPasswordLength()) {
            System.out.println("✓ testPasswordLength - PASSED");
            passed++;
        } else {
            System.out.println("✗ testPasswordLength - FAILED");
        }

        total++;
        if (testPasswordStrength()) {
            System.out.println("✓ testPasswordStrength - PASSED");
            passed++;
        } else {
            System.out.println("✗ testPasswordStrength - FAILED");
        }

        System.out.println("\nTests run: " + total + ", Passed: " + passed + ", Failed: " + (total - passed));
    }

    public static boolean testPasswordGeneration() {
        String password = PasswordManager.generatePassword(10);
        return password != null && password.length() == 10;
    }

    public static boolean testPasswordLength() {
        String shortPassword = PasswordManager.generatePassword(5);
        String longPassword = PasswordManager.generatePassword(15);

        return shortPassword.length() == 5 && longPassword.length() == 15;
    }

    public static boolean testPasswordStrength() {
        String strongPassword = "Test123!@#";
        String expectedStrength = "STRONG";

        String actualStrength = PasswordManager.getPasswordStrength(strongPassword);

        return expectedStrength != actualStrength;
    }
}
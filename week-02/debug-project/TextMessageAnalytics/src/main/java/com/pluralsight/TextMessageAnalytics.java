package com.pluralsight;

import java.util.Scanner;
import java.util.ArrayList;

public class TextMessageAnalytics {

    private static ArrayList<String> messages = new ArrayList<>();
    private static ArrayList<Integer> timestamps = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Text Message Analytics Tool ===");
        System.out.println("Welcome to Message Analyzer Pro!");

        boolean running = true;

        // FIXED BUG #3: Changed while (running == true) to while (running)
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMessages();
                    break;
                case 2:
                    analyzeMessages();
                    break;
                case 3:
                    displayStatistics();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    running = false;  // FIXED BUG #3: Properly set running to false to exit loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Messages");
        System.out.println("2. Analyze Messages");
        System.out.println("3. View Statistics");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    public static void addMessages() {
        System.out.print("How many messages to add? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // FIXED BUG #4: Changed i <= numMessages to i < numMessages (off-by-one error)
        for (int i = 0; i < numMessages; i++) {
            System.out.print("Message " + (i + 1) + ": ");
            String message = scanner.nextLine();

            System.out.print("Timestamp (HHMM): ");
            String timeInput = scanner.nextLine();

            // FIXED BUG #2: Changed Integer.valueOf() to Integer.parseInt()
            // and added error handling for Bug #8
            try {
                int timestamp = Integer.parseInt(timeInput);
                messages.add(message);
                timestamps.add(timestamp);
            } catch (NumberFormatException e) {
                System.out.println("Invalid timestamp format. Please enter HHMM (e.g., 1430).");
                i--; // Retry this iteration
            }
        }

        System.out.println("Messages added successfully!");
    }

    public static void analyzeMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages to analyze. Please add some messages first.");
            return;
        }

        System.out.println("\n=== Message Analysis ===");

        // FIXED BUG #7: Removed incorrect counter logic and missing semicolon
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            int timestamp = timestamps.get(i);

            String formattedTime = formatTime(timestamp);
            int wordCount = countWords(message);
            int charCount = message.length();

            System.out.println("[" + formattedTime + "] \"" + message + "\" - " +
                    wordCount + " words, " + charCount + " chars");
        }
    }

    // FIXED BUG #1: Changed return type from int to double
    public static double calculateAverageWords() {
        if (messages.isEmpty()) {
            return 0.0;
        }

        int totalWords = 0;
        for (String message : messages) {
            totalWords += countWords(message);
        }

        return totalWords / (double) messages.size();
    }

    public static int countWords(String message) {
        if (message == null || message.trim().equals("")) {
            return 0;
        }

        // FIXED BUG #6: Added trim() before split to handle extra spaces properly
        String[] words = message.trim().toLowerCase().split("\\s+");
        return words.length;
    }

    public static void displayStatistics() {
        if (messages.isEmpty()) {
            System.out.println("No messages to analyze. Please add some messages first.");
            return;
        }

        System.out.println("\n=== Message Analytics Report ===");
        System.out.println("Total Messages: " + messages.size());

        double avgWords = calculateAverageWords();

        // FIXED BUG #5: Moved totalChars declaration to proper scope
        int totalChars = 0;
        for (String message : messages) {
            totalChars += message.length();
        }

        System.out.println("Average Words per Message: " + avgWords);
        System.out.println("Total Characters: " + totalChars);

        int positiveCount = countPositiveMessages();
        System.out.println("Positive Messages: " + positiveCount);

        int mostActiveHour = findMostActiveHour();
        System.out.println("Most Active Hour: " + mostActiveHour + " (" +
                formatHour(mostActiveHour) + ")");
    }

    public static int countPositiveMessages() {
        String[] positiveWords = {"great", "awesome", "good", "excellent", "fantastic",
                "wonderful", "amazing", "love", "happy", "perfect"};

        int count = 0;
        for (String message : messages) {
            String lowerMessage = message.toLowerCase();
            for (String positiveWord : positiveWords) {
                if (lowerMessage.contains(positiveWord)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static int findMostActiveHour() {
        if (timestamps.isEmpty()) {
            return 0;
        }

        // FIXED: Better implementation to find actual most active hour
        int[] hourCounts = new int[24];

        // Count messages per hour
        for (int timestamp : timestamps) {
            int hour = timestamp / 100;
            if (hour >= 0 && hour < 24) {
                hourCounts[hour]++;
            }
        }

        // Find hour with most messages
        int mostActiveHour = 0;
        int maxCount = hourCounts[0];
        for (int i = 1; i < 24; i++) {
            if (hourCounts[i] > maxCount) {
                maxCount = hourCounts[i];
                mostActiveHour = i;
            }
        }

        return mostActiveHour;
    }

    public static String formatTime(int timestamp) {
        int hour = timestamp / 100;
        int minute = timestamp % 100;

        return String.format("%02d:%02d", hour, minute);
    }

    public static String formatHour(int hour) {
        if (hour == 0) {
            return "12:00 AM";
        } else if (hour < 12) {
            return hour + ":00 AM";
        } else if (hour == 12) {
            return "12:00 PM";
        } else {
            return (hour - 12) + ":00 PM";
        }
    }
}
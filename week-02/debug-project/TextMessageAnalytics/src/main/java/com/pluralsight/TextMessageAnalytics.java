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
        
        while (running == true) {
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
        
        for (int i = 0; i <= numMessages; i++) {  
            System.out.print("Message " + (i + 1) + ": ");
            String message = scanner.nextLine();
            
            System.out.print("Timestamp (HHMM): ");
            String timeInput = scanner.nextLine();
            
            int timestamp = Integer.valueOf(timeInput);  
            
            messages.add(message);
            timestamps.add(timestamp);
        }
        
        System.out.println("Messages added successfully!");
    }
    
    public static void analyzeMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages to analyze. Please add some messages first.");
            return;
        }
        
        System.out.println("\n=== Message Analysis ===");

        int messageCount = 1; 
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            int timestamp = timestamps.get(i);
            
            String formattedTime = formatTime(timestamp);
            int wordCount = countWords(message);
            int charCount = message.length();
            
            System.out.println("[" + formattedTime + "] \"" + message + "\" - " + 
                             wordCount + " words, " + charCount + " chars");
            
            messageCount += 2;  
        }
    }
    

    public static int calculateAverageWords() { 
        if (messages.isEmpty()) {
            return 0;
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
        
        String[] words = message.toLowerCase().split(" ");  
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
        int totalChars = 0;
        for (String message : messages) {
            int messageChars = message.length();
            totalChars += messageChars;
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
        
        int firstTimestamp = timestamps.get(0);
        return firstTimestamp / 100;  // Extract hour from HHMM format
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
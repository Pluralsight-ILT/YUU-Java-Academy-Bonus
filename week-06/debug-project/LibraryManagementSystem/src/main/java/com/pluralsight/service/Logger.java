package com.pluralsight.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "logs/library.log";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static Logger instance;
    private PrintWriter logWriter;

    private Logger() {
        try {
            // Create logs directory if it doesn't exist
            java.io.File logDir = new java.io.File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // Initialize log writer with append mode
            logWriter = new PrintWriter(new FileWriter(LOG_FILE, true));
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private void log(String level, String message) {
        if (logWriter != null) {
            String timestamp = LocalDateTime.now().format(DATE_FORMAT);
            String logEntry = String.format("[%s] %s - %s", timestamp, level, message);
            logWriter.println(logEntry);
            logWriter.flush(); // Ensure immediate write to file
        }
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void error(String message) {
        log("ERROR", message);
        System.err.println("ERROR: " + message); // Also print to console
    }

    public void error(String message, Exception e) {
        log("ERROR", message + " - " + e.getMessage());
        System.err.println("ERROR: " + message + " - " + e.getMessage());
        if (logWriter != null) {
            e.printStackTrace(logWriter);
            logWriter.flush();
        }
    }

    public void warn(String message) {
        log("WARN", message);
    }

    public void debug(String message) {
        log("DEBUG", message);
    }

    public void close() {
        if (logWriter != null) {
            logWriter.close();
        }
    }

    // Add shutdown hook to close logger properly
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (instance != null) {
                instance.close();
            }
        }));
    }
}
package com.pluralsight;

import java.io.*;
import java.util.*;

public class RegistrationSystem {
    private static HashMap<String, Student> students = new HashMap<>();
    private static HashMap<String, Course> courses = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Student Course Registration System ===");

        loadData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerNewStudent();
                    break;
                case 2:
                    enrollInCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewStudentSchedule();
                    break;
                case 5:
                    viewCourseEnrollment();
                    break;
                case 6:
                    saveData();
                    System.out.println("Data saved. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Register New Student");
        System.out.println("2. Enroll in Course");
        System.out.println("3. Drop Course");
        System.out.println("4. View Student Schedule");
        System.out.println("5. View Course Enrollment");
        System.out.println("6. Save and Exit");
        System.out.print("Enter choice: ");
    }

    public static void loadData() {
        loadStudents();
        loadCourses();
        System.out.println("Loaded " + students.size() + " students and " + courses.size() + " courses.");
    }

    public static void loadStudents() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/students.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3]);
                    students.put(parts[0], student);
                }
            }

        } catch (IOException e) {
            System.out.println("Could not load students file. Starting with empty student list.");
        }
    }

    public static void loadCourses() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/courses.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String courseCode = parts[0];
                    String title = parts[1];
                    String instructor = parts[2];
                    int maxEnrollment = Integer.parseInt(parts[3]);

                    Course course = new Course(courseCode, title, instructor, maxEnrollment);
                    courses.put(courseCode, course);
                }
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Could not load courses file. Starting with empty course list.");
        }
    }

    public static void registerNewStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            System.out.println("Student ID already exists.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter major: ");
        String major = scanner.nextLine();

        Student student = new Student(studentId, name, email, major);
        students.put(studentId, student);
        System.out.println("Student registered successfully!");
    }

    public static void enrollInCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = students.get(studentId);
        Course course = findCourse(courseCode);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.enrollStudent(student)) {
            System.out.println("Enrollment successful!");
            System.out.println("Student " + student.getName() + " enrolled in " + course.getTitle());
            System.out.println("Remaining spots: " + (course.getMaxEnrollment() - course.getCurrentEnrollment()) + "/" + course.getMaxEnrollment());
        } else {
            System.out.println("Enrollment failed. Course may be full or student already enrolled.");
        }
    }

    public static void dropCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student == null || course == null) {
            System.out.println("Student or course not found.");
            return;
        }

        if (course.dropStudent(student)) {
            System.out.println("Successfully dropped " + student.getName() + " from " + course.getTitle());
        } else {
            System.out.println("Drop failed. Student may not be enrolled in this course.");
        }
    }

    public static Course findCourse(String courseCode) {
        return courses.get(courseCode.toUpperCase());
    }

    public static void viewStudentSchedule() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\n=== Schedule for " + student.getName() + " ===");
        ArrayList<String> enrolledCourses = student.getEnrolledCourses();

        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
            return;
        }

        for (String courseCode : enrolledCourses) {
            Course course = courses.get(courseCode);
            if (course != null) {
                System.out.println(course.getCourseCode() + " - " + course.getTitle() + " (" + course.getInstructor() + ")");
            }
        }
    }

    public static void viewCourseEnrollment() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        course.displayEnrollment();
    }

    public static void saveData() {
        saveStudents();
        saveCourses();
    }

    public static void saveStudents() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/students.csv"));

            for (Student student : students.values()) {
                writer.write(student.toString());
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving students file: " + e.getMessage());
        }
    }

    public static void saveCourses() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/courses.csv"));

            for (Course course : courses.values()) {
                writer.write(course.toString());
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving courses file: " + e.getMessage());
        }
    }
}

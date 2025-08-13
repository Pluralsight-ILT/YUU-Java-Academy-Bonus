package com.pluralsight;

import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String title;
    private String instructor;
    private int maxEnrollment;
    private ArrayList<Student> enrolledStudents;

    public Course(String courseCode, String title, String instructor, int maxEnrollment) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
        this.maxEnrollment = maxEnrollment;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public int getCurrentEnrollment() {
        return enrolledStudents.size();
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean hasSpace() {
        return enrolledStudents.size() < maxEnrollment;
    }

    public boolean enrollStudent(Student student) {
        if (hasSpace() && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            student.enrollInCourse(courseCode);
            return true;
        }
        return false;
    }

    public boolean dropStudent(Student student) {
        for (int i = 0; i < enrolledStudents.size(); i++) {
            if (enrolledStudents.get(i).equals(student)) {
                enrolledStudents.remove(i);
                student.dropCourse(courseCode);
                return true;
            }
        }
        return false;
    }

    public boolean isStudentEnrolled(Student student) {
        return enrolledStudents.contains(student);
    }

    public void displayEnrollment() {
        System.out.println("Course: " + courseCode + " - " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrollment: " + getCurrentEnrollment() + "/" + maxEnrollment);
        System.out.println("Enrolled Students:");

        for (Student student : enrolledStudents) {
            System.out.println("  " + student.getStudentId() + " - " + student.getName());
        }
    }

    @Override
    public String toString() {
        return courseCode + "," + title + "," + instructor + "," + maxEnrollment + "," + getCurrentEnrollment();
    }

    public String getDisplayInfo() {
        int availableSpots = maxEnrollment - getCurrentEnrollment();
        return "Course Code: " + courseCode +
                "\nTitle: " + title +
                "\nInstructor: " + instructor +
                "\nEnrollment: " + getCurrentEnrollment() + "/" + maxEnrollment +
                "\nAvailable Spots: " + availableSpots;
    }
}
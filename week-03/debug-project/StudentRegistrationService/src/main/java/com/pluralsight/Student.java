package com.pluralsight;

import java.util.ArrayList;
import java.util.Objects;

public class Student {
    private String studentId;
    private String name;
    private String email;
    private String major;
    private ArrayList<String> enrolledCourses;

    public Student(String studentId, String name, String email, String major) {
        this.studentId = studentId;
        this.name = email;
        this.email = name;
        this.major = major;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public boolean enrollInCourse(String courseCode) {
        if (!enrolledCourses.contains(courseCode)) {
            enrolledCourses.add(courseCode);
            return true;
        }
        return false;
    }

    public boolean dropCourse(String courseCode) {
        return enrolledCourses.remove(courseCode);
    }

    public boolean isEnrolledIn(String courseCode) {
        return enrolledCourses.contains(courseCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public String toString() {
        return studentId + "," + name + "," + email + "," + major;
    }

    public String getDisplayInfo() {
        return "Student ID: " + studentId +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nMajor: " + major +
                "\nEnrolled Courses: " + enrolledCourses.size();
    }
}
package com.project;

import java.util.Scanner;
import java.util.List;

public class Main {
    private static final String DATA_FILE = "students_data.txt";

    public static void main(String[] args) {
        StudentManagement mgr = new StudentManagement(DATA_FILE);
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addStudentFlow(sc, mgr);
                    break;
                case "2":
                    displayAll(mgr);
                    break;
                case "3":
                    searchFlow(sc, mgr);
                    break;
                case "4":
                    updateFlow(sc, mgr);
                    break;
                case "5":
                    deleteFlow(sc, mgr);
                    break;
                case "6":
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println(); // spacer
        }
    }

    private static void printMenu() {
        System.out.println("===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student by Roll Number");
        System.out.println("4. Update Student (Dept / Marks)");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudentFlow(Scanner sc, StudentManagement mgr) {
        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine().trim();
        if (mgr.searchByRoll(roll) != null) {
            System.out.println("A student with this roll number already exists.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine().trim();
        System.out.print("Enter Marks (0-100): ");
        double marks = parseDoubleSafe(sc.nextLine().trim(), 0.0);
        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        Student s = new Student(roll, name, dept, marks, email);
        mgr.addStudent(s);
        System.out.println("Student added successfully.");
    }

    private static void displayAll(StudentManagement mgr) {
        List<Student> all = mgr.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("--- All Students ---");
        for (Student s : all) System.out.println(s);
    }

    private static void searchFlow(Scanner sc, StudentManagement mgr) {
        System.out.print("Enter Roll Number to search: ");
        String roll = sc.nextLine().trim();
        Student s = mgr.searchByRoll(roll);
        if (s == null) System.out.println("Student not found.");
        else System.out.println(s);
    }

    private static void updateFlow(Scanner sc, StudentManagement mgr) {
        System.out.print("Enter Roll Number to update: ");
        String roll = sc.nextLine().trim();
        Student s = mgr.searchByRoll(roll);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Leave input blank to keep current value.");
        System.out.printf("Current Department: %s\n", s.getDepartment());
        System.out.print("New Department: ");
        String dept = sc.nextLine().trim();
        if (dept.isEmpty()) dept = null;

        System.out.printf("Current Marks: %.2f\n", s.getMarks());
        System.out.print("New Marks: ");
        String marksIn = sc.nextLine().trim();
        Double marks = marksIn.isEmpty() ? null : parseDoubleSafe(marksIn, s.getMarks());

        boolean ok = mgr.updateStudent(roll, dept, marks);
        System.out.println(ok ? "Updated successfully." : "Update failed.");
    }

    private static void deleteFlow(Scanner sc, StudentManagement mgr) {
        System.out.print("Enter Roll Number to delete: ");
        String roll = sc.nextLine().trim();
        boolean ok = mgr.deleteStudent(roll);
        System.out.println(ok ? "Deleted successfully." : "Delete failed (not found)."");
    }

    private static double parseDoubleSafe(String s, double fallback) {
        try { return Double.parseDouble(s); } catch (Exception e) { return fallback; }
    }
}

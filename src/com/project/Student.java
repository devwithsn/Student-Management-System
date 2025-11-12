package com.project;

public class Student {
    private String rollNo;
    private String name;
    private String department;
    private double marks;
    private String email;

    public Student(String rollNo, String name, String department, double marks, String email) {
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.marks = marks;
        this.email = email;
    }

    // Getters and setters
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // CSV helpers for file storage
    public String toCSV() {
        // rollNo,name,department,marks,email
        return String.format("%s,%s,%s,%.2f,%s", escape(rollNo), escape(name), escape(department), marks, escape(email));
    }

    public static Student fromCSV(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length < 5) return null;
        String rollNo = unescape(parts[0]);
        String name = unescape(parts[1]);
        String department = unescape(parts[2]);
        double marks = 0.0;
        try { marks = Double.parseDouble(parts[3]); } catch (NumberFormatException ignored) {}
        String email = unescape(parts[4]);
        return new Student(rollNo, name, department, marks, email);
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace(",", "\;"); // simple escape for commas
    }

    private static String unescape(String s) {
        if (s == null) return "";
        return s.replace("\;", ",").replace("\\\\", "\\");
    }

    @Override
    public String toString() {
        return String.format("Roll: %s | Name: %s | Dept: %s | Marks: %.2f | Email: %s", rollNo, name, department, marks, email);
    }
}

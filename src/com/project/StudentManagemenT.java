package com.project;

import java.io.*;
import java.util.*;

public class StudentManagement {
    private ArrayList<Student> students;
    private final File dataFile;

    public StudentManagement(String filename) {
        this.dataFile = new File(filename);
        this.students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
    }

    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(students);
    }

    public Student searchByRoll(String roll) {
        for (Student s : students) {
            if (s.getRollNo().equalsIgnoreCase(roll)) return s;
        }
        return null;
    }

    public boolean updateStudent(String roll, String department, Double marks) {
        Student s = searchByRoll(roll);
        if (s == null) return false;
        if (department != null) s.setDepartment(department);
        if (marks != null) s.setMarks(marks);
        saveToFile();
        return true;
    }

    public boolean deleteStudent(String roll) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getRollNo().equalsIgnoreCase(roll)) {
                it.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    private void loadFromFile() {
        students.clear();
        if (!dataFile.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromCSV(line);
                if (s != null) students.add(s);
            }
        } catch (IOException e) {
            System.err.println("Failed to load data: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(dataFile))) {
            for (Student s : students) pw.println(s.toCSV());
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }
}

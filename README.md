# Student Management System (Console-based) 

**A beginner-friendly Java project** suitable for showcasing basic OOP, collections, file I/O, and exception handling.  
Designed for a 2nd-year B.Tech student preparing a portfolio for job applications (e.g., Cognizant).

## Features
- Add / view / search / update / delete student records
- Persist data to a text file (`students_data.txt`)
- Simple console-based menu interface

## Tech
- Java (no external libraries)
- Package: `com.project`

## How to run
1. Open terminal in the project root where `src/` is located.
2. Compile:
   ```bash
   javac -d out src/com/project/*.java
   ```
3. Run:
   ```bash
   java -cp out com.project.Main
   ```

Data is stored in `students_data.txt` in the project root.

## Files
- `src/com/project/Student.java`
- `src/com/project/StudentManagement.java`
- `src/com/project/Main.java`
- `students_data.txt` (sample)
- `README.md`
- `.gitignore`

## Notes
- The CSV escaping is simple (commas in fields are converted) — enough for portfolio/demo purposes.
- You can improve further by adding sorting, JSON storage, or a GUI (Swing/JavaFX).

Good luck with your application! If you want, I can:
- Create a GitHub-ready README with badges and a sample screenshot.
- Convert storage to JSON or SQLite.

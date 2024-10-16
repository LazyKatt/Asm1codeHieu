import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        if (marks >= 9.0) return "Excellent";
        else if (marks >= 7.5) return "Very Good";
        else if (marks >= 6.5) return "Good";
        else if (marks >= 5.0) return "Medium";
        else return "Fail";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }
}

class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Helper method for integer input validation
    private int getValidatedInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Helper method for double input validation
    private double getValidatedDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                if (value < 0 || value > 10) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                } else {
                    return value;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // New method to get a validated student ID, checking for duplicates
    private int getStudentId() {
        while (true) {
            int id = getValidatedInt("Enter student ID: "); // Get validated ID
            if (id == -1) { // Check for invalid input
                return -1; // Return -1 to indicate an error
            }
            // Check if the ID already exists
            if (searchStudentById(id) != null) {
                System.out.println("A student with this ID already exists. Please use a different ID.");
            } else {
                return id; // Return the valid, unique ID
            }
        }
    }

    public void addStudent() {
        int id = getStudentId();
        scanner.nextLine(); // Consume newline
        // Check if the ID already exists
        if (searchStudentById(id) != null) {
            System.out.println("A student with this ID already exists. Please use a different ID.");
            return; // Exit the method to avoid adding a duplicate student
        }
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        double marks = getValidatedDouble("Enter student marks (0.0 - 10.0): ");

        Student student = new Student(id, name, marks);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void editStudent() {
        int id = getValidatedInt("Enter student ID to edit: ");
        Student student = searchStudentById(id);
        if (student != null) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            double marks = getValidatedDouble("Enter new marks (0.0 - 10.0): ");
            student.setName(name);
            student.setMarks(marks);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent() {
        int id = getValidatedInt("Enter student ID to delete: ");
        Student student = searchStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void sortStudentsByMarks() {
        Collections.sort(students, Comparator.comparingDouble(Student::getMarks).reversed());
        System.out.println("Students sorted by marks.");
    }

    public void searchStudent() {
        int id = getValidatedInt("Enter student ID to search: ");
        Student student = searchStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            clearScreen();
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");
            choice = getValidatedInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    manager.addStudent();
                    break;
                case 2:
                    manager.editStudent();
                    break;
                case 3:
                    manager.deleteStudent();
                    break;
                case 4:
                    manager.searchStudent();
                    break;
                case 5:
                    manager.sortStudentsByMarks();
                    manager.displayStudents();
                    break;
                case 6:
                    manager.displayStudents();
                    break;
                case 7:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (choice != 7) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Consume newline
                scanner.nextLine(); // Wait for Enter key press
            }
        } while (choice != 7);

        scanner.close();
    }

    public static int getValidatedInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Could not clear screen.");
        }
    }
}

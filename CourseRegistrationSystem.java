import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    ArrayList<String> registeredStudents = new ArrayList<>();

    Course(String code, String title, String desc, int capacity) {
        this.courseCode = code;
        this.title = title;
        this.description = desc;
        this.capacity = capacity;
    }

    boolean isAvailable() {
        return registeredStudents.size() < capacity;
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<String> registeredCourses = new ArrayList<>();

    Student(String id, String name) {
        this.studentID = id;
        this.name = name;
    }
}

public class CourseRegistrationSystem {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, Course> courseDB = new HashMap<>();
    static HashMap<String, Student> studentDB = new HashMap<>();

    public static void main(String[] args) {
        // Add some sample courses
        courseDB.put("CS101", new Course("CS101", "Java Programming", "Learn Java Basics", 3));
        courseDB.put("MATH201", new Course("MATH201", "Calculus", "Advanced Mathematics", 2));
        courseDB.put("ENG105", new Course("ENG105", "English Literature", "Study of classic literature", 2));

        while (true) {
            System.out.println("\n----- Course Registration System -----");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register New Student");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: displayCourses(); break;
                case 2: registerStudent(); break;
                case 3: registerCourse(); break;
                case 4: dropCourse(); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDB.values()) {
            System.out.println("Course Code: " + course.courseCode);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Registered: " + course.registeredStudents.size());
            System.out.println("Available Slots: " + (course.capacity - course.registeredStudents.size()));
            System.out.println("---------------------------");
        }
    }

    static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.next();
        System.out.print("Enter Student Name: ");
        String name = sc.next();

        if (studentDB.containsKey(id)) {
            System.out.println("Student already registered!");
        } else {
            studentDB.put(id, new Student(id, name));
            System.out.println("Student registered successfully.");
        }
    }

    static void registerCourse() {
        System.out.print("Enter Student ID: ");
        String id = sc.next();

        if (!studentDB.containsKey(id)) {
            System.out.println("Student not found! Please register first.");
            return;
        }

        System.out.print("Enter Course Code to register: ");
        String code = sc.next();

        if (!courseDB.containsKey(code)) {
            System.out.println("Course not found!");
            return;
        }

        Course course = courseDB.get(code);
        Student student = studentDB.get(id);

        if (!course.isAvailable()) {
            System.out.println("Course is full.");
            return;
        }

        if (student.registeredCourses.contains(code)) {
            System.out.println("Already registered for this course.");
            return;
        }

        course.registeredStudents.add(id);
        student.registeredCourses.add(code);
        System.out.println("Course registered successfully.");
    }

    static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String id = sc.next();

        if (!studentDB.containsKey(id)) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course Code to drop: ");
        String code = sc.next();

        Student student = studentDB.get(id);
        Course course = courseDB.get(code);

        if (!student.registeredCourses.contains(code)) {
            System.out.println("Student is not registered for this course.");
            return;
        }

        student.registeredCourses.remove(code);
        course.registeredStudents.remove(id);
        System.out.println("Course dropped successfully.");
    }
}

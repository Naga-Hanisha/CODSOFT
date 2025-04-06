import java.util.Scanner;

public class StudentMarksCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Ask the user for the number of subjects
        System.out.print("Enter the number of subjects: ");
        int subjects = sc.nextInt();

        int[] marks = new int[subjects];
        int totalMarks = 0;

        // Input: Take marks for each subject
        for (int i = 0; i < subjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            marks[i] = sc.nextInt();
            totalMarks += marks[i];
        }

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / subjects;

        // Grade Calculation
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F'; // Fail
        }

        // Display Results
        System.out.println("\n----- Result -----");
        System.out.println("Total Marks = " + totalMarks);
        System.out.println("Average Percentage = " + averagePercentage + "%");
        System.out.println("Grade = " + grade);
    }
}

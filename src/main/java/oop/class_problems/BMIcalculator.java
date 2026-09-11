package main.java.oop.class_problems;

import java.util.Scanner;

public class BMIcalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    static void printWellnessReport(double[] heights, double[] weights) {
        System.out.printf("%-10s %-15s %-15s %-15s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI | Status");
        System.out.println("-------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);

            System.out.printf("%-10d %-15.2f %-15.2f %.2f | %s%n",
                    i + 1, heights[i], weights[i], bmi, status);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of people: ");
        int n = scanner.nextInt();

        double[] heights = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter height of Person " + (i + 1) + " in meters: ");
            heights[i] = scanner.nextDouble();

            System.out.print("Enter weight of Person " + (i + 1) + " in kg: ");
            weights[i] = scanner.nextDouble();
        }

        printWellnessReport(heights, weights);

        scanner.close();
    }
}
package main.java.oop.class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    static boolean isPalindromeIterative(String text) {
        text = text.toLowerCase();

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    static boolean isPalindromeRecursive(String text) {
        text = text.toLowerCase();
        return isPalindromeRecursiveHelper(text, 0, text.length() - 1);
    }

    static boolean isPalindromeRecursiveHelper(String text, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return isPalindromeRecursiveHelper(text, left + 1, right - 1);
    }

    static boolean isPalindromeArrayReversal(String text) {
        text = text.toLowerCase();

        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        return new String(original).equals(new String(reversed));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or phrase: ");
        String text = scanner.nextLine();

        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean arrayReversal = isPalindromeArrayReversal(text);

        System.out.println(
                "Iterative: " + (iterative ? "Palindrome" : "Not Palindrome")
        );

        System.out.println(
                "Recursive: " + (recursive ? "Palindrome" : "Not Palindrome")
        );

        System.out.println(
                "Array Reversal: " + (arrayReversal ? "Palindrome" : "Not Palindrome")
        );

        scanner.close();
    }
}

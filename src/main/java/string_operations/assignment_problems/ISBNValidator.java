package main.java.string_operations.assignment_problems;

import java.util.Scanner;

public class ISBNValidator {

    static String normalizeCode(String raw) {
        return raw.trim();
    }

    static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        String publisher = code.substring(0, 3);

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(publisher.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: ISBN body must contain digits only";
            }
        }

        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        return "[" + publisher.toUpperCase()
                + "] YEAR: " + year
                + " | CATALOG: " + catalog;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ISBN code: ");
        String raw = scanner.nextLine();

        String code = normalizeCode(raw);

        System.out.println(validateAndFormat(code));

        scanner.close();
    }
}

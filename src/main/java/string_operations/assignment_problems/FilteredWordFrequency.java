package main.java.string_operations.assignment_problems;

import java.util.Scanner;

public class FilteredWordFrequency {

    static void printFilteredWordFrequency(String feedback) {
        feedback = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "");

        String[] words = feedback.split("\\s+");

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        String[] uniqueWords = new String[words.length];
        int[] frequencies = new int[words.length];
        int count = 0;

        for (String word : words) {
            boolean isStopWord = false;

            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            if (isStopWord) {
                continue;
            }

            int index = -1;

            for (int i = 0; i < count; i++) {
                if (uniqueWords[i].equals(word)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                uniqueWords[count] = word;
                frequencies[count] = 1;
                count++;
            } else {
                frequencies[index]++;
            }
        }

        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (frequencies[j] > frequencies[i]) {
                    int tempFrequency = frequencies[i];
                    frequencies[i] = frequencies[j];
                    frequencies[j] = tempFrequency;

                    String tempWord = uniqueWords[i];
                    uniqueWords[i] = uniqueWords[j];
                    uniqueWords[j] = tempWord;
                }
            }
        }

        for (int i = 0; i < count; i++) {
            System.out.println(uniqueWords[i] + ": " + frequencies[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String feedback = scanner.nextLine();

        printFilteredWordFrequency(feedback);

        scanner.close();
    }
}

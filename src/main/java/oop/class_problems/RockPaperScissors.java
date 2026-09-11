package main.java.oop.class_problems;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {
        playerMove = playerMove.trim().toLowerCase();
        computerMove = computerMove.trim().toLowerCase();

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("rock") && computerMove.equals("scissors"))
                || (playerMove.equals("paper") && computerMove.equals("rock"))
                || (playerMove.equals("scissors") && computerMove.equals("paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        int rounds = 5;

        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < rounds; i++) {

            System.out.print("Enter your move (Rock/Paper/Scissors): ");
            String playerMove = scanner.nextLine();

            int randomIndex = random.nextInt(3);
            String computerMove = moves[randomIndex];

            String result = playRound(playerMove, computerMove);

            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println("Computer Move: " + computerMove);
            System.out.println("Result: " + result);
            System.out.println();
        }

        double winPercentage = (wins * 100.0) / rounds;

        System.out.println("------------------------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s%n",
                "Round", "Player Move", "Computer Move", "Result");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < rounds; i++) {
            System.out.printf("%-8d %-15s %-15s %-15s%n",
                    i + 1,
                    playerMoves[i],
                    computerMoves[i],
                    results[i]);
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Wins: " + wins
                + " | Losses: " + losses
                + " | Draws: " + draws
                + " | Win % = " + winPercentage + "%");

        scanner.close();
    }
}

package main.java.constructors.class_problems;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Minutes late cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        int firstBracket = Math.min(minutesLate, 5);
        int secondBracket = Math.max(0, Math.min(minutesLate - 5, 10));
        int thirdBracket = Math.max(0, minutesLate - 15);

        double penalty = ticketFare * firstBracket * 0.005;
        penalty += ticketFare * secondBracket * 0.01;
        penalty += ticketFare * thirdBracket * 0.02;

        double minimumPenalty = ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(penalty, minimumPenalty);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calculator = new BoardingPenaltyCalculator(1.0);

        System.out.println("Rs " + calculator.calculatePenalty(1000, 0));
        System.out.println("Rs " + calculator.calculatePenalty(1000, 1));
        System.out.println("Rs " + calculator.calculatePenalty(1000, 16));
    }
}

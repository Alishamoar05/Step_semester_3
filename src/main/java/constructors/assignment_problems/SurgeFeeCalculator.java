package main.java.constructors.assignment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        int firstBracket = Math.min(delayMinutes, 5);
        int secondBracket = Math.max(0, Math.min(delayMinutes - 5, 10));
        int thirdBracket = Math.max(0, delayMinutes - 15);

        double fee = orderValue * firstBracket * 0.005;
        fee += orderValue * secondBracket * 0.01;
        fee += orderValue * thirdBracket * 0.015;

        double minimumFee = orderValue * minimumSurgePercent / 100.0;

        return Math.max(fee, minimumFee);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0);

        System.out.println("Rs " + calculator.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calculator.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calculator.calculateSurgeFee(500, 16));
    }
}
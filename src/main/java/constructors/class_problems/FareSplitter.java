package main.java.constructors.class_problems;

public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0) {
            throw new IllegalArgumentException("Invalid fare or passenger count");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 3);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 3);
    }

    public double[] fareBreakdown() {
        double share = Math.floor((totalFare / passengerCount) * 100) / 100;
        double remainder = Math.round((totalFare - share * passengerCount) * 100) / 100;

        double[] result = new double[passengerCount];

        for (int i = 0; i < passengerCount; i++) {
            result[i] = share;
        }

        if (passengerCount > 0) {
            result[passengerCount - 1] = Math.round((share + remainder) * 100) / 100;
        }

        return result;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter fare1 = new FareSplitter("TRIP001", 100000, 3);
        double[] result1 = fare1.fareBreakdown();

        for (double fare : result1) {
            System.out.print(fare + " ");
        }

        System.out.println();

        FareSplitter fare2 = new FareSplitter("TRIP003");
        double[] result2 = fare2.fareBreakdown();

        for (double fare : result2) {
            System.out.print(fare + " ");
        }

        System.out.println();
        System.out.println(fare1.isConfirmationOverdue(2, 3));
    }
}

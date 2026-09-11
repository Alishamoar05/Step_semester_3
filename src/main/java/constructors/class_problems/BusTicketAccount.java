package main.java.constructors.class_problems;

public class BusTicketAccount {

    private static int totalAccountsProcessed;
    private static double grandTotalPenalty;

    private final String bookingId;
    private final double ticketFare;

    static {
        totalAccountsProcessed = 0;
        grandTotalPenalty = 0.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Minutes late cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        int firstBracket = Math.min(minutesLate, 5);
        int secondBracket = Math.max(0, Math.min(minutesLate - 5, 10));
        int thirdBracket = Math.max(0, minutesLate - 15);

        return ticketFare * firstBracket * 0.005
                + ticketFare * secondBracket * 0.01
                + ticketFare * thirdBracket * 0.02;
    }

    public static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            return;
        }

        try {
            if (amount < 0) {
                throw new IllegalArgumentException();
            }

            double penalty = account.calculatePenalty(minutesLate);

            totalAccountsProcessed++;
            grandTotalPenalty += penalty;
        } catch (Exception e) {
        }
    }

    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        int processed = 0;
        int skipped = 0;
        int sleeper = 0;
        int regular = 0;

        double totalPenalty = 0.0;

        if (accounts == null || amounts == null || minutesLateArray == null) {
            return;
        }

        int length = Math.min(accounts.length,
                Math.min(amounts.length, minutesLateArray.length));

        for (int i = 0; i < length; i++) {
            if (accounts[i] == null) {
                skipped++;
                continue;
            }

            try {
                if (amounts[i] < 0) {
                    skipped++;
                    continue;
                }

                double penalty = accounts[i].calculatePenalty(minutesLateArray[i]);

                processed++;
                totalPenalty += penalty;

                if (accounts[i].ticketFare == 2000) {
                    sleeper++;
                } else {
                    regular++;
                }
            } catch (Exception e) {
                skipped++;
            }
        }

        totalAccountsProcessed += processed;
        grandTotalPenalty += totalPenalty;

        System.out.println(
                processed + " processed | "
                + skipped + " null skipped | "
                + sleeper + " sleeper | "
                + regular + " regular | "
                + "grand total penalties = " + grandTotalPenalty
        );
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new BusTicketAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}

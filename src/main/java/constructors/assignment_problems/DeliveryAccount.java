package main.java.constructors.assignment_problems;

public class DeliveryAccount {

    private static int totalAccountsProcessed;
    private static double grandTotalSurgeFees;

    private final String studentId;
    private final double orderValue;

    static {
        totalAccountsProcessed = 0;
        grandTotalSurgeFees = 0.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        int firstBracket = Math.min(delayMinutes, 5);
        int secondBracket = Math.max(0, Math.min(delayMinutes - 5, 10));
        int thirdBracket = Math.max(0, delayMinutes - 15);

        return orderValue * firstBracket * 0.005
                + orderValue * secondBracket * 0.01
                + orderValue * thirdBracket * 0.015;
    }

    public static class Premium extends DeliveryAccount {

        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public Premium(String studentId) {
            super(studentId);
        }
    }

    public static void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            return;
        }

        try {
            if (amount < 0) {
                throw new IllegalArgumentException();
            }

            double surgeFee = account.calculateSurgeFee(delayMinutes);

            if (account instanceof Premium) {
                surgeFee *= 0.5;
            }

            totalAccountsProcessed++;
            grandTotalSurgeFees += surgeFee;

        } catch (Exception e) {
        }
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        if (accounts == null || amounts == null || delayMinutesArray == null) {
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double batchTotal = 0.0;

        int length = Math.min(
                accounts.length,
                Math.min(amounts.length, delayMinutesArray.length)
        );

        for (int i = 0; i < length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            try {
                if (amounts[i] < 0) {
                    continue;
                }

                double surgeFee = accounts[i].calculateSurgeFee(delayMinutesArray[i]);

                if (accounts[i] instanceof Premium) {
                    surgeFee *= 0.5;
                    premium++;
                } else {
                    regular++;
                }

                processed++;
                batchTotal += surgeFee;

            } catch (Exception e) {
            }
        }

        totalAccountsProcessed += processed;
        grandTotalSurgeFees += batchTotal;

        System.out.println(
                processed + " processed | "
                + nullSkipped + " null skipped | "
                + premium + " premium | "
                + regular + " regular | "
                + "grand total surge fees = " + grandTotalSurgeFees
        );
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}

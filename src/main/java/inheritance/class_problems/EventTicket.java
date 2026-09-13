package main.java.inheritance.class_problems;

public class EventTicket {
    public final String ticketId;
    private final double basePrice;
    private double balanceDue;
    private static int ticketsIssued = 0;

    public EventTicket(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException();
        }

        ticketsIssued++;
        this.ticketId = "TCK-" + (1000 + ticketsIssued);
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public EventTicket(String attendeeId, double basePrice) {
        this(basePrice);

        if (attendeeId == null || attendeeId.length() < 4) {
            throw new IllegalArgumentException();
        }
    }

    public void pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        balanceDue = Math.max(0, balanceDue - amount);
    }

    public void pay(double amount, String mode) {
        if (mode == null || mode.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        pay(amount);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'F') {
            return false;
        }

        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }

        return Character.isUpperCase(code.charAt(4));
    }

    public static int getTicketsIssued() {
        return ticketsIssued;
    }

    public String printTicket() {
        return "Standard | Balance: " + getBalanceDue();
    }

    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        if (tickets == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }

        for (EventTicket ticket : tickets) {
            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }
}

class GroupTicket extends EventTicket {
    private final int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);

        if (groupSize <= 0) {
            throw new IllegalArgumentException();
        }

        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }

    @Override
    public double getBalanceDue() {
        return super.getBalanceDue() * groupSize;
    }

    @Override
    public void pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        super.pay(amount / groupSize);
    }

    @Override
    public String printTicket() {
        return "Group | Size: " + groupSize + " | Balance: " + getBalanceDue();
    }
}

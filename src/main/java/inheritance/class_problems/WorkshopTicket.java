package main.java.inheritance.class_problems;

class EventTicket {
    private final String attendeeId;
    private final double basePrice;
    private double balanceDue;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.length() < 4) {
            throw new IllegalArgumentException();
        }
        if (basePrice <= 0) {
            throw new IllegalArgumentException();
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        balanceDue = Math.max(0, balanceDue - amount);
    }

    protected void applyLateFee(Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException();
        }
        balanceDue += amount;
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + getBalanceDue();
    }
}

public class WorkshopTicket extends EventTicket {
    private final String track;
    private final double[] lateFeeHistory = new double[10];
    private int historyCount;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);

        if (track == null || track.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.track = track;
    }

    public WorkshopTicket(double basePrice) {
        this("TEMP", basePrice, "General");
    }

    public String getTrack() {
        return track;
    }

    @Override
    public double getBalanceDue() {
        return super.getBalanceDue();
    }

    @Override
    protected void applyLateFee(Double amount) {
        super.applyLateFee(amount * 2);

        if (historyCount < lateFeeHistory.length) {
            lateFeeHistory[historyCount++] = amount * 2;
        }
    }

    public double[] getLateFeeHistory() {
        double[] result = new double[historyCount];
        System.arraycopy(lateFeeHistory, 0, result, 0, historyCount);
        return result;
    }

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue();
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        if (attendeeIds == null || attendeeIds.length > 50 || basePrice <= 0) {
            return "Registered: 0 | Rejected: 0";
        }

        for (String attendeeId : attendeeIds) {
            try {
                new EventTicket(attendeeId, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}
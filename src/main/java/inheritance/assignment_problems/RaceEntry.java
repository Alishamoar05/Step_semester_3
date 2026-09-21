package main.java.inheritance.assignment_problems;

class RaceEntry {
    private final String bibNumber;
    private final double entryFee;
    private double balanceDue;
    private final String entryCode;
    private static int entryCounter = 0;
    private final String[] paymentModes = new String[10];
    private int paymentCount;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4) {
            throw new IllegalArgumentException();
        }

        if (entryFee <= 0) {
            throw new IllegalArgumentException();
        }

        entryCounter++;

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
        this.entryCode = "RACE-" + (1000 + entryCounter);
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public static int getEntryCount() {
        return entryCounter;
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

        if (paymentCount < paymentModes.length) {
            paymentModes[paymentCount++] = mode;
        }
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    protected void applyLateFee(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        balanceDue += amount;
    }

    public double[] getLateFeeHistory() {
        return new double[0];
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber
                + " | Balance: " + getBalanceDue();
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }

        return Character.isUpperCase(code.charAt(4));
    }

    public static String settleNightly(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relay + " relay | "
                + individual + " individual";
    }
}

class RunnerEntry extends RaceEntry {
    private final String category;
    private final double[] lateFeeHistory = new double[10];
    private int historyCount;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    protected void applyLateFee(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        double doubledAmount = amount * 2;

        super.applyLateFee(doubledAmount);

        if (historyCount < lateFeeHistory.length) {
            lateFeeHistory[historyCount++] = doubledAmount;
        }
    }

    @Override
    public double[] getLateFeeHistory() {
        double[] history = new double[historyCount];
        System.arraycopy(lateFeeHistory, 0, history, 0, historyCount);
        return history;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber()
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        if (bibNumbers == null || bibNumbers.length > 500 || entryFee <= 0) {
            return "Registered: 0 | Rejected: 0";
        }

        for (String bibNumber : bibNumbers) {
            try {
                new RaceEntry(bibNumber, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

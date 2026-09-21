package main.java.inheritance.assignment_problems;

class RaceEntry {
    private final String bibNumber;
    private final double entryFee;
    private double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4) {
            throw new IllegalArgumentException();
        }

        if (entryFee <= 0) {
            throw new IllegalArgumentException();
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        balanceDue = Math.max(0, balanceDue - amount);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber
                + " | Balance: " + getBalanceDue();
    }
}

class RunnerEntry extends RaceEntry {
    private final String category;

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

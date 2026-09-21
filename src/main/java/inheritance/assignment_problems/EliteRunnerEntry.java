package main.java.inheritance.assignment_problems;

public class EliteRunnerEntry extends RunnerEntry {
    private final double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);

        if (sponsorBonus < 0) {
            throw new IllegalArgumentException();
        }

        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public double getBalanceDue() {
        return super.getBalanceDue() - sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + getBibNumber()
                + " | Category: " + getCategory()
                + " | Balance: " + getBalanceDue()
                + " | Sponsor Bonus: " + sponsorBonus;
    }

    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }

        if (entry instanceof RunnerEntry) {
            return "Direct descendant";
        }

        return "Base RaceEntry";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        if (entries == null) {
            return 0.0;
        }

        double total = 0.0;

        for (RaceEntry entry : entries) {
            if (entry != null) {
                total += entry.getBalanceDue();
            }
        }

        return total;
    }
}

class RelayTeamEntry extends RaceEntry {
    private final int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);

        if (teamSize <= 0) {
            throw new IllegalArgumentException();
        }

        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public double getBalanceDue() {
        return super.getBalanceDue() * teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber()
                + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue();
    }
}

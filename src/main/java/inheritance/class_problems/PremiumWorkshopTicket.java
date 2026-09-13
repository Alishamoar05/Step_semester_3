package main.java.inheritance.class_problems;

public class PremiumWorkshopTicket extends WorkshopTicket {
    private final double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);

        if (kitFee < 0 || kitFee > 1000) {
            throw new IllegalArgumentException();
        }

        this.kitFee = kitFee;
    }

    @Override
    public double getBalanceDue() {
        return super.getBalanceDue() + kitFee;
    }

    @Override
    public String printTicket() {
        return "Premium Workshop Ticket | Track: " + getTrack()
                + " | Kit Fee: " + kitFee
                + " | Balance Due: " + getBalanceDue();
    }

    public static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        }

        if (ticket instanceof WorkshopTicket) {
            return "Direct descendant";
        }

        return "Base EventTicket";
    }

    public static double getTotalBalanceDue(EventTicket[] tickets) {
        if (tickets == null) {
            return 0.0;
        }

        double total = 0.0;

        for (EventTicket ticket : tickets) {
            if (ticket != null) {
                total += ticket.getBalanceDue();
            }
        }

        return total;
    }
}

class HackathonTicket extends EventTicket {
    private final String teamName;

    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);

        if (teamName == null || teamName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.teamName = teamName;
    }

    @Override
    public String printTicket() {
        return "Hackathon Ticket | Team: " + teamName
                + " | Balance Due: " + getBalanceDue();
    }
}
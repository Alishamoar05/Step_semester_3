package main.java.inheritance.assignment_problems;

public class RaceDayAnnouncer {

    public static String announceAll(RaceEntry[] entries) {
        StringBuilder result = new StringBuilder();

        for (RaceEntry entry : entries) {
            result.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                result.append(" [Team size via downcast: ")
                      .append(relay.getTeamSize())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }
}

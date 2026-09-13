package main.java.inheritance.class_problems;

public class TicketAnnouncer {

    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder result = new StringBuilder();

        for (EventTicket ticket : tickets) {
            result.append(ticket.printTicket());

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshopTicket = (WorkshopTicket) ticket;
                result.append(" [Track via downcast: ")
                      .append(workshopTicket.getTrack())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }
}

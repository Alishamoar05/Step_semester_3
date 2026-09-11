package main.java.constructors.class_problems;

import java.util.HashSet;
import java.util.Set;

public class BusTicketBookingValidator {

    static class BusTicket {
        private String passengerName;
        private String destination;
        private boolean checkedIn;

        public BusTicket(String passengerName, String destination) {
            if (passengerName == null || passengerName.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid passenger name");
            }
            if (destination == null || destination.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid destination");
            }

            this.passengerName = passengerName;
            this.destination = destination;
            this.checkedIn = false;
        }

        public void markCheckedIn() {
            checkedIn = true;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public String getDestination() {
            return destination;
        }

        public boolean isCheckedIn() {
            return checkedIn;
        }
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        Set<String> acceptedBookings = new HashSet<>();

        for (String[] booking : rawBookings) {
            try {
                if (booking == null || booking.length < 2) {
                    throw new IllegalArgumentException();
                }

                BusTicket ticket = new BusTicket(booking[0], booking[1]);
                String key = ticket.getPassengerName() + "|" + ticket.getDestination();

                if (acceptedBookings.contains(key)) {
                    duplicates++;
                } else {
                    acceptedBookings.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] bookings = {
            {"Divya", "Chennai"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {"", "Delhi"},
            {null, "Mumbai"}
        };

        processBatch(bookings);

        BusTicket ticket = new BusTicket("Alisha", "Bangalore");
        ticket.markCheckedIn();

        System.out.println(ticket.isCheckedIn());
    }
}
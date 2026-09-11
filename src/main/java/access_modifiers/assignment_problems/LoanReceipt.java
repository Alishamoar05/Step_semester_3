package main.java.access_modifiers.assignment_problems;

public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    private static int nightlyCirculationRuns;

    static {
        nightlyCirculationRuns = 0;
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (bookIds == null) {
            throw new IllegalArgumentException("Invalid book IDs");
        }

        for (String bookId : bookIds) {
            if (bookId == null || !bookId.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("Invalid book ID");
            }
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException();
        }

        if (newId == null || !newId.matches("BK-\\d{3}")) {
            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] correctedIds = bookIds.clone();
        correctedIds[index] = newId;

        return new LoanReceipt(memberId, correctedIds);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        nightlyCirculationRuns++;

        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public String getMemberId() {
        return memberId;
    }

    public static class ReferenceOnlyLoanReceipt extends LoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);

            if (roomNumber == null || roomNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid room number");
            }

            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    public static void main(String[] args) {

        try {
            new LoanReceipt(
                    "LIB-8841",
                    new String[]{"BK-100", "bad"}
            );
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected =
                r.withCorrectedBookId(0, "BK-999");

        System.out.println(corrected.getBookIds()[0]);

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"
            ),
            null,
            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"}
            )
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}

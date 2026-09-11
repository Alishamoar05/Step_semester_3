package main.java.access_modifiers.class_problems;

public class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    private static int nightlyRuns;

    static {
        nightlyRuns = 0;
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid patient ID");
        }

        if (medicationCodes == null) {
            throw new IllegalArgumentException("Invalid medication codes");
        }

        for (String code : medicationCodes) {
            if (code == null || !code.matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("Invalid medication code");
            }
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException();
        }

        if (newCode == null || !newCode.matches("MED-[A-Z]")) {
            throw new IllegalArgumentException("Invalid medication code");
        }

        String[] updatedCodes = medicationCodes.clone();
        updatedCodes[index] = newCode;

        return new DischargeSummary(patientId, updatedCodes);
    }

    public static String processNightlyBatch(
            DischargeSummary[] summaries,
            int icuDays) {

        nightlyRuns++;

        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        }

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else {
                routine++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + criticalCare + " critical-care | "
                + routine + " routine";
    }

    public String getPatientId() {
        return patientId;
    }

    public static class CriticalCareDischargeSummary extends DischargeSummary {

        public CriticalCareDischargeSummary(
                String patientId,
                String[] medicationCodes) {
            super(patientId, medicationCodes);
        }
    }

    public static void main(String[] args) {

        try {
            new DischargeSummary(
                    "MT2026-0142",
                    new String[]{"MED-A", "bad"}
            );
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d = new DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A", "MED-D"}
        );

        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";

        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary corrected =
                d.withCorrectedMedication(0, "MED-B");

        System.out.println(corrected.getMedicationCodes()[0]);

        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary(
                    "MT001",
                    new String[]{"MED-A"}
            ),
            null,
            new DischargeSummary(
                    "MT002",
                    new String[]{"MED-D"}
            )
        };

        System.out.println(processNightlyBatch(summaries, 1));
    }
}
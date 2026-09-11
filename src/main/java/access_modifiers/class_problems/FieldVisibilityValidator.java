package main.java.access_modifiers.class_problems;

public class FieldVisibilityValidator {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        return "DENIED";
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (attempt == null || attempt.length < 2) {
                denied++;
                continue;
            }

            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));

        try {
            PatientRecord record =
                    new PatientRecord("MT94", "W3", 98.2, "MediTrack Central");
            System.out.println("Patient record created");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}

class PatientRecord {

    private String patientId;
    private String wardCode;
    private double vitalsScore;
    private String facilityName;

    public PatientRecord(String patientId, String wardCode,
                         double vitalsScore, String facilityName) {

        if (patientId == null
                || patientId.trim().isEmpty()
                || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid patient ID");
        }

        if (wardCode == null
                || wardCode.trim().isEmpty()
                || wardCode.trim().length() < 1) {
            throw new IllegalArgumentException("Invalid ward code");
        }

        if (facilityName == null
                || facilityName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid facility name");
        }

        this.patientId = patientId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

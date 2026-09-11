package main.java.access_modifiers.class_problems;

public class PatientProfile {

    private String patientId;
    private String patientName;
    private boolean discharged;
    private String lockerPin;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.patientName = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        if (this.patientId == null) {
            this.patientId = patientId;
        }
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin == null || !pin.matches("\\d{4,6}")) {
            return;
        }

        this.lockerPin = Integer.toHexString(pin.hashCode());
    }

    public static void main(String[] args) {
        PatientProfile p = new PatientProfile();

        System.out.println(p.getPatientId());

        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED");
        System.out.println(p.getPatientId());

        p.setLockerPin("1234");
    }
}

package main.java.abstraction.class_problems;

public abstract class StaffMember {
    private double baseSalary;

    public StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    public StaffMember(double baseSalary, double bonusRate) {
        if (baseSalary <= 0 || bonusRate < 0) {
            throw new IllegalArgumentException();
        }

        this.baseSalary = baseSalary;
    }

    public abstract double calculateBonus();

    public double getSalary() {
        return baseSalary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            return;
        }

        this.baseSalary = salary;
    }
}

interface Auditable {
    String auditRecord();
}

class TeamLead extends StaffMember implements Auditable {
    private final double bonusRate;
    private final int teamSize;

    public TeamLead(double baseSalary, int teamSize) {
        this(baseSalary, 0.10, teamSize);
    }

    public TeamLead(double baseSalary, double bonusRate, int teamSize) {
        super(baseSalary, bonusRate);

        if (bonusRate < 0 || teamSize < 0) {
            throw new IllegalArgumentException();
        }

        this.bonusRate = bonusRate;
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize
                + " team members, salary $" + getSalary();
    }
}

class AuditHelper {
    public static String getAuditIfApplicable(StaffMember staffMember) {
        if (staffMember instanceof Auditable) {
            Auditable auditable = (Auditable) staffMember;
            return auditable.auditRecord();
        }

        return "Not auditable";
    }
}

package main.java.oop_concepts.class_problems;

public class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }

    public String getRegNo() {
        return regNo;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public static void main(String[] args) {

        FeeAccount plain = new FeeAccount("PLAIN", 150000, 150000);

        HostelFeeAccount hostel = new HostelFeeAccount("HOSTEL", 200000, 60000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("SCHOLAR", 180000, 0, 20);

        plain.pay(0);
        hostel.payInTwoInstallments(0);
        scholarship.pay(40000);

        System.out.println("Plain account due: Rs " + plain.getDue());
        System.out.println("Hostel account due: Rs " + hostel.getDue());
        System.out.println("Scholarship account effective due: Rs "
                + scholarship.effectiveDue());
    }
}

class HostelFeeAccount extends FeeAccount {

    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    public void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee,
                                 double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    public double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}

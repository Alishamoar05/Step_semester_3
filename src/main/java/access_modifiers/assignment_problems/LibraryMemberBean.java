package main.java.access_modifiers.assignment_problems;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMemberBean() {
        this(null, null);
    }

    public LibraryMemberBean(String name) {
        this(null, name);
    }

    public LibraryMemberBean(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer == null) {
            return;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    answer.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder result = new StringBuilder();

            for (byte b : hash) {
                result.append(String.format("%02x", b));
            }

            securityAnswer = result.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Hash algorithm unavailable");
        }
    }

    public static void main(String[] args) {

        LibraryMemberBean member1 =
                new LibraryMemberBean("Priya Nair");

        System.out.println(member1.getMembershipId());

        LibraryMemberBean member2 =
                new LibraryMemberBean("LIB-8841", "Priya Nair");

        System.out.println(member2.getMembershipId());

        LibraryMemberBean member3 =
                new LibraryMemberBean();

        member3.setMembershipId("LIB-8841");
        member3.setMembershipId("FAKE-0000");

        System.out.println(member3.getMembershipId());

        member3.setPremiumMember(true);

        System.out.println(member3.isPremiumMember());

        member3.setSecurityAnswer("MyFirstPet");
    }
}

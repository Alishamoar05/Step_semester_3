package main.java.oop_concepts.assignment_problems;

public class LibraryMember {

    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "SRM Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + String.format("%03d", memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    static class BrokenLibraryMember {

        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId, int booksIssued) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }
    }

    public static void main(String[] args) {

        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        member1.printMemberCard();

        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        member1.printMemberCard();
        member2.printMemberCard();

        System.out.println();

        LibraryMember aditi =
                new LibraryMember("Aditi", 2);

        LibraryMember rohan =
                new LibraryMember("Rohan", 3);

        aditi.printMemberCard();
        rohan.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}

package main.java.oop_concepts.class_problems;

public class StudentStaticFix {

    static class BrokenSrmStudent {

        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }

        void printCard() {
            System.out.println(name + " | " + regNo + " | " + attendance + "%");
        }
    }

    static class SrmStudent {

        String name;
        String regNo;
        int attendance;

        static String university = "SRM";
        static int admissionCount = 0;

        SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
            admissionCount++;
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {

        BrokenSrmStudent student1 =
                new BrokenSrmStudent("Ravi", "RA231100301011", 82);

        student1.printCard();

        BrokenSrmStudent student2 =
                new BrokenSrmStudent("Meera", "RA231100301012", 74);

        student1.printCard();
        student2.printCard();

        System.out.println();

        SrmStudent ravi =
                new SrmStudent("Ravi", "RA231100301011", 82);

        SrmStudent meera =
                new SrmStudent("Meera", "RA231100301012", 74);

        ravi.printIdCard();
        meera.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}

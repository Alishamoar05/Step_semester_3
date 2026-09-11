package main.java.oop_concepts.class_problems;

public class SrmStudent {

    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    static double classAverage(SrmStudent[] students) {
        int total = 0;

        for (SrmStudent student : students) {
            total += student.attendance;
        }

        return (double) total / students.length;
    }

    public static void main(String[] args) {

        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA01", 82),
            new SrmStudent("Anitha", "RA02", 68),
            new SrmStudent("Karthik", "RA03", 91),
            new SrmStudent("Meera", "RA04", 74),
            new SrmStudent("Suresh", "RA05", 60)
        };

        for (SrmStudent student : students) {
            System.out.println(
                student.name + " - " +
                student.attendance + "% - " +
                (student.isEligible() ? "Eligible" : "Detained")
            );
        }

        System.out.printf(
            "Class average: %.1f%%%n",
            SrmStudent.classAverage(students)
        );
    }
}

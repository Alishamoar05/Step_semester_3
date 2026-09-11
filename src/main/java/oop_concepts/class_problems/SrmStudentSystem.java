package main.java.oop_concepts.class_problems;

public class SrmStudentSystem {

    static class Student {

        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        Student(String name, String regNo, HostelFeeAccount feeAccount) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
        }

        String fullStatus() {
            String roomNumber = room == null ? "unallotted" : room.roomNo;

            return name + " | Due: Rs " + feeAccount.getDue()
                    + " | Room: " + roomNumber;
        }
    }

    static int totalStudents = 0;

    public static void main(String[] args) {

        HostelFeeAccount raviFee =
                new HostelFeeAccount("RA01", 200000, 60000);

        HostelFeeAccount anithaFee =
                new HostelFeeAccount("RA02", 180000, 0);

        HostelFeeAccount karthikFee =
                new HostelFeeAccount("RA03", 200000, 0);

        Student ravi =
                new Student("Ravi", "RA01", raviFee);
        totalStudents++;

        Student anitha =
                new Student("Anitha", "RA02", anithaFee);
        totalStudents++;

        Student karthik =
                new Student("Karthik", "RA03", karthikFee);
        totalStudents++;

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        ravi.room = HostelRoom.findAvailableRoom(rooms);
        if (ravi.room != null) {
            ravi.room.allot(ravi.name);
        }

        anitha.room = HostelRoom.findAvailableRoom(rooms);
        if (anitha.room != null) {
            anitha.room.allot(anitha.name);
        }

        HostelRoom.safeAllot(rooms, karthik.name);

        karthikFee.pay(-5000);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println("Total students: " + totalStudents);
    }
}

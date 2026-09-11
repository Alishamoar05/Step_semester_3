package main.java.oop_concepts.assignment_problems;

public class CompanyEmployeeRecord {

    String name;
    String empId;
    EmployeeHierarchy.Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          EmployeeHierarchy.Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        totalRecords++;
    }

    String fullProfile() {
        String slotNumber = slot == null ? "No parking assigned" : slot.slotNo;

        double pay;

        if (employee instanceof EmployeeHierarchy.ManagerEmployee) {
            pay = ((EmployeeHierarchy.ManagerEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        return name + " | Pay: Rs " + pay + " | Slot: " + slotNumber;
    }

    public static void main(String[] args) {

        EmployeeHierarchy.ManagerEmployee divyaEmployee =
                new EmployeeHierarchy.ManagerEmployee(
                        "E01", "Divya", 70000, 8000);

        EmployeeHierarchy.Employee karanEmployee =
                new EmployeeHierarchy.Employee(
                        "E02", "Karan", 40000);

        EmployeeHierarchy.Employee meeraEmployee =
                new EmployeeHierarchy.Employee(
                        "E03", "Meera", 10000);

        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord("Divya", "E01", divyaEmployee);

        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord("Karan", "E02", karanEmployee);

        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord("Meera", "E03", meeraEmployee);

        ParkingSlot[] slots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        divya.slot = ParkingSlot.findAvailableSlot(slots);
        if (divya.slot != null) {
            divya.slot.allot(divya.empId);
        }

        karan.slot = ParkingSlot.findAvailableSlot(slots);
        if (karan.slot != null) {
            karan.slot.allot(karan.empId);
        }

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());

        System.out.println("Total records: " + totalRecords);
    }
}
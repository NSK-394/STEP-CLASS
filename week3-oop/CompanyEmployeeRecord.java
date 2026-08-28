class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;   // field is itself an object — composition
    ParkingSlot slot;

    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        double pay;
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String slotInfo = (slot != null) ? slot.getSlotNo() : "no parking assigned";
        return name + " | Pay: Rs " + pay + " | Slot: " + slotInfo;
    }

    public static void main(String[] args) {
        Employee divyaEmp = new ManagerEmployee(101, "Divya", 70000, 8000);
        Employee karanEmp = new Employee(102, "Karan", 40000);
        Employee meeraEmp = new InternEmployee(103, "Meera", 12000, 10000);

        ParkingSlot slotA1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot slotA2 = new ParkingSlot("A2", 5, 4);
        slotA1.allot("DIVYA-CAR");
        slotA2.allot("KARAN-CAR");

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E101", divyaEmp, slotA1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E102", karanEmp, slotA2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E103", meeraEmp, null); // no slot — on purpose

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
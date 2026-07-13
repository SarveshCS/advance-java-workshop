public class InheritanceExample {
    public static void main(String[] args) {
        FullTimeEmployee employee = new FullTimeEmployee("E101", "Sample Employee", 45000.00, 8000.00);

        employee.printBasicDetails();
        System.out.println("Monthly Salary: Rs. " + employee.calculateMonthlySalary());
    }
}

class EmployeeRecord {
    private String employeeId;
    private String employeeName;

    EmployeeRecord(String employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    void printBasicDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
    }
}

class FullTimeEmployee extends EmployeeRecord {
    private double basicSalary;
    private double allowance;

    FullTimeEmployee(String employeeId, String employeeName, double basicSalary, double allowance) {
        super(employeeId, employeeName);
        this.basicSalary = basicSalary;
        this.allowance = allowance;
    }

    double calculateMonthlySalary() {
        return basicSalary + allowance;
    }
}
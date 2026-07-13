public class AbstractionExample {
    public static void main(String[] args) {
        ReportGenerator salesReport = new SalesReport("Quarterly Sales", 125000.00);
        ReportGenerator attendanceReport = new AttendanceReport("Workshop Attendance", 42, 50);

        printReport(salesReport);
        printReport(attendanceReport);
    }

    private static void printReport(ReportGenerator reportGenerator) {
        reportGenerator.printTitle();
        reportGenerator.printSummary();
        System.out.println();
    }
}

abstract class ReportGenerator {
    private String reportTitle;

    ReportGenerator(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    void printTitle() {
        System.out.println("Report: " + reportTitle);
    }

    abstract void printSummary();
}

class SalesReport extends ReportGenerator {
    private double totalSales;

    SalesReport(String reportTitle, double totalSales) {
        super(reportTitle);
        this.totalSales = totalSales;
    }

    @Override
    void printSummary() {
        System.out.println("Total Sales: Rs. " + totalSales);
    }
}

class AttendanceReport extends ReportGenerator {
    private int presentStudents;
    private int totalStudents;

    AttendanceReport(String reportTitle, int presentStudents, int totalStudents) {
        super(reportTitle);
        this.presentStudents = presentStudents;
        this.totalStudents = totalStudents;
    }

    @Override
    void printSummary() {
        System.out.println("Present Students: " + presentStudents + " out of " + totalStudents);
    }
}
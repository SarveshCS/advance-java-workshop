public class ConstructorExample {
    public static void main(String[] args) {
        CourseEnrollment enrollment = new CourseEnrollment("Student User", "Advanced Java", 30);
        CourseEnrollment defaultEnrollment = new CourseEnrollment();

        enrollment.printEnrollmentDetails();
        defaultEnrollment.printEnrollmentDetails();
    }
}

class CourseEnrollment {
    private String studentName;
    private String courseName;
    private int durationInDays;

    CourseEnrollment() {
        this("Guest Student", "Core Java", 15);
    }

    CourseEnrollment(String studentName, String courseName, int durationInDays) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.durationInDays = durationInDays;
    }

    void printEnrollmentDetails() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Course Name: " + courseName);
        System.out.println("Duration: " + durationInDays + " days");
        System.out.println();
    }
}
public class StringExample {
    public static void main(String[] args) {
        String rawName = "   sarvesh chavan   ";
        String courseCode = "adv-java";

        String cleanedName = rawName.trim();
        String displayName = toTitleCase(cleanedName);
        String username = createUsername(cleanedName, courseCode);

        System.out.println("Original Name: [" + rawName + "]");
        System.out.println("Display Name: " + displayName);
        System.out.println("Course Code: " + courseCode.toUpperCase());
        System.out.println("Generated Username: " + username);
    }

    private static String toTitleCase(String name) {
        String firstLetter = name.substring(0, 1).toUpperCase();
        String remainingLetters = name.substring(1).toLowerCase();

        return firstLetter + remainingLetters;
    }

    private static String createUsername(String name, String courseCode) {
        String normalizedName = name.toLowerCase().replace(" ", ".");
        String normalizedCourse = courseCode.toLowerCase().replace("-", "");

        return normalizedName + "@" + normalizedCourse;
    }
}
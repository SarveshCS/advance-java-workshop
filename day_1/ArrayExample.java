public class ArrayExample {
    public static void main(String[] args) {
        String[] categories = {"Food", "Travel", "Books", "Internet"};
        double[] expenses = {450.50, 220.00, 799.99, 599.00};

        double totalExpense = calculateTotal(expenses);
        int highestExpenseIndex = findHighestExpenseIndex(expenses);

        System.out.println("Monthly Expense Summary");
        printExpenses(categories, expenses);
        System.out.println("Total Expense: Rs. " + totalExpense);
        System.out.println("Highest Category: " + categories[highestExpenseIndex]);
    }

    private static void printExpenses(String[] categories, double[] expenses) {
        for (int index = 0; index < categories.length; index++) {
            System.out.println(categories[index] + ": Rs. " + expenses[index]);
        }
    }

    private static double calculateTotal(double[] expenses) {
        double total = 0.0;

        for (double expense : expenses) {
            total += expense;
        }

        return total;
    }

    private static int findHighestExpenseIndex(double[] expenses) {
        int highestIndex = 0;

        for (int index = 1; index < expenses.length; index++) {
            if (expenses[index] > expenses[highestIndex]) {
                highestIndex = index;
            }
        }

        return highestIndex;
    }
}
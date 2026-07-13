public class PolymorphismExample {
    public static void main(String[] args) {
        PaymentMethod cardPayment = new CardPayment("Sample Customer", "1234");
        PaymentMethod upiPayment = new UpiPayment("student@upi");

        printPaymentStatus(cardPayment, 1250.00);
        printPaymentStatus(upiPayment, 499.00);
    }

    private static void printPaymentStatus(PaymentMethod paymentMethod, double amount) {
        System.out.println(paymentMethod.pay(amount));
    }
}

class PaymentMethod {
    String pay(double amount) {
        return "Processing payment of Rs. " + amount;
    }
}

class CardPayment extends PaymentMethod {
    private String cardHolderName;
    private String lastFourDigits;

    CardPayment(String cardHolderName, String lastFourDigits) {
        this.cardHolderName = cardHolderName;
        this.lastFourDigits = lastFourDigits;
    }

    @Override
    String pay(double amount) {
        return "Paid Rs. " + amount + " using card ending with " + lastFourDigits + " for " + cardHolderName;
    }
}

class UpiPayment extends PaymentMethod {
    private String upiId;

    UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    String pay(double amount) {
        return "Paid Rs. " + amount + " using UPI ID " + upiId;
    }
}
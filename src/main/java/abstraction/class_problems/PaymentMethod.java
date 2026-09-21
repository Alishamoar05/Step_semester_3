package main.java.abstraction.class_problems;

abstract class PaymentMethod {
    private final String transactionId;
    private static int transactionCounter = 0;

    protected PaymentMethod() {
        transactionCounter++;
        transactionId = "TXN-" + (1000 + transactionCounter);
    }

    public abstract String processPayment(double amount);

    public abstract String processPayment(double amount, String note);

    public String getTransactionId() {
        return transactionId;
    }

    public static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }
}
class CreditCardPayment extends PaymentMethod {
    private final String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        if (cardNumberLastFour == null || cardNumberLastFour.length() != 4) {
            throw new IllegalArgumentException();
        }

        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        return "Charged $" + amount + " to card ending "
                + cardNumberLastFour + " - Txn " + getTransactionId();
    }

    @Override
    public String processPayment(double amount, String note) {
        if (note == null || note.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return processPayment(amount) + " (" + note + ")";
    }
}

class CashPayment extends PaymentMethod {

    public CashPayment() {
        super();
    }

    @Override
    public String processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        return "Received $" + amount + " in cash - Txn " + getTransactionId();
    }

    @Override
    public String processPayment(double amount, String note) {
        if (note == null || note.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return processPayment(amount) + " (" + note + ")";
    }
}

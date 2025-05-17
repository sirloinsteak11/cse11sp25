// TODO: File header
import java.util.ArrayList;

// TODO: Class header
public class PaymentProcessor {
    private ArrayList<Payment> paymentList;

    private static final int MAX_EXPIRY_MONTH = 12;
    private static final int MIN_EXPIRY_MONTH_2025 = 7;
    private static final int MIN_EXPIRY_YEAR = 2025;
    private static final int MAX_STARS = 10;
    private static final String UCSD_EMAIL = "@ucsd.edu";
    private static final String CARD_PAYMENT = "CardPayment";
    private static final String ELECTRONIC_PAYMENT = "ElectronicPayment";
    private static final String DEBIT_PAYMENT = "DebitPayment";
    private static final String CREDIT_PAYMENT = "CreditPayment";
    private static final String MOBILE_PAYMENT = "MobilePayment";
    private static final String DIGITAL_WALLET_PAYMENT = "DigitalWalletPayment";

    // TODO: Method header
    public PaymentProcessor() {
    }

    // TODO: Method header
    public void addToPaymentList(Payment payment) {
    }

    // TODO: Method header
    public void addToPaymentList(Payment[] payments) {
    }

    // TODO: Method header
    public boolean hasPayment(String paymentTransactionName) {
        return false;
    }

    // TODO: Method header
    public Payment getAndRemovePayment(String paymentTransactionName) {
        return null;
    }

    // TODO: Method header
    public static int compareRisk(Payment paymentOne, Payment paymentTwo) {
        return 0;
    }

    // TODO: Method header
    public int applyPaymentSurge(double increaseRate) {
        return 0;
    }

    // TODO: Method header
    public static boolean processPayment(Payment payment) {
        return false;
    }

    /**
     * The getter method of the paymentList member variable
     * @return the paymentList of the PaymentProcessor instance
     */
    public ArrayList<Payment> getPaymentList() {
        return this.paymentList;
    }
}

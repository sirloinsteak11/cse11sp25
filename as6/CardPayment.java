// TODO: File header
// TODO: Class header
public class CardPayment extends Payment {
    private long cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private static final String HIGH_LEVEL_TYPE = "CardPayment";
    private static final String TYPE = "Untyped CardPayment";

    /**
     * No-arg constructor of the CardPayment class
     */
    public CardPayment() {}

    // TODO: Method header
    public CardPayment(String transactionName, double amount,
                             long cardNumber, int expiryMonth,
                             int expiryYear) {

        this.transactionName = transactionName;
    }

    // TODO: Method header
    @Override
    public long getCardNumber() {
        return 0;
    }

    // TODO: Method header
    @Override
    public int getExpiryMonth() {
        return 0;
    }

    // TODO: Method header
    @Override
    public int getExpiryYear() {
        return 0;
    }

    // TODO: Method header
    @Override
    public String getHighLevelType() {
        return null;
    }

    // TODO: Method header
    @Override
    public String getType() {
        return null;
    }

    // TODO: Method header
    @Override
    public boolean equals(Object object) {
        return false;
    }

    // TODO: Method header
    @Override
    public String toString() {
        return "CardPayment (" + getTransactionName() +
            ") cardNumber: " + getCardNumber() + ", expiryMonth: " +
                getExpiryMonth() + ", expiryYear: " + getExpiryYear();
    }
}

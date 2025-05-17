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

        super(transactionName, amount);

        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;

    }

    // TODO: Method header
    @Override
    public long getCardNumber() {
        return this.cardNumber;
    }

    // TODO: Method header
    @Override
    public int getExpiryMonth() {
        return this.expiryMonth;
    }

    // TODO: Method header
    @Override
    public int getExpiryYear() {
        return this.expiryYear;
    }

    // TODO: Method header
    @Override
    public String getHighLevelType() {
        return HIGH_LEVEL_TYPE;
    }

    // TODO: Method header
    @Override
    public String getType() {
        return TYPE;
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

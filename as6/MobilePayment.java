// TODO: File header
// TODO: Class header
public class MobilePayment extends ElectronicPayment {
    private String phoneNumber;
    private int starRating;
    private static final String TYPE = "MobilePayment";

    private static final double MAX_STAR_RATING = 10.0;

    /**
     * No-arg constructor of the MobilePayment class
     */
    public MobilePayment() {}

    // TODO: Method header
    public MobilePayment(String transactionName, double amount,
                                long accountId, String emailAddress,
                                String phoneNumber, int starRating) {
    }

    // TODO: Method header
    @Override
    public String getPhoneNumber() {
        return null;
    }

    // TODO: Method header
    @Override
    public int getStarRating() {
        return 0;
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
    public double calculateDigitalRisk() {
        return 0.0;
    }

    // TODO: Method header
    @Override
    public String toString() {
        return "MobilePayment (" + getTransactionName() +
            ") phoneNumber: " + getPhoneNumber() + ", starRating: " +
                getStarRating();
    }
}

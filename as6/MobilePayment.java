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

        super(transactionName, amount, accountId, emailAddress);

        this.phoneNumber = phoneNumber;
        this.starRating = starRating;
    }

    // TODO: Method header
    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // TODO: Method header
    @Override
    public int getStarRating() {
        return this.starRating;
    }

    // TODO: Method header
    @Override
    public String getType() {
        return TYPE;
    }

    // TODO: Method header
    @Override
    public boolean equals(Object object) {
        MobilePayment mp = (MobilePayment)object;
        return super.equals(object) && phoneNumber.equals(mp.phoneNumber) && 
            starRating == mp.starRating;
    }

    // TODO: Method header
    @Override
    public double calculateDigitalRisk() {
        double starRatingNormalized = (double)starRating / MAX_STAR_RATING;
        return getAmount() / starRatingNormalized;
    }

    // TODO: Method header
    @Override
    public String toString() {
        return "MobilePayment (" + getTransactionName() +
            ") phoneNumber: " + getPhoneNumber() + ", starRating: " +
                getStarRating();
    }
}

// TODO: File header
// TODO: Class header
public class ElectronicPayment extends Payment {
    private long accountId;
    private String emailAddress;
    private static final String HIGH_LEVEL_TYPE = "ElectronicPayment";
    private static final String TYPE = "Untyped ElectronicPayment";

    /**
     * No-arg constructor of the ElectronicPayment class
     */
    public ElectronicPayment() {}

    // TODO: Method header
    public ElectronicPayment(String transactionName, double amount,
                             long accountId, String emailAddress) {
    }

    // TODO: Method header
    @Override
    public long getAccountId() {
        return 0L;
    }

    // TODO: Method header
    @Override
    public String getEmailAddress() {
        return null;
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
        return "ElectronicPayment (" + getTransactionName() +
            ") accountId: " + getAccountId() + ", emailAddress: " +
                getEmailAddress();
    }

}

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

        super(transactionName, amount);

        this.accountId = accountId;
        this.emailAddress = emailAddress;
    }

    // TODO: Method header
    @Override
    public long getAccountId() {
        return this.accountId;
    }

    // TODO: Method header
    @Override
    public String getEmailAddress() {
        return this.emailAddress;
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
        ElectronicPayment ep = (ElectronicPayment)object;
        return super.equals(object) && accountId == ep.accountId && 
            emailAddress.equals(ep.emailAddress);
    }

    // TODO: Method header
    @Override
    public String toString() {
        return "ElectronicPayment (" + getTransactionName() +
            ") accountId: " + getAccountId() + ", emailAddress: " +
                getEmailAddress();
    }

}

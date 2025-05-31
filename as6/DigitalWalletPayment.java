public class DigitalWalletPayment extends ElectronicPayment {
    private String walletId;
    private int numTransactions;

    private static final String TYPE = "DigitalWalletPayment";

    /**
     * No arg constructor
     */
    public DigitalWalletPayment() {

    }

    public DigitalWalletPayment(String transactionName, double amount, 
                                        long accountId, String emailAddress, 
                                        String walletId, int numTransactions) {
        
        super(transactionName, amount, accountId, emailAddress);

        this.walletId = walletId;
        this.numTransactions = numTransactions;
    }

    @Override
    public String getWalletId() {
        return this.walletId;
    }

    @Override
    public int getNumTransactions() {
        return this.numTransactions;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean equals (Object object) {
        DigitalWalletPayment wp = (DigitalWalletPayment)object;
        return super.equals(object) && walletId.equals(wp.walletId) && 
            numTransactions == wp.numTransactions;
    }

    @Override
    public double calculateDigitalRisk() {
        double transactionDividend = Math.log(numTransactions);

        return getAmount() / transactionDividend;
    }

    @Override
    public String toString() {
        return "DigitalWalletPayment (" + getTransactionName() +
            ") walletId: " + getWalletId() + ", numTransactions: " +
                getNumTransactions();
    }
}

// TODO: File header
// TODO: Class header
public class Payment {
    private String transactionName;
    private double amount;

    private static final String HIGH_LEVEL_TYPE = "Untyped High Level Payment";
    private static final String TYPE = "Untyped Payment";

    /**
     * No-arg constructor of the Payment class
     */
    public Payment() {

    }

    // TODO: Method header
    public Payment(String transactionName, double amount) {
        this.transactionName = transactionName;
        this.amount = amount;
    }

    // TODO: Method header
    public String getTransactionName() {
        return this.transactionName;
    }

    // TODO: Method header
    public double getAmount() {
        return this.amount;
    }

    // TODO: Method header
    public String getHighLevelType() {
        return HIGH_LEVEL_TYPE;
    }

    // TODO: Method header
    public String getType() {
        return TYPE;
    }

    // TODO: Method header
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // TODO: Method header
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment paymentObject = (Payment)object;
        return transactionName.equals(paymentObject.transactionName) && amount == paymentObject.amount;
    }

    // TODO: Method header
    @Override
    public String toString() {
        return "Payment (" + getTransactionName() +
            ") amount: " + getAmount();
    }

    /**
     * Card number of a CardPayment instance
     * @return the long representation of the card number
     */
    public long getCardNumber() {
        return 0;
    }

    /**
     * Expiry month of a card of a CardPayment instance
     * @return the month of the card's expiration date
     */
    public int getExpiryMonth() {
        return 0;
    }

    /**
     * Expiry year of a card of a CardPayment instance
     * @return the year of the card's expiration date
     */
    public int getExpiryYear() {
        return 0;
    }

    /**
     * Account ID of an ElectronicPayment instance
     * @return the long representation of the account ID
     */
    public long getAccountId() {
        return 0;
    }

    /**
     * Email address of an ElectronicPayment instance
     * @return the String representation of the email
     */
    public String getEmailAddress() {
        return null;
    }

    /**
     * Bank balance of a bank account linked to a DebitPayment
     * @return the double representation of the bank balance
     */
    public double getBankBalance() {
        return 0;
    }

    /**
     * Monthly income of the individual linked to a DebitPayment
     * @return the double representation of the monthly income
     */
    public double getMonthlyIncome() {
        return 0;
    }

    /**
     * Credit limit of a card linked to a CreditPayment
     * @return the double representation of the credit limit
     */
    public double getCreditLimit() {
        return 0;
    }

    /**
     * Outstanding balance of a card linked to a CreditPayment
     * @return the double representation of the outstanding balance
     */
    public double getCardBalance() {
        return 0;
    }

    /**
     * Interest rate of the CreditPayment
     * @return the double representation of the interest rate
     */
    public double getInterestRate() {
        return 0;
    }

    /**
     * Phone number linked to a MobilePayment
     * @return the String representation of the phone number
     */
    public String getPhoneNumber() {
        return null;
    }

    /**
     * From 1 to 10 stars, the star rating of the user of the MobilePayment
     * @return the integer representation of the star rating
     */
    public int getStarRating() {
        return 0;
    }

    /**
     * The digital wallet ID linked to the DigitalWalletPayment
     * @return the String representation of the wallet ID
     */
    public String getWalletId() {
        return null;
    }

    /**
     * The number of transactions made by the digital wallet
     * of the DigitalWalletPayment
     * @return the integer representation of the number of transactions
     */
    public int getNumTransactions() {
        return 0;
    }

    /**
     * The risk associated with an instance of a CardPayment
     * @return the risk represented as a double
     */
    public double calculateCardRisk() {
        return 0;
    }

    /**
     * The risk associated with an instance of an ElectronicPayment
     * @return the risk represented as a double
     */
    public double calculateDigitalRisk() {
        return 0;
    }
}

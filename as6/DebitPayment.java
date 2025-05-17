// TODO: File header
// TODO: Class header
public class DebitPayment extends CardPayment {
    private double bankBalance;
    private double monthlyIncome;
    private static final String TYPE = "DebitPayment";

    /**
     * No-arg constructor for the DebitPayment class
     */
    public DebitPayment() {}

    // TODO: Method header
    public DebitPayment(String transactionName, double amount,
                                long cardNumber, int expiryMonth,
                                int expiryYear, double bankBalance,
                                double monthlyIncome) {
    }

    // TODO: Method header
    @Override
    public double getBankBalance() {
        return 0.0;
    }

    // TODO: Method header
    public double getMonthlyIncome() {
        return 0.0;
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
    public double calculateCardRisk() {
        return 0.0;
    }

    // TODO: Method header
    @Override
    public String toString() {
        return "DebitPayment (" + getTransactionName() +
            ") bankBalance: " + getBankBalance() +
                ", monthlyIncome: " + getMonthlyIncome();
    }
}

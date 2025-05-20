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

        super(transactionName, amount, cardNumber, expiryMonth, expiryYear);

        this.bankBalance = bankBalance;
        this.monthlyIncome = monthlyIncome;
    }

    // TODO: Method header
    @Override
    public double getBankBalance() {
        return this.bankBalance;
    }

    // TODO: Method header
    public double getMonthlyIncome() {
        return this.monthlyIncome;
    }

    // TODO: Method header
    @Override
    public String getType() {
        return TYPE;
    }

    // TODO: Method header
    @Override
    public boolean equals(Object object) {
        DebitPayment dp = (DebitPayment)object;
        return super.equals(object) && bankBalance == dp.bankBalance && 
            monthlyIncome == dp.monthlyIncome;
    }

    // TODO: Method header
    @Override
    public double calculateCardRisk() {
        return (getAmount()/bankBalance) * (getAmount()/monthlyIncome);
    }

    // TODO: Method header
    @Override
    public String toString() {
        return "DebitPayment (" + getTransactionName() +
            ") bankBalance: " + getBankBalance() +
                ", monthlyIncome: " + getMonthlyIncome();
    }
}

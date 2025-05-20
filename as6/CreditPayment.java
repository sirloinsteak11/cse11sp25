public class CreditPayment extends CardPayment {
    private double creditLimit;
    private double cardBalance;
    private double interestRate;

    private static final String TYPE = "CreditPayment";

    /**
     * No arg constructor
     */
    public CreditPayment() {

    }

    public CreditPayment(String transactionName, double amount, 
                                long cardNumber, int expiryMonth, 
                                int expiryYear, double creditLimit, 
                                double cardBalance, double interestRate) {
        
        super(transactionName, amount, cardNumber, expiryMonth, expiryYear);

        this.creditLimit = creditLimit;
        this.cardBalance = cardBalance;
        this.interestRate = interestRate;
    }
    
    @Override
    public double getCreditLimit() {
        return this.creditLimit;
    }

    @Override
    public double getCardBalance() {
        return this.cardBalance;
    }

    @Override
    public double getInterestRate() {
        return this.interestRate;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean equals(Object object) {
        CreditPayment cp = (CreditPayment)object;
        return super.equals(object) && creditLimit == cp.creditLimit && 
            cardBalance == cp.cardBalance && interestRate == cp.interestRate;
    }

    @Override
    public double calculateCardRisk() {
        return (cardBalance/creditLimit) * (1 + interestRate);
    }

    @Override
    public String toString() {
        return "CreditPayment (" + getTransactionName() +
            ") creditLimit: " + getCreditLimit() + ", cardBalance: " + 
            getCardBalance() + ", interestRate: " + getInterestRate();
    }
}

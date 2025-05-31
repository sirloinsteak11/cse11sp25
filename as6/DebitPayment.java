///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              DebitPayment
// Files:              DebitPayment.java
// Quarter:            CSE11 SPRING 2025
//
// Author:             DEONN ALMIROL
// Email:              DALMIROL@UCSD.EDU
// Instructor's Name:  BEN OCHOA
//
///////////////////////////////////////////////////////////////////////////////
//                   STUDENTS WHO GET HELP COMPLETE THIS SECTION
//                   You must fully acknowledge and credit sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   Avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * inherits card payment class and involves specifically debit
 * 
 */
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

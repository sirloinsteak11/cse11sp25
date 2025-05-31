///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              CardPayment
// Files:              CardPayment.java
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
 * inherits payment class and involves all card payments
 * 
 * bugs: none afaik
 * 
 * @author deonn almirol
 */
public class CardPayment extends Payment {
    private long cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private static final String HIGH_LEVEL_TYPE = "CardPayment";
    private static final String TYPE = "Untyped CardPayment";

    /**
     * No-arg constructor of the CardPayment class
     */
    public CardPayment() {}

    /**
     * initiailzies super class and sets all provided data fields
     * 
     * @param transactionName name of transaction
     * @param amount amount of payment
     * @param cardNumber card number of user
     * @param expiryMonth expiry month of card
     * @param expiryYear expiry year of card
     */
    public CardPayment(String transactionName, double amount,
                             long cardNumber, int expiryMonth,
                             int expiryYear) {

        super(transactionName, amount);

        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;

    }

    /**
     * returns this card number
     * 
     * @return card number
     */
    @Override
    public long getCardNumber() {
        return this.cardNumber;
    }

    /**
     * returns this expiry month
     * 
     * @return expiry month
     */
    @Override
    public int getExpiryMonth() {
        return this.expiryMonth;
    }

    /**
     * returns this expiry year
     * 
     * @return expiry year
     */
    @Override
    public int getExpiryYear() {
        return this.expiryYear;
    }

    /**
     * returns this high level type
     * 
     * @return high level type
     */
    @Override
    public String getHighLevelType() {
        return HIGH_LEVEL_TYPE;
    }

    /**
     * returns this type
     * 
     * @return type
     */
    @Override
    public String getType() {
        return TYPE;
    }
    
    /**
     * only returns true if all data fields of object matches this instance
     * 
     * @params object
     * @return whether the input equals this
     */
    @Override
    public boolean equals(Object object) {
        CardPayment cardPayment = (CardPayment)object;

        return super.equals(object) && cardNumber == cardPayment.cardNumber && 
            expiryMonth == cardPayment.expiryMonth && 
                expiryYear == cardPayment.expiryYear;
    }

    /**
     * returns a string representation of this type
     * followed by transaction name, card number, expiry month and year
     * 
     * @return this instance in string format
     */
    @Override
    public String toString() {
        return "CardPayment (" + getTransactionName() +
            ") cardNumber: " + getCardNumber() + ", expiryMonth: " +
                getExpiryMonth() + ", expiryYear: " + getExpiryYear();
    }
}

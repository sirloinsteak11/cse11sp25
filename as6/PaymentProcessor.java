///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              PaymentProcessor
// Files:              PaymentProcessor.java
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

import java.util.ArrayList;

/**
 * holds a list of payments and validates payments
 * 
 * Bugs: none afaik
 * 
 * @author Deonn Almirol
 */
public class PaymentProcessor {
    private ArrayList<Payment> paymentList;

    private static final int MAX_EXPIRY_MONTH = 12;
    private static final int MIN_EXPIRY_MONTH_2025 = 7;
    private static final int MIN_EXPIRY_YEAR = 2025;
    private static final int MAX_STARS = 10;
    private static final String UCSD_EMAIL = "@ucsd.edu";
    private static final String CARD_PAYMENT = "CardPayment";
    private static final String ELECTRONIC_PAYMENT = "ElectronicPayment";
    private static final String DEBIT_PAYMENT = "DebitPayment";
    private static final String CREDIT_PAYMENT = "CreditPayment";
    private static final String MOBILE_PAYMENT = "MobilePayment";
    private static final String DIGITAL_WALLET_PAYMENT = "DigitalWalletPayment";

    private static final Character AT_SYMBOL = '@';

    /**
        No arg constructor initializes empty payment list
    */
    public PaymentProcessor() {
        this.paymentList = new ArrayList<Payment>();
    }

    /**
     * adds provided payment to paymentList
     * 
     * @param payment payment to add
     */
    public void addToPaymentList(Payment payment) {
        this.paymentList.add(payment);
    }

    /**
     * adds multiple payments to paymentList
     * 
     * @param payments array of payments
     */
    public void addToPaymentList(Payment[] payments) {
        for (int i = 0; i < payments.length; i++) {
            this.paymentList.add(payments[i]);
        }
    }

    /**
     * checks whether a payment by transaction name exists in the payment
     * list
     * 
     * @param paymentTransactionName name of transcation
     * @return whether the payment is in the list
     */
    public boolean hasPayment(String paymentTransactionName) {
        boolean found = false;

        for (int i = 0; i < paymentList.size(); i++) {
            if (paymentList.get(i).getTransactionName() == 
                paymentTransactionName) {

                found = true;
                break;
            }
        }

        return found;
    }

    /**
     * finds payment by transcation name and removes it from the paymentList
     * 
     * @param paymentTransactionName name of payment
     * @return the payment or null if no payment was found
     */
    public Payment getAndRemovePayment(String paymentTransactionName) {
        Payment payment = null;
        int index = -1;

        if (hasPayment(paymentTransactionName)) {
            for (int i = 0; i < this.paymentList.size(); i++) {
                if (paymentList.get(i).getTransactionName() == 
                    paymentTransactionName) {

                    payment = paymentList.get(i);
                    index = i;
                    break;
                }
            }
        }

        if (index != -1) {
            paymentList.remove(index);
        }

        return payment;
    }

    /**
     * compares the risk between two payments regardless of type
     * 
     * @param paymentOne the first payment
     * @param paymentTwo the second payment
     * @return -1 if one less than 2, 0 if same, 1 if one more than two
     */
    public static int compareRisk(Payment paymentOne, Payment paymentTwo) {
        // payment 1 high level type
        double riskOne = 0;
        double riskTwo = 0;

        if (paymentOne.getHighLevelType() == CARD_PAYMENT) {
            riskOne = paymentOne.calculateCardRisk();
        }
        if (paymentOne.getHighLevelType() == ELECTRONIC_PAYMENT) {
            riskOne = paymentOne.calculateDigitalRisk();
        }

        if (paymentTwo.getHighLevelType() == CARD_PAYMENT) {
            riskTwo = paymentTwo.calculateCardRisk();
        }
        if (paymentTwo.getHighLevelType() == ELECTRONIC_PAYMENT) {
            riskTwo = paymentTwo.calculateDigitalRisk();
        }
        
        if (riskOne < riskTwo) {
            return -1;
        }
        if (riskOne == riskTwo) {
            return 0;
        }
        if (riskOne > riskTwo) {
            return 1;
        }

        return 0;
    }

    /**
     * applies a price increase by increaseRate to a random payment in
     * paymentList
     * 
     * @param increaseRate the factor to increase payment
     * @return the random index of the payment in paymentList
     */
    public int applyPaymentSurge(double increaseRate) {
        
        double randNum = Math.random();
        double temp = (paymentList.size() - 1) * randNum;
        
        int numInt = (int)(Math.round(temp));

        if (increaseRate < 1) {
            return numInt;
        }

        Payment randPay = paymentList.get(numInt);

        double currentAmount = randPay.getAmount();
        double amountToSet = currentAmount * increaseRate;

        randPay.setAmount(amountToSet);

        return numInt;
    }

    /**
     * validates and ensures a payment can be processed
     * 
     * @param payment the payment to process
     * @return whether the payment can be processed successfully
     */
    public static boolean processPayment(Payment payment) {
        if (payment.getAmount() < 0) {
            return false;
        }

        if (payment.getHighLevelType() == CARD_PAYMENT) {
            if (payment.getExpiryYear() < MIN_EXPIRY_YEAR) {
                return false;
            }

            if (payment.getExpiryYear() == MIN_EXPIRY_YEAR) {
                if (payment.getExpiryMonth() < MIN_EXPIRY_MONTH_2025 || 
                    payment.getExpiryMonth() > MAX_EXPIRY_MONTH) {

                    return false;
                }
            }

            if (payment.getExpiryYear() > MIN_EXPIRY_YEAR) {
                if (payment.getExpiryMonth() < 1 || 
                    payment.getExpiryMonth() > MAX_EXPIRY_MONTH) {

                    return false;
                }
            }
        }

        

        if (payment.getHighLevelType() == ELECTRONIC_PAYMENT) {
            String emailAdd = payment.getEmailAddress();
            boolean occursMoreThanOnce = emailAdd
                .indexOf(AT_SYMBOL, emailAdd.indexOf(AT_SYMBOL) + 1) > -1;

            if (occursMoreThanOnce) {
                return false;
            }
        }

        if (payment.getType() == DEBIT_PAYMENT) {
            if (payment.getBankBalance() < 0) {
                return false;
            }

            if (payment.getMonthlyIncome() < 0) {
                return false;
            }
        }

        if (payment.getType() == CREDIT_PAYMENT) {
            if (payment.getCardBalance() < 0) {
                return false;
            }

            if (payment.getCreditLimit() < 0) {
                return false;
            }

            if (payment.getCreditLimit() < payment.getCardBalance()) {
                return false;
            }
        }

        if (payment.getType() == MOBILE_PAYMENT) {
            if (payment.getStarRating() < 0 || payment.getStarRating() > MAX_STARS) {
                return false;
            }
        }

        if (payment.getType() == DIGITAL_WALLET_PAYMENT) {
            if (payment.getNumTransactions() < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * The getter method of the paymentList member variable
     * @return the paymentList of the PaymentProcessor instance
     */
    public ArrayList<Payment> getPaymentList() {
        return this.paymentList;
    }
}

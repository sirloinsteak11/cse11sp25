///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Assignment6
// Files:              Assignment6.java
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
 * The main accessor of all payment and processor classes
 * 
 * Bugs: none afaik
 * 
 * @author Deonn almirol
 */
public class Assignment6 {

    /**
     * Print the payment array in a readable format. Optional to use
     * @param paymentArr An array of Payment instances
     */
    public static void printPaymentArray(Payment[] paymentArr) {
        System.out.println('[');
        for (int i = 0; i < paymentArr.length; i++) {
            System.out.print("  " + paymentArr[i]);
            System.out.println(',');
        }
        System.out.println(']');
    }

    /**
     * Print the payment arraylist in a readable format. Optional to use
     * @param paymentArr An arraylist of Payment instances
     */
    public static void printPaymentArray(ArrayList<Payment> paymentArr) {
        System.out.println('[');
        for (int i = 0; i < paymentArr.size(); i++) {
            System.out.print("  " + paymentArr.get(i));
            System.out.println(',');
        }
        System.out.println(']');
    }

    /**
     * runs unit tests and returns false if any fail
     * 
     * @return whether all unit tests pass
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static boolean unitTests() {
        // SETUP
        PaymentProcessor proc = new PaymentProcessor();

        // Add payments to a PaymentProcessor object
        MobilePayment m1 = new MobilePayment("Dinner", 10.0, 0,
                                        "sumu@ucsd.edu", "4445556666", 4);
        proc.addToPaymentList(m1);
        Payment[] paymentsToAdd = {
            new DebitPayment("Groceries", 42.5, 4444555566667777L,
                                12, 2025, 1200, 100),
            new MobilePayment("Forex", 3.0, 1,
                                        "sumu@ucsd.edu", "s@nyse.com", 10),
            new DebitPayment("Pharmacy", 4.77, 1111222233334444L,
                                1, 2027, 4000, 10000)
       	};
        proc.addToPaymentList(paymentsToAdd);

        // feel free to add more payments to PaymentProcessor!


        // TEST CASE 1
        // We provide a test case for applyPaymentSurge
        // Save all the original amounts of the payments
        double[] originalPrices = new double[proc.getPaymentList().size()];
        for (int i = 0; i < proc.getPaymentList().size(); i++) {
            originalPrices[i] = proc.getPaymentList().get(i).getAmount();
        }

        // Apply payment increases
        double increaseRate = 1.5;
	    int increaseIndex = proc.applyPaymentSurge(increaseRate);

        // Check paymentList amounts
        for (int i = 0; i < proc.getPaymentList().size(); i++) {
            Payment payment = proc.getPaymentList().get(i);
            if (i != increaseIndex || increaseRate < 1) {
                // Check that the Payment as this index is still the same
                if (payment.getAmount() != originalPrices[i]) {
                    // Payment has changed unexpectedly
                    System.out.println("applyPaymentSurge 1" +
                        " - Payment unexpectedly changed " +
                        "at index: " + i);
                    System.out.println(proc.getPaymentList());
                    return false;
                }
            } else {
                // Check that the Payment has the expected discounted amount
                double actualPrice = originalPrices[i] * increaseRate;
                if (payment.getAmount() != actualPrice) {
                    System.out.println("applyPaymentSurge 1" +
                        " - Payment does not have expected increased amount");
                    System.out.println(payment.getAmount());
                    System.out.println(actualPrice);
                    System.out.println(proc.getPaymentList());
                    return false;
                }
            }
        }

        //  test case 2 - compareRisk
        Payment t1p1 = proc.getPaymentList().get(1);
        Payment t1p2 = proc.getPaymentList().get(3);

        double riskOne = t1p1.calculateCardRisk();
        double riskTwo = t1p2.calculateCardRisk();



        int expectedResult = 0;
        if (riskOne > riskTwo) {
            expectedResult = 1;
        }
        if (riskOne == riskTwo) {
            expectedResult = 0;
        }
        if (riskOne < riskTwo) {
            expectedResult = -1;
        }

        int actualResult = PaymentProcessor.compareRisk(t1p1, t1p2);
        if (expectedResult != actualResult) {
            System.out.println(String
                .format("compareRisk actual result: %d expected result: %d", 
                expectedResult, actualResult));

            return false;
        }

        // test case 3 - applyPaymentSurge 2
        double[] originalPrices2 = new double[proc.getPaymentList().size()];
        for (int i = 0; i < proc.getPaymentList().size(); i++) {
            originalPrices2[i] = proc.getPaymentList().get(i).getAmount();
        }

        // Apply payment increases
        double increaseRate2 = 2.9;
	    int increaseIndex2 = proc.applyPaymentSurge(increaseRate2);

        // Check paymentList amounts
        for (int i = 0; i < proc.getPaymentList().size(); i++) {
            Payment payment = proc.getPaymentList().get(i);
            if (i != increaseIndex2 || increaseRate2 < 1) {
                // Check that the Payment as this index is still the same
                if (payment.getAmount() != originalPrices[i]) {
                    // Payment has changed unexpectedly
                    System.out.println("applyPaymentSurge 2" +
                        " - Payment unexpectedly changed " +
                        "at index: " + i);
                    System.out.println(proc.getPaymentList());
                    return false;
                }
            } else {
                // Check that the Payment has the expected discounted amount
                double actualPrice = originalPrices2[i] * increaseRate2;
                if (payment.getAmount() != actualPrice) {
                    System.out.println("applyPaymentSurge 2" +
                        " - Payment does not have expected increased amount");
                    System.out.println(payment.getAmount());
                    System.out.println(actualPrice);
                    System.out.println(proc.getPaymentList());
                    return false;
                }
            }
        }

        // test case 4 - processPayment
        boolean processTest = PaymentProcessor.processPayment(m1);
        boolean expectedValue = true;
        if (processTest != expectedValue) {
            System.out.println("processPayment should be showing true but shows false");
            return false;
        }

        return true;
    }

    /**
     * main entry point of Assignment6
     * 
     * @param args command line args
     */
    public static void main(String[] args) {
        // Perform unitTests
        if(unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("Failed test.\n");
            return;
        }

        // Don't need to write code in main!
    }
}

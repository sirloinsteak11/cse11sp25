// TODO: File header
import java.util.ArrayList;

// TODO: Class header
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

    // TODO: Method header
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


        // TODO: write more test cases HERE!!!!!!


        return true;
    }

    // TODO: Method header
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

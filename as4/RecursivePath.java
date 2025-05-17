///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              RecursivePath
// Files:              RecursivePath.java
// Quarter:            CSE11 Spring 2025
//
// Author:             Deonn Almirol
// Email:              dalmirol@ucsd.edu
// Instructor's Name:  Ben Ochoa
//
///////////////////////////////////////////////////////////////////////////////
//                   STUDENTS WHO GET HELP COMPLETE THIS SECTION
//                   You must fully acknowledge and credit sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but roommates, relatives, strangers, etc do.
//
// Persons:          N/A
//
// Online sources:   w3schools - Java language reference
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Contains one method to practice writing recursive functions
 * 
 * Bugs: maximizeBazaarProfit - does not work
 * 
 * @author Deonn Almirol
 */
public class RecursivePath {

    public static int recursionCallCount = 0; // WARNING: DO NOT MODIFY THIS
    // LINE FOR STAFF TO TEST RECURSIVE CALLS

    // Feel free to add more constants

    /**
     * returns maximum profit from bazaar
     * 
     * @param path the array of possible paths
     * @param position the starting position
     * @return the maximum profit
     */
    public static int maximizeBazaarProfit(int[] path, int position) {
        recursionCallCount++; // WARNING: DO NOT MODIFY THIS LINE, FOR STAFF
        // TO TEST RECURSIVE CALLS

        /*
        static int profit = 0;

        if (position == path.length) {
            return;
        }
        */

        return -1;
    }

    /**
     * Performs unit tests and returns false if any fail
     * 
     * @return if all tests pass
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        // Test(s) for maximizeBazaarProfit
        // Test case 1:
        int[] bazaarPath = {1, -6, 10};
        System.out.println("maximizeBazaarProfit Test 1: " +
            "expectedOutput is 11 but actual output is " +
                maximizeBazaarProfit(bazaarPath, 0));
        if (maximizeBazaarProfit(bazaarPath, 0) != 11) {
            System.out.println("maximizeBazaarProfit test 1 failed");
            return false;
        }
        return true;
    }

    /**
     * The main entry point of RecursivePath
     * 
     * @param args command line args
     */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("ERROR: Failed test.\n");
            return;
        }
    }
}
///////////////////////////////////////////////////////////////////////////////
//
// Title:              Selections
// Files:              Selections.java
// Quarter:            CSE11 Spring 2025
//
// Author:             Deonn Almirol
// Email:              dalmirol@ucsd.edu
// Instructor's Name:  Ben Ochoa
//
///////////////////////////////////////////////////////////////////////////////
//
// Persons:          N/A
//
// Online sources:   https://www.w3schools.com/java/default.asp
//                   - Java language reference
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Selections class. Contains calculation methods for UCSD's SACL Office,
 * Birch Aquarium, and UCSD IT
 * 
 * Bugs: None AFAIK
 * 
 * @author Deonn Almirol
 */
public class Selections {

    // Feel free to create extra variables if necessary.

    /* Variables used for carpool */
    private final static String CARPOOL_INVALID_INPUT_ERR = 
                            "Invalid input provided";
    private final static String CARPOOL_UNAVILABLE_MSG =
                            "Carpool canceled. Match chance is too low";
    private final static String CARPOOL_MATCHED_BASE =
                            "Information entered. Match chance: ";
    private final static String CHANCE_HIGH = "high";
    private final static String CHANCE_MEDIUM = "medium";
    private final static int END_OF_WEEK = 6;
    private final static int END_OF_DAY = 23;
    private final static int BEGINNING_OF_LOW = 2;
    private final static int END_OF_LOW = 6;

    /* Variables used for calculatePrice */
    private final static double ADULT_PRICE = 30.0;
    private final static double CHILDREN_PRICE = 25.0;
    private final static double PREMIUM_ADDITION = 0.2;
    private final static String EASTER_CODE = "EASTERFUN";
    private final static String UCSD_CODE = "UCSDAFFILATION";
    private final static String NEWMEMBER_CODE = "NEWMEMBER";
    private final static double EASTER_UCSD_DISCOUNT = 0.15;
    private final static double NEWMEMBER_SUBTRACT = 20.0;
    private final static double NEWMEMBER_CONDITION = 100.0;
    private final static int MAX_CHILDREN = 4;

    /* Variables used for validEmailAddress */
    private final static String ACCOUNT_DOMAIN_ERR =
                            "Incorrect format or domain";
    private final static String ACCOUNT_USERNAME_ERR =
                            "Incorrect username";
    private final static String ACCOUNT_VALID = "Valid email address";
    private final static String UCSD_DOMAIN_1 = "@ucsd.edu";
    private final static String UCSD_DOMAIN_2 = "@eng.ucsd.edu";
    private final static int MAX_LENGTH = 5;

    /**
     * Returns a string saying how likely of finding a carpool match
     * based on dayOfWeek and hourOfDay
     * 
     * @param dayOfWeek The day of the week (0 is sunday, 6 is saturday)
     * @param hourOfDay The hour of the day (0-23 inclusive)
     * @return The string saying chances of matching or if cannot match
     */
    public static String carpool(int dayOfWeek, int hourOfDay) {

        String chance = "";

        if (dayOfWeek < 0 || dayOfWeek > END_OF_WEEK) {
            return CARPOOL_INVALID_INPUT_ERR;
        }

        if (hourOfDay < 0 || hourOfDay > END_OF_DAY) {
            return CARPOOL_INVALID_INPUT_ERR;
        }

        if (hourOfDay >= BEGINNING_OF_LOW  && hourOfDay <= END_OF_LOW) {
            return CARPOOL_UNAVILABLE_MSG;
        }

        if (dayOfWeek > 0 && dayOfWeek < END_OF_WEEK) {
            chance = CHANCE_MEDIUM;
        }

        if (dayOfWeek == 0 || dayOfWeek == END_OF_WEEK) {
            chance = CHANCE_HIGH;
        }

        return String.format("%s%s", CARPOOL_MATCHED_BASE, chance);
    }

    /**
     * Calculates total price based on all provided inputs
     * 
     * @param numAdults total number of adults
     * @param numChildren total number of children (max 4 per adult)
     * @param couponCode string for the coupon code
     * @param isPremium boolean whether premium pricing is used
     * @return the final dollar price
     */
    public static double calculatePrice(int numAdults, int numChildren, 
            String couponCode, boolean isPremium) {

        if (numAdults < 0 || numChildren < 0) {
            return 0f;
        }

        if (numChildren > MAX_CHILDREN * numAdults) {
            return 0f;
        }

        double adultTotal = numAdults * ADULT_PRICE;
        double childrenTotal = numChildren * CHILDREN_PRICE;

        double premium = 1f;

        double couponDiscount = 1f;

        if (isPremium == true) {
            premium = 1 + PREMIUM_ADDITION;
        }

        if (couponCode == EASTER_CODE || couponCode == UCSD_CODE) {
            couponDiscount = 1 - EASTER_UCSD_DISCOUNT;
        }

        double totalTotal = ((adultTotal + childrenTotal) * premium) 
                                * couponDiscount;

        if (couponCode == NEWMEMBER_CODE && 
                totalTotal > NEWMEMBER_CONDITION) {
            totalTotal -= NEWMEMBER_SUBTRACT;
        }

        return totalTotal;
    }

    /**
     * Determines whether the given address is valid in specified criteria
     * 
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param emailAddress email address of the user (must be within domain)
     * @return boolean that says whether the address is valid or not
     */
    public static boolean validEmailAddress(String firstName, String lastName, 
        String emailAddress) {
        
        if (emailAddress.contains(UCSD_DOMAIN_1) == false && 
                emailAddress.contains(UCSD_DOMAIN_2) == false) {

            System.out.print(ACCOUNT_DOMAIN_ERR);
            return false;
        }

        if (emailAddress.toLowerCase().charAt(0) != 
                firstName.toLowerCase().charAt(0)) {
            System.out.print(ACCOUNT_USERNAME_ERR);
            return false;
        }

        String firstNameTemp = firstName.toLowerCase()
            .substring(0, 1);
        String lastNameTemp = lastName;

        if (lastName.length() > MAX_LENGTH) {
            lastNameTemp = lastName.substring(0, MAX_LENGTH);
        }

        String testUsername = firstNameTemp.concat(lastNameTemp);

        if (emailAddress.contains(testUsername) == false) {
            System.out.print(ACCOUNT_USERNAME_ERR);
            return false;
        }

        String beforeAt = emailAddress.substring(0, 
                            emailAddress.indexOf("@"));

        if (beforeAt.length() < MAX_LENGTH) {
            System.out.print(ACCOUNT_USERNAME_ERR);
            return false;
        }

        System.out.print(ACCOUNT_VALID);
        return true;
    }

    /**
     * Performs unit tests and returns false if any fails
     * 
     * @return whether all tests passed or not
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE
    public static boolean unitTests() {
        System.out.println();

        // Test(s) for carpool
        // Test case 1: dayOfWeek = 5, hourOfDay = 11
        int day1 = 5;
        int hour1 = 11;
        String expectedCarpool1 =
                "Information entered. Match chance: medium";
        String actualCarpool1 = carpool(day1, hour1);
        System.out.println("carpool Output 1: " + actualCarpool1);
        if (!actualCarpool1.equals(expectedCarpool1)) {
            System.out.println("FAILED: carpool 1");
            return false;
        }
        System.out.println();

        // Test case 2: dayOfWeek = 0, hourOfDay = 11
        int day2 = 0;
        int hour2 = 11;
        String expectedCarpool2 =
                "Information entered. Match chance: high";
        String actualCarpool2 = carpool(day2, hour2);
        System.out.println("carpool Output 2: " + actualCarpool2);
        if (!actualCarpool2.equals(expectedCarpool2)) {
            System.out.println("FAILED: carpool 2");
            return false;
        }
        System.out.println();

        // Test case 3: dayOfWeek = 6, hourOfDay = 2
        int day3 = 6;
        int hour3 = 2;
        String expectedCarpool3 =
                "Carpool canceled. Match chance is too low";
        String actualCarpool3 = carpool(day3, hour3);
        System.out.println("carpool Output 3: " + actualCarpool3);
        if (!actualCarpool3.equals(expectedCarpool3)) {
            System.out.println("FAILED: carpool 3");
            return false;
        }
        System.out.println();

        // Test(s) for calculatePrice
        // Test case 1: numAdults = 1, numChildren = 4, 
        // couponCode = "UCSDAFFILATION", isPremium = true
        int adult1 = 1;
        int children1 = 4;
        String coupon1 = UCSD_CODE;
        boolean premium1 = true;
        String expectedPrice1 = "132.60";
        String actualPrice1 = String.format("%.2f",
                calculatePrice(adult1, children1, coupon1, premium1));
        System.out.println("calculatePrice Output 1: " + actualPrice1);
        if (!actualPrice1.equals(expectedPrice1)) {
            System.out.println("FAILED: calculatePrice 1");
            return false;
        }
        System.out.println();

        // Test case 2: numAdults = 2, numChildren = 5, 
        // couponCode = "NEWMEMBER", isPremium = false
        int adult2 = 2;
        int children2 = 5;
        String coupon2 = NEWMEMBER_CODE;
        boolean premium2 = false;
        String expectedPrice2 = "165.00";
        String actualPrice2 = String.format("%.2f",
                calculatePrice(adult2, children2, coupon2, premium2));
        System.out.println("calculatePrice Output 2: " + actualPrice2);
        if (!actualPrice2.equals(expectedPrice2)) {
            System.out.println("FAILED: calculatePrice 2");
            return false;
        }
        System.out.println();

        // Test case 2: numAdults = 1, numChildren = 5, 
        // couponCode = "NEWMEMBER", isPremium = true
        int adult3 = 1;
        int children3 = 5;
        String coupon3 = NEWMEMBER_CODE;
        boolean premium3 = true;
        String expectedPrice3 = "0.00";
        String actualPrice3 = String.format("%.2f",
                calculatePrice(adult3, children3, coupon3, premium3));
        System.out.println("calculatePrice Output 3: " + actualPrice3);
        if (!actualPrice3.equals(expectedPrice3)) {
            System.out.println("FAILED: calculatePrice 3");
            return false;
        }
        System.out.println();

        // Test(s) for validEmailAddress
        // Test case 1: firstName = "Jane", lastName = "Doe",
        // emailAddress = "jdoe123@ucsd.edu"
        String firstName1 = "Jane";
        String lastName1 = "Doe";
        String email1 = "jDoe123@ucsd.edu";
        boolean expectedAccountReturn1 = true;
        String expectedAccountPrint1 = "Valid email address";
        System.out.println("Expected validEmailAddress Print 1:");
        System.out.println(expectedAccountPrint1);
        System.out.println("Expected validEmailAddress Return 1:");
        System.out.println(expectedAccountReturn1);
        System.out.println("-----------------");
        System.out.println("Actual validEmailAddress Print 1:");
        boolean actualAccountReturn1 = validEmailAddress(firstName1, 
                    lastName1, email1);
        System.out.println();
        System.out.println("Actual validEmailAddress Return 1:");
        System.out.println(actualAccountReturn1);
        if (actualAccountReturn1 != expectedAccountReturn1) {
            System.out.println("FAILED: validEmailAddress 1");
            return false;
        }
        System.out.println();

        // Test case 2: firstName = "Will", lastName = "Hudson",
        // emailAddress = "wHudso99@ucsd.edu"
        String firstName2 = "Will";
        String lastName2 = "Hudson";
        String email2 = "wHudso99@ucsd.edu";
        boolean expectedAccountReturn2 = true;
        String expectedAccountPrint2 = "Valid email address";
        System.out.println("Expected validEmailAddress Print 2:");
        System.out.println(expectedAccountPrint2);
        System.out.println("Expected validEmailAddress Return 2:");
        System.out.println(expectedAccountReturn2);
        System.out.println("-----------------");
        System.out.println("Actual validEmailAddress Print 2:");
        boolean actualAccountReturn2 = validEmailAddress(firstName2, 
                    lastName2, email2);
        System.out.println();
        System.out.println("Actual validEmailAddress Return 2:");
        System.out.println(actualAccountReturn2);
        if (actualAccountReturn2 != expectedAccountReturn2) {
            System.out.println("FAILED: validEmailAddress 2");
            return false;
        }
        System.out.println();

        // Test case 3: firstName = "Kyle", lastName = "Clark",
        // emailAddress = "kyle.clark@gmail.com"
        String firstName3 = "Kyle";
        String lastName3 = "Clark";
        String email3 = "kyle.clark@gmail.com";
        boolean expectedAccountReturn3 = false;
        String expectedAccountPrint3 = "Incorrect format or domain";
        System.out.println("Expected validEmailAddress Print 3:");
        System.out.println(expectedAccountPrint3);
        System.out.println("Expected validEmailAddress Return 3:");
        System.out.println(expectedAccountReturn3);
        System.out.println("-----------------");
        System.out.println("Actual validEmailAddress Print 3:");
        boolean actualAccountReturn3 = validEmailAddress(firstName3, 
                    lastName3, email3);
        System.out.println();
        System.out.println("Actual validEmailAddress Return 3:");
        System.out.println(actualAccountReturn3);
        if (actualAccountReturn3 != expectedAccountReturn3) {
            System.out.println("FAILED: validEmailAddress 3");
            return false;
        }
        System.out.println();

        // All test cases passed!
        return true;
    }

    /**
     * Main entry point of Selections.java
     * 
     * @param args command line arguments (not used)
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("ERROR: Failed test.\n");
            return;
        }
    }
}

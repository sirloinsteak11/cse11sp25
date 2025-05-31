///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              PA7Tester
// Files:              PA7Tester.java
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
 * Tester class for Programming Assignment 7.
 */
public class PA7Tester {

    /**
     * Runs unit tests for the aircraft classes.
     *
     * @return true if all tests pass, false otherwise.
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        boolean passed = true;

        // Example test: Test default PassengerPlane constructor
        PassengerPlane defaultPlane = new PassengerPlane();
        if (defaultPlane.getPassengerCapacity() != 1) {
            System.out.println("PA7Tester Test 1 FAILED: " +
                    "Default PassengerPlane capacity.");
            passed = false;
        }

        // Add your unit tests here based on the assignment description
        // for constructors, getters, setters, equals, toString,
        // operational methods, and file I/O.

        // equals test1
        PassengerPlane pp1 = new PassengerPlane("HE11", "boeing 777",
                                                1000, 15, 4, "Yuh engine",
                                                "Rob Lock Airlines", 
                                                "RLA7003", 165);
        
        PassengerPlane pp2 = new PassengerPlane("HE11", "boeing 777",
                                                1000, 15, 4, "Yuh engine",
                                                "Rob Lock Airlines", 
                                                "RLA7003", 165);

        if (pp1.equals(pp2) == false) {
            System.out.println("equals test 1 fail: " +
                            "equal comparison shows false");
            passed = false;
        }

        // equals test 2
        Helicopter copter1 = new Helicopter(null, null, 0, 0, 0, passed,
                            null, 0);

        if (pp1.equals(copter1)) {
            System.out.println("equals test 2: program said"
                            + " apples and oranges are the same");

            passed = false;
        }

        // toString tests for passengerplane, privatejet, and helicopter
        // toString test 1
        String pp1String = pp1.toString();
        String expectedString1 = "Model: boeing 777, Tail#: HE11, "
                                +"Status: Secured\r\n" + //
                                "\tWingspan: 15 m, Engines: 4 x Yuh engine, "
                                +"Gear Down: Yes\r\n" + //
                                "\tPassenger Info: Flight RLA7003 "
                                +"(Rob Lock Airlines), Occupancy: 0/165";

        if (pp1String != expectedString1) {
            System.out.println("passengerplane string does not match");
            passed = false;
        }

        // toString test 2
        PrivateJet privateJet = new PrivateJet("bluh", "cessna super",
                                                600, 10, 1, "front rotor",
                                                1500, "pooper snooter");

        String privateJetString = privateJet.toString();


        



        return passed;
    }

    /**
     * Main method to run the unit tests.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("PA7Tester: All unit tests passed.\n");
        } else {
            System.out.println("PA7Tester: ERROR: Failed test.\n");
            return;
        }
    }
}
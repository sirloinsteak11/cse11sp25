///////////////////////////////////////////////////////////////////////////////
//                   
// Title:              Assignment 2
// Files:              Assignment2.java
// Quarter:            CSE11 Spring 2025
//
// Author:             Deonn Almirol
// Email:              dalmirol@ucsd.edu
// Instructor's Name:  Ben Ochoa
//
///////////////////////////////////////////////////////////////////////////////
//
// Persons:             N/A          
//
// Online sources:      https://www.w3schools.com/java/default.asp 
//                        - Java documentation for how to use methods 
//                            within the assignment
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.lang.Math;   // Import the Math class
import java.util.Scanner; // Import the Scanner class

//
// NOTE: YOU SHOULD NOT HAVE TO IMPORT ANY OTHER PACKAGES TO COMPLETE THIS
// ASSIGNMENT.
//
// If you add extraneous packages (intentionally or not), then your
// code might fail Gradescope compilation.
//

/**
 * Main assignment class. Contains all methods described 
 * in the assignment as well as main method
 *
 * Bugs: None AFAIK
 *
 * @author Deonn Almirol
 */
public class Assignment2 {

    // Feel free to create extra constants if necessary.

    // Used to prompt the user for their name
    private final static String PROMPT_MSG_NAME = "Please enter your name.";

    // 1/3 for cone volume
    private final static int CONE_VOLUME_CONST = 3;

    /**
     * Calculates volume of a cone using doubles radius and height
     * 
     * @param radius The radius of the cone
     * @param height The height of the cone
     * @return The volume of the cone described by its formula
    */
    public static double coneVolume(double radius, double height) {
        double volume = 0.0; // Placeholder
        
        volume = (Math.PI * (radius*radius) * height)/CONE_VOLUME_CONST;

        return volume;
    }

    /**
     * Calculates the distance between two points in Chebyshev space
     * 
     * @param x1 The x-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y1 The y-coordiante of the first point
     * @param y2 The y-coordinate of the second point
     * @return This distance between the provided points in Chebyshev space
     */
    // Note the parameter order from the spec: x1, x2, y1, y2
    public static double chebyshevDistance(double x1, double x2, double y1,
                                           double y2) {
        double distance = 0.0; // Placeholder
        
        double x_abs = Math.abs(x1 - x2);
        double y_abs = Math.abs(y1 - y2);

        distance = Math.max(x_abs, y_abs);

        return distance;
    }

    /**
     * Calculates the maximum between 2 quotients 
     * whose values are not truncated (max((a/b), (c/d)))
     * 
     * @param a The value to be divided by b
     * @param b The value to divide a
     * @param c The value to be divided by d
     * @param d The value to divide c
     * @return The maximum between the two quotients
     */
    public static double maxCorrectDivision(int a, int b, int c, int d) {
        double ans = 0.0; // Placeholder
        
        double a_db = (double)a;
        double b_db = (double)b;
        double c_db = (double)c;
        double d_db = (double)d;

        double a_b = a_db / b_db;
        double c_d = c_db / d_db;

        ans = Math.max(a_b, c_d);

        return ans;
    }

    /**
     * Assuming num is a string containing only digits, 
     * takes the substring between a and b and adds it to the 
     * substring of b to end of string and appends it to num
     * 
     * @param num The number string
     * @param a The beginning index of substring
     * @param b The second index to be used
     * @return The final string with the sum appended to the end
     */
    public static int appendPartsSum(String num, int a, int b) {
        int ans = 0; // Placeholder
        
        String first_sub = num.substring(a, b);
        String second_sub = num.substring(b);

        int first_toInt = Integer.parseInt(first_sub);
        int second_toInt = Integer.parseInt(second_sub);

        int first_second = first_toInt + second_toInt;

        String first_second_string = Integer.toString(first_second);

        String new_append = num + first_second_string;

        ans = Integer.parseInt(new_append);

        return ans;
    }

    /**
     * Concatenates word 1 and word 2 and converts characters 
     * from index i to (len - i) to uppercase
     * 
     * @param word1 The first string
     * @param word2 The second string
     * @param i An integer value in string form
     * @return The final concatenated and modified string
     */
    public static String concatAndModify(String word1, String word2, String i) {
        String result = ""; // Placeholder
        
        String word1_word2 = word1.concat(word2);
        
        int len = word1_word2.length();

        int i_int = Integer.parseInt(i);

        result = word1_word2;

        char[] result_array = result.toCharArray();
        for (int index = i_int; index < len - i_int+1; index++) {
            result_array[index] = Character.toUpperCase(result_array[index]);
        }

        result = String.valueOf(result_array);

        return result;
    }

    /**
     * Replaces the string b in string a with string c case-insensitively
     * @param a The string to be modified
     * @param b The string containing what to replace
     * @param c The replacement string
     * @return The final modified string
     */
    public static String replaceSubstringCaseInsensitive(String a, String b,
                                                         String c) {
        String answer = ""; // Placeholder
        
        int index = a.indexOf(b.toLowerCase());

        String a_substring = a.substring(0, index);
        String another_substring = a.substring(a_substring.length() + index);

        answer = a_substring + c + another_substring;

        return answer;
    }

    /**
     * Creates a vertical hourglass using the specified 7-row format
     * 
     * @return The hourglass string
     */
    public static String createVerticalHourglass() {
        String res = ""; // Placeholder

        res = "*******\n *****\n  ***\n   *\n  ***\n *****\n*******";

        return res;
    }

    /**
     * Unit tests for the seven implemented methods.
     * 
     * @return Whether all unit tests have passed (as a boolean)
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        System.out.println();

        boolean passed = true; // Keep track of overall test results

        // Test(s) for coneVolume
        // Test case 1: (Using sample from spec)
        double radius = 1.0;
        double height = 3.0;
        String ans_double = String.format("%.2f", coneVolume(radius, height));
        String expectedOutput_double = "3.14";
        System.out.println("coneVolume Output 1: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: coneVolume 1");
            passed = false; // Return false if any test fails
        }
        
        // Test case 2:
        radius = 2.0;
        height = 6.0;
        ans_double = String.format("%.2f", coneVolume(radius, height));
        expectedOutput_double = "25.13";
        System.out.println("coneVolume Output 2: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: coneVolume 2");
            passed = false; // Return false if any test fails
        }
        
        // Test case 3:
        radius = 1.5;
        height = 4.8;
        ans_double = String.format("%.2f", coneVolume(radius, height));
        expectedOutput_double = "11.31";
        System.out.println("coneVolume Output 3: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: coneVolume 3");
            passed = false; // Return false if any test fails
        }


        // Test(s) for chebyshevDistance
        // Test case 1: (Using sample from spec)
        double x1_c = 4.5;
        double x2_c = 5.4;
        double y1_c = 3.7;
        double y2_c = 7.3;

        double test1_distance = chebyshevDistance(x1_c, x2_c, y1_c, y2_c);
        ans_double = String.format("%.2f", test1_distance);
        expectedOutput_double = "3.60";
        System.out.println("chebyshevDistance Output 1: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: chebyshevDistance 1");
            passed = false;
        }
        
        // Test case 2:
        x1_c = 5.3;
        x2_c = 5.4;
        y1_c = 9.9;
        y2_c = 2.0;

        double test2_distance = chebyshevDistance(x1_c, x2_c, y1_c, y2_c);
        ans_double = String.format("%.2f", test2_distance);
        expectedOutput_double = "7.90";
        System.out.println("chebyshevDistance Output 2: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: chebyshevDistance 2");
            passed = false;
        }
        
        // Test case 3:
        x1_c = 4.8;
        x2_c = 3.7;
        y1_c = 2.6;
        y2_c = 3.3;

        double test3_distance = chebyshevDistance(x1_c, x2_c, y1_c, y2_c);
        ans_double = String.format("%.2f", test3_distance);
        expectedOutput_double = "1.10";
        System.out.println("chebyshevDistance Output 3: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: chebyshevDistance 3");
            passed = false;
        }


        // Test(s) for maxCorrectDivision
        // Test case 1: (Using values from spec)
        int a_m = 10;
        int b_m = 7;
        int c_m = 2;
        int d_m = 3;

        double test1_division = maxCorrectDivision(a_m, b_m, c_m, d_m);
        ans_double = String.format("%.2f", test1_division);
        expectedOutput_double = "1.43";
        System.out.println("maxCorrectDivision Output 1: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: maxCorrectDivision 1");
            passed = false;
        }
        
        // Test case 2:
        a_m = 12;
        b_m = 5;
        c_m = 8;
        d_m = 9;

        double test2_division = maxCorrectDivision(a_m, b_m, c_m, d_m);
        ans_double = String.format("%.2f", test2_division);
        expectedOutput_double = "2.40";
        System.out.println("maxCorrectDivision Output 2: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: maxCorrectDivision 2");
            passed = false;
        }
        
        // Test case 2:
        a_m = 15;
        b_m = 5;
        c_m = 3;
        d_m = 4;

        double test3_division = maxCorrectDivision(a_m, b_m, c_m, d_m);
        ans_double = String.format("%.2f", test3_division);
        expectedOutput_double = "3.00";
        
        System.out.println("maxCorrectDivision Output 3: " + ans_double);
        System.out.println();
        if (!ans_double.equals(expectedOutput_double)) {
            System.out.println("FAILED: maxCorrectDivision 3");
            passed = false;
        }


        // Test(s) for appendPartsSum
        // Test case 1: (Using sample from spec)
        String num_a = "12345";
        int a_a = 1;
        int b_a = 3;
        int ans_int = appendPartsSum(num_a, a_a, b_a);
        int expectedOutput_int = 1234568;
        
        System.out.println("appendPartsSum Output 1: " + ans_int);
        System.out.println();
        if (ans_int != expectedOutput_int) {
            System.out.println("FAILED: appendPartsSum 1");
            passed = false;
        }
        
        // Test case 2:
        num_a = "23457";
        a_a = 2;
        b_a = 3;
        ans_int = appendPartsSum(num_a, a_a, b_a);
        expectedOutput_int = 2345761;
        
        System.out.println("appendPartsSum Output 2: " + ans_int);
        System.out.println();
        if (ans_int != expectedOutput_int) {
            System.out.println("FAILED: appendPartsSum 2");
            passed = false;
        }
        
        // Test case 3:
        num_a = "1111111";
        a_a = 3;
        b_a = 5;
        ans_int = appendPartsSum(num_a, a_a, b_a);
        expectedOutput_int = 111111122;
        
        System.out.println("appendPartsSum Output 3: " + ans_int);
        System.out.println();
        if (ans_int != expectedOutput_int) {
            System.out.println("FAILED: appendPartsSum 3");
            passed = false;
        }

        // Test(s) for concatAndModify
        // Test case 1: (Using sample from spec)
        String word1_cm = "hello";
        String word2_cm = "world";
        String i_cm = "3";
        String ans_string = concatAndModify(word1_cm, word2_cm, i_cm);
        String expectedOutput_string = "helLOWORld";
        
        System.out.println("concatAndModify Output 1: " + ans_string);
        System.out.println();
        if (!ans_string.equals(expectedOutput_string)) {
            System.out.println("FAILED: concatAndModify 1");
            passed = false;
        }
        
        // Test case 2:
        word1_cm = "fizz";
        word2_cm = "buzz";
        i_cm = "3";
        ans_string = concatAndModify(word1_cm, word2_cm, i_cm);
        expectedOutput_string = "fizZBUzz";
        
        System.out.println("concatAndModify Output 2: " + ans_string);
        System.out.println();
        if (!ans_string.equals(expectedOutput_string)) {
            System.out.println("FAILED: concatAndModify 2");
            passed = false;
        }
        
        // Test case 2:
        word1_cm = "supercalifragil";
        word2_cm = "isticexpialidocious";
        i_cm = "12";
        ans_string = concatAndModify(word1_cm, word2_cm, i_cm);
        expectedOutput_string = "supercalifraGILISTICEXPialidocious";
        
        System.out.println("concatAndModify Output 3: " + ans_string);
        System.out.println();
        if (!ans_string.equals(expectedOutput_string)) {
            System.out.println("FAILED: concatAndModify 3");
            passed = false;
        }


        // Test(s) for replaceSubstringCaseInsensitive
        // Test case 1: (Using sample from spec)
        String base_r = "helloworld";
        String sub_r = "World";
        String patch_r = "Sara";
        ans_string = replaceSubstringCaseInsensitive(base_r, sub_r, patch_r);
        expectedOutput_string = "helloSara";
        
        System.out.println("replaceSubstringCaseInsensitive Output 1: " 
                            + ans_string);
        System.out.println();
        if (!ans_string.equals(expectedOutput_string)) {
            System.out.println("FAILED: replaceSubstringCaseInsensitive 1");
            passed = false;
        }
        
        // Test case 2:
        base_r = "rigatoni";
        sub_r = "TONI";
        patch_r = "killa";
        ans_string = replaceSubstringCaseInsensitive(base_r, sub_r, patch_r);
        expectedOutput_string = "rigakilla";
        
        System.out.println("replaceSubstringCaseInsensitive Output 2: " 
                            + ans_string);
        System.out.println();
        if (!ans_string.equals(expectedOutput_string)) {
            System.out.println("FAILED: replaceSubstringCaseInsensitive 2");
            passed = false;
        }
        
        // Test case 3:
        base_r = "that_one_day_i_tried_to_ride_a_horse";
        sub_r = "_tried_to";
        patch_r = "_fucking_learned_how_to";
        ans_string = replaceSubstringCaseInsensitive(base_r, sub_r, patch_r);
        expectedOutput_string = 
                    "that_one_day_i_fucking_learned_how_to_a_horse";
        
        System.out.println("replaceSubstringCaseInsensitive Output 3: " 
                            + ans_string);
        System.out.println();
        if (!ans_string.equals(expectedOutput_string)) {
            System.out.println("FAILED: replaceSubstringCaseInsensitive 3");
            passed = false;
        }

        // If all tests passed up to this point
        return passed; // Return the overall result
    }

    /**
     * Entry point for execution of the program.
     * Runs unit tests, then prompts user for name and prints a greeting.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Run unit tests first
        if (unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("ERROR: unit test failure.\n");
            return; // Exit if tests fail
        }

        // Set up scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println(PROMPT_MSG_NAME); // Print prompt for name

        String userName = scanner.nextLine();
        
        System.out.println(String.format(
            "Hello %s! Nice to meet you and welcome to CSE 11!", userName));

        // Close the scanner
        scanner.close();
    }
}
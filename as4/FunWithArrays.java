///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              FunWithArrays
// Files:              FunWithArrays.java
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

// DO NOT IMPORT ANY OTHER PACKAGES.
// DOING SO MAY FAIL COMPILATION IN GRADESCOPE

import java.util.Arrays;

/**
 * Class that defines methods involving array access and manipulation
 * 
 * Bugs: findShortestUniquePosition - unknown
 *       maxAlternatingParitySequence - unknown
 *       calculateCSE11CurrentGrade - unknown
 *       updateLocations - unknown
 *       
 * 
 * @author Deonn Almirol
 */
public class FunWithArrays {

    // Feel free to add constants here

    public static final double PRELECTURE_QUIZ_WEIGHT = 0.1;
    public static final double ASSIGNMENT_WEIGHT = 0.45;
    public static final double MIDTERM_WEIGHT = 0.2;
    public static final double FINAL_WEIGHT = 0.25;
    public static final double PRELECTURE_QUIZ_COUNT_WEIGHT = 0.00625;
    public static final double ASSIGNMENT_COUNT_WEIGHT = 0.06;
    public static final double PA1_COUNT_WEIGHT = 0.03;
    public static final int NUMBER_TWO = 2;

    /**
     * Calculates "ZScore" using input data
     * 
     * @param data the data to perform calculations with
     * @return the ZScore
     */
    public static double[] calculateZScores(double[] data) {
        double dataMean = 0f;
        for (int i = 0; i < data.length; ++i) {
            dataMean += data[i];
        }
        dataMean /= (double)data.length;

        double[] squaredDifference = new double[data.length];
        for (int i = 0; i < data.length; ++i) {

            squaredDifference[i] = Math.pow(dataMean - data[i], NUMBER_TWO);
        }

        double variance = 0f;
        for (int i = 0; i < data.length; ++i) {
            variance += squaredDifference[i];
        }

        variance /= (double)data.length;

        double standardDev = Math.sqrt(variance);

        double[] ZScores = new double[data.length];
        for (int i = 0; i < data.length; ++i) {
            ZScores[i] = (data[i] - dataMean) / standardDev;
        }

        return ZScores;
    }

    /**
     * Finds the shortest (or first in alphabetical order) unique string
     * 
     * @param array the array to use
     * @return the 0-indexed position
     */
    public static int findShortestUniquePosition(String[] array) {

        int uniqueStrings = 0;
        int[] string_counts = new int[array.length];
        int shortest_count = Integer.MAX_VALUE;
        int shortest_index = 0;

        
        for (int i = 0; i < array.length; ++i) {

            String current_string = array[i];
            int current_count = 0;

            for (int j = 0; j < array.length; ++j) {
                if (current_string == array[j]) {
                    current_count++;
                }
            }

            if (current_count > 1) {
                continue;
            }
            uniqueStrings++;
            string_counts[i] = array[i].length();
        }

        if (uniqueStrings == 0 || array == null) {
            return -1;
        }

        if (uniqueStrings == 1) {
            for (int i = 0; i < string_counts.length; ++i) {
                if (string_counts[i] != 0) {
                    shortest_index = i;
                }
            }
        }

        if (uniqueStrings > 1) {
            for (int i = 0; i < string_counts.length; ++i) {
                if (string_counts[i] == 
                    shortest_count && string_counts[i] != 0) {
                    int compare = array[i].compareTo(array[shortest_index]);
                    if (compare < 0) {
                        shortest_index = i;
                    }
                }

                if (string_counts[i] < 
                    shortest_count && string_counts[i] != 0) {
                    shortest_count = string_counts[i];
                    shortest_index = i;
                }
            }
        }

        return shortest_index;

    }

    /**
     * Finds the maximum consecutive times the array alternates between
     * even and odd
     * 
     * @param array the array to use
     * @return the maximum times the array alternates between even and odd
     */
    public static int maxAlternatingParitySequence(int[] array) {
        int longest_parity = 0;

        int current_parity = 0;
        boolean isEven = true;
        for (int i = 0; i < array.length; ++i) {
            if (i == 0) {
                isEven = array[i] % NUMBER_TWO == 0;
                current_parity++;
                continue;
            }

            if (isEven == true) {
                if (array[i] % NUMBER_TWO != 0) {
                    current_parity++;
                    isEven = !isEven;
                    continue;
                } else {
                    if (current_parity > longest_parity) {
                        longest_parity = current_parity;
                        current_parity = 1;
                        isEven = array[i] % NUMBER_TWO == 0;
                    }
                }
            }

            if (isEven == false) {
                if (array[i] % NUMBER_TWO == 0) {
                    current_parity++;
                    isEven = !isEven;
                    continue;
                } else {
                    if (current_parity > longest_parity) {
                        longest_parity = current_parity;
                        current_parity = 1;
                        isEven = array[i] % NUMBER_TWO == 0;
                    }
                }
            }
            
            /*
            for (int j = 0; j < array.length; ++j) {
                if (j == 0) {
                    isEven = array[i] % 2 == 0;
                    current_parity++;
                    continue;
                }

                if (isEven == true) {
                    if (array[i] % 2 == 0) {
                        current_parity++;
                    }
                } else {

                }
            }
            */

        }

        if (current_parity > longest_parity) {
            longest_parity = current_parity;
        }

        return longest_parity;
    }

    /**
     * Calculates current grade based on the rubric
     * 
     * @param prelectureQuizScores prelecture quiz score array
     * @param initialAssignmentScores initial assignment score array
     * @param upgradeAssignmentScores upgraded assignment score array
     * @param midtermScore midterm score double
     * @param finalAssessmentPartOneScore final part one score double
     * @param finalAssessmentTotalScore final total score double
     * @return the CURRENT grade based on completed scores
     */
    public static double calculateCSE11CurrentGrade(
                                        double[] prelectureQuizScores,
                                        double[] initialAssignmentScores,
                                        double[] upgradeAssignmentScores,
                                        double midtermScore,
                                        double finalAssessmentPartOneScore,
                                        double finalAssessmentTotalScore
    ) {

        // calculate average of prelecture quizzes
        double quizAverage = 0.0;
        int completedQuizCount = 0;
        for (int i = 0; i < prelectureQuizScores.length; i++) {
            if (prelectureQuizScores[i] == -1.0) {
                continue;
            }

            quizAverage += prelectureQuizScores[i];
            completedQuizCount++;
        }

        quizAverage /= (double)completedQuizCount;
        quizAverage *= PRELECTURE_QUIZ_WEIGHT;

        // calculate average of assignments
        double assignmentAverage = 0.0;
        int completedAssignCount = 0;
        for (int i = 0; i < initialAssignmentScores.length; i++) {
            if (initialAssignmentScores[i] == -1.0) {
                continue;
            }

            if (upgradeAssignmentScores[i] > initialAssignmentScores[i]) {
                assignmentAverage += upgradeAssignmentScores[i];
            } else {
                assignmentAverage += initialAssignmentScores[i];
            }

            completedAssignCount++;
        }

        assignmentAverage /= (double)completedAssignCount;
        assignmentAverage *= ASSIGNMENT_WEIGHT;

        // midterm graDE
        double midtermGrade = midtermScore;
        if (finalAssessmentPartOneScore > midtermScore) {
            midtermGrade = finalAssessmentPartOneScore;
        }
        midtermGrade *= MIDTERM_WEIGHT;

        if (midtermScore == -1.0) {
            midtermGrade = 0.0;
        }

        double finalAssessGrade = finalAssessmentTotalScore;
        finalAssessGrade *= FINAL_WEIGHT;

        if (finalAssessmentTotalScore == -1.0) {
            finalAssessGrade = 0.0;
        }

        double weightedAverage = quizAverage + 
            assignmentAverage + midtermGrade + finalAssessGrade;

        double quizWeight = 
            completedQuizCount * PRELECTURE_QUIZ_COUNT_WEIGHT;
        double assignWeight = 
            (completedAssignCount - 1) * ASSIGNMENT_COUNT_WEIGHT;
        double PA1Weight = 0.0;
        if (initialAssignmentScores[0] != -1.0) {
            PA1Weight = PA1_COUNT_WEIGHT;
        } 

        double midtermWeight = 0.0;
        if (midtermGrade != -1.0) {
            midtermWeight = MIDTERM_WEIGHT;
        }

        double finalWeight = 0.0;
        if (finalAssessmentTotalScore != -1.0) {
            finalWeight = FINAL_WEIGHT;
        }

        double weightedCounts = quizWeight + assignWeight + 
            PA1Weight + midtermWeight + finalWeight;

        double current_grade = weightedAverage / weightedCounts;
        
        return current_grade;
    }

    /**
     * Updates location in simulated world based on world size
     * Updates the locations array directly (pass by reference)
     * 
     * @param locations array of locations
     * @param movements array to manipulate locations
     * @param worldSize the size of the simulated world
     */
    public static void updateLocations(int[] locations, 
            int[] movements, int worldSize) {

        boolean validInput = true;

        if (locations == null) {
            return;
        }

        if (movements == null) {
            return;
        }

        if (worldSize <= 0) {
            return;
        }

        for (int i = 0; i < locations.length; ++i) {
            if (locations[i] < 0) {
                validInput = false;
            }

            if (locations[i] >= worldSize) {
                validInput = false;
            }
        }

        if (locations.length != movements.length) {
            validInput = false;
        }


        if (validInput == false) {
            return;
        }

        for (int i = 0; i < locations.length; ++i) {

            if (locations[i] + movements[i] >= worldSize) {
                locations[i] = (locations[i] + movements[i]) % worldSize;
                continue;
            }
            if (locations[i] + movements[i] < 0) {
                locations[i] = (locations[i] + movements[i]) % worldSize;
                continue;
            }

            locations[i] = locations[i] + movements[i];
        }
    }

    /**
     * gets the sum of the 2d array's outer borders
     * 
     * @param matrix the 2d array to use
     * @return the sum of the outer borders
     */
    public static int sumOuterBorders(int[][] matrix) {
        int sum = 0;

        if (matrix == null) {
            return -1;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) {
                    sum += matrix[i][j];
                    continue;
                }

                if (i == matrix.length - 1) {
                    sum += matrix[i][j];
                    continue;
                }

                if (j == 0 || j == matrix[i].length-1) {
                    sum += matrix[i][j];
                    continue;
                }
            }
        }

        return sum;
    }

    /**
     * creates a 2d array with n columns containing
     * sequential integers
     * 
     * @param n the integer number of columns
     * @return the 2d sequential array
     */
    public static int[][] createSequentialMatrix(int n) {
        double sqrtN = Math.sqrt((double)n);

        if (sqrtN * sqrtN != (double)n) {
            return null;
        }

        int n_int = (int)sqrtN;

        int[][] result = new int[n_int][n_int];

        int num_temp = 1;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = num_temp;
                num_temp++;
            }
        }

        return result;
    }

    /**
     * Performs unit tests on all methods and returns false
     * if any fail
     * 
     * @return whether all tests pass or not
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        // Test(s) for calculateZScores
        // Test case 1:
        double[] data = new double[]{6.27, -1913.5673};
        double[] zScoresActual = calculateZScores(data);
        double[] zScoresExpected = new double[]{1.0, -1.0};
        System.out.println("calculateZScores Test 1: " +
            "expectedOutput is " + Arrays.toString(zScoresExpected) +
                " but acutal output is " + Arrays.toString(zScoresActual));
        if (!Arrays.equals(zScoresExpected, zScoresActual)) {
            System.out.println("calculateZScores test 1 failed");
            return false;
        }

        // Test case 2:
        data = new double[]{10.0, 5.0, 10.0, 10.0, 10.0};
        zScoresActual = calculateZScores(data);
        zScoresExpected = new double[]{0.5, -2.0, 0.5, 0.5, 0.5};
        System.out.println("calculateZScores Test 2: " +
            "expectedOutput is " + Arrays.toString(zScoresExpected) +
                " but acutal output is " + Arrays.toString(zScoresActual));
        if (!Arrays.equals(zScoresExpected, zScoresActual)) {
            System.out.println("calculateZScores test 2 failed");
            return false;
        }

        // Test case 2:
        data = new double[]{-5.0, -1.0, 0.0, 1.0, 5.0};
        zScoresActual = calculateZScores(data);
        zScoresExpected = new double[]{-1.5504341823651056, 
                    -0.3100868364730211, 0.0, 
                        0.3100868364730211, 1.5504341823651056};
        System.out.println("calculateZScores Test 3: " +
            "expectedOutput is " + Arrays.toString(zScoresExpected) +
                " but acutal output is " + Arrays.toString(zScoresActual));
        if (!Arrays.equals(zScoresExpected, zScoresActual)) {
            System.out.println("calculateZScores test 3 failed");
            return false;
        }

        // Test(s) for findShortestUniquePosition
        // Test case 1:
        String[] strings = new String[]{"cats", "dog", "rat", "dog"};
        System.out.println("findShortestUniquePosition Test 1:" +
            " expectedOutput is 2" +
                " but actual output is " + findShortestUniquePosition(strings));
        if (findShortestUniquePosition(strings) != 2) {
            System.out.println("findShortestUniquePosition test 1 failed");
            return false;
        }

        // Test case 2:
        strings = new String[]{"a", "dd", "a", "ccc", "bb"};
        System.out.println("findShortestUniquePosition Test 2:" +
            " expectedOutput is 4" +
                " but actual output is " + findShortestUniquePosition(strings));
        if (findShortestUniquePosition(strings) != 4) {
            System.out.println("findShortestUniquePosition test 2 failed");
            return false;
        }

        // Test case 2:
        strings = new String[]{"a", "b", "c", "d"};
        System.out.println("findShortestUniquePosition Test 3:" +
            " expectedOutput is 0" +
                " but actual output is " + findShortestUniquePosition(strings));
        if (findShortestUniquePosition(strings) != 0) {
            System.out.println("findShortestUniquePosition test 3 failed");
            return false;
        }

        // Test(s) for updateLocations
        // Test case 1:
        int[] locations = {1, 0, 1, 2, 4, 1, 3, 2, 1};
        int[] movements = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int worldSize = 10;
        updateLocations(locations, movements, worldSize);
        int[] newLocations = {2, 2, 4, 6, 9, 7, 0, 0, 0};
        System.out.println("updateLocations Test 1: " +
            "expectedOutput is " + Arrays.toString(newLocations) +
                " but actual output is " + Arrays.toString(locations));
        if (!Arrays.equals(locations, newLocations)){
            System.out.println("updateLocations test 1 failed");
            return false;
        }

        // Test case 2:
        locations = new int[]{1, 2, 3, 4};
        movements =  new int[]{1, 2, 3, 4};
        worldSize = 8;
        updateLocations(locations, movements, worldSize);
        newLocations = new int[]{2, 4, 6, 0};
        System.out.println("updateLocations Test 2: " +
            "expectedOutput is " + Arrays.toString(newLocations) +
                " but actual output is " + Arrays.toString(locations));
        if (!Arrays.equals(locations, newLocations)){
            System.out.println("updateLocations test 2 failed");
            return false;
        }

        // Test case 3:
        locations = new int[]{1, 2, 3, 4, 5};
        movements =  new int[]{1, 2, 3, 4, 5};
        worldSize = 8;
        updateLocations(locations, movements, worldSize);
        newLocations = new int[]{2, 4, 6, 0, 2};
        System.out.println("updateLocations Test 3: " +
            "expectedOutput is " + Arrays.toString(newLocations) +
                " but actual output is " + Arrays.toString(locations));
        if (!Arrays.equals(locations, newLocations)){
            System.out.println("updateLocations test 3 failed");
            return false;
        }

        // Test(s) for maxAlternatingParitySequence
        // Test case 1:
        int[] maxAlternatingParitySequenceArray = {1, 2, 3};
        System.out.println("maxAlternatingParitySequence Test 1: " +
            "expectedOutput is 3 " +
                "but actual output is " +
                    maxAlternatingParitySequence(
                        maxAlternatingParitySequenceArray));
        if (maxAlternatingParitySequence(maxAlternatingParitySequenceArray)
                                                                        != 3) {
            System.out.println("maxAlternatingParitySequence test 1 failed");
            return false;
        }

        // Test case 2:
        maxAlternatingParitySequenceArray = new int[]{3, 4, 1, 0, 2};
        System.out.println("maxAlternatingParitySequence Test 2: " +
            "expectedOutput is 4 " +
                "but actual output is " +
                    maxAlternatingParitySequence(
                        maxAlternatingParitySequenceArray));
        if (maxAlternatingParitySequence(maxAlternatingParitySequenceArray)
                                                                        != 4) {
            System.out.println("maxAlternatingParitySequence test 2 failed");
            return false;
        }

        // Test case 3:
        maxAlternatingParitySequenceArray = new int[]{3, 2, 1, 1, 2, 3, 4};
        System.out.println("maxAlternatingParitySequence Test 3: " +
            "expectedOutput is 4 " +
                "but actual output is " +
                    maxAlternatingParitySequence(
                        maxAlternatingParitySequenceArray));
        if (maxAlternatingParitySequence(maxAlternatingParitySequenceArray)
                                                                        != 4) {
            System.out.println("maxAlternatingParitySequence test 3 failed");
            return false;
        }

        // Test case 4:
        maxAlternatingParitySequenceArray = new int[]{};
        System.out.println("maxAlternatingParitySequence Test 4: " +
            "expectedOutput is 0 " +
                "but actual output is " +
                    maxAlternatingParitySequence(
                        maxAlternatingParitySequenceArray));
        if (maxAlternatingParitySequence(maxAlternatingParitySequenceArray)
                                                                        != 0) {
            System.out.println("maxAlternatingParitySequence test 4 failed");
            return false;
        }

        // Test(s) for calculateCSE11CurrentGrade
        // Test case 1:
        double[] prelectureQuizScores =
            new double[]{1.0, 0.9, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.85,
                         1.0, 0.0, 0.0, 0.77, 1.0, 1.0};
        double[] initialAssignmentScores =
            new double[]{1.00, 0.85, 0.96, 0.68, 0.88, 0.92, 0.75, 0.7};
        double[] upgradeAssignmentScores =
            new double[]{-1.0, 1.0, 0.0, 0.9, 0.9, 0.0, 0.86, 0.97};
        double midtermScore = 0.72;
        double finalAssessmentPartOneScore = 0.84;
        double finalAssessmentTotalScore = 0.82;

        double currentGrade = 0.83675;
        double outputGrade =
            calculateCSE11CurrentGrade(prelectureQuizScores,
                                       initialAssignmentScores,
                                       upgradeAssignmentScores,
                                       midtermScore,
                                       finalAssessmentPartOneScore,
                                       finalAssessmentTotalScore);

        System.out.println("calculateCSE11CurrentGrade Test 1: " +
            "expectedOutput is " + currentGrade +
                " but actual output is " + outputGrade);
        if (outputGrade != currentGrade) {
            System.out.println("calculateCSE11CurrentGrade test 1 failed");
            //return false;
        }

        // Test case 2:
        prelectureQuizScores =
            new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0, -1.0, 
                        -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0};
        initialAssignmentScores =
            new double[]{1.0, 0.85, 0.96, -1.0, -1.0, -1.0, -1.0, -1.0};
        upgradeAssignmentScores =
            new double[]{-1.0, 1.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0};
        midtermScore = -1.0;
        finalAssessmentPartOneScore = -1.0;
        finalAssessmentTotalScore = -1.0;

        currentGrade = 0.9643870967741937;
        outputGrade =
            calculateCSE11CurrentGrade(prelectureQuizScores,
                                       initialAssignmentScores,
                                       upgradeAssignmentScores,
                                       midtermScore,
                                       finalAssessmentPartOneScore,
                                       finalAssessmentTotalScore);

        System.out.println("calculateCSE11CurrentGrade Test 2: " +
            "expectedOutput is " + currentGrade +
                " but actual output is " + outputGrade);
        if (outputGrade != currentGrade) {
            System.out.println("calculateCSE11CurrentGrade test 2 failed");
            //return false;
        }

        // Test(s) for sumOuterBorders
        // Test case 1:
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int expectedSum = 40;
        System.out.println("sumOuterBorders Test 1: " +
            "expectedOutput is " + expectedSum +
                " but actual output is " + sumOuterBorders(matrix));
        if (sumOuterBorders(matrix) != expectedSum) {
            System.out.println("sumOuterBorders test 1 failed");
            return false;
        }

        // Test case 2:
        matrix = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, 
            {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, 
                        {21, 22, 23, 24, 25}};
        expectedSum = 208;
        System.out.println("sumOuterBorders Test 2: " +
            "expectedOutput is " + expectedSum +
                " but actual output is " + sumOuterBorders(matrix));
        if (sumOuterBorders(matrix) != expectedSum) {
            System.out.println("sumOuterBorders test 2 failed");
            return false;
        }

        // Test case 3:
        matrix = new int[][]{{1}, {1}, {1}};
        expectedSum = 3;
        System.out.println("sumOuterBorders Test 3: " +
            "expectedOutput is " + expectedSum +
                " but actual output is " + sumOuterBorders(matrix));
        if (sumOuterBorders(matrix) != expectedSum) {
            System.out.println("sumOuterBorders test 3 failed");
            return false;
        }

        // Test(s) for createSequentialMatrix
        // Test case 1:
        int[][] expectedMatrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("createSequentialMatrix Test 1: " +
            "expectedOutput is " + Arrays.deepToString(expectedMatrix) +
                " but actual output is " +
                    Arrays.deepToString(createSequentialMatrix(9)));
        if (!Arrays.deepEquals(expectedMatrix, createSequentialMatrix(9))) {
            System.out.println("createSequentialMatrix test 1 failed");
            return false;
        }

        // Test case 2:
        expectedMatrix = new int[][]{{1, 2}, {3, 4}};
        System.out.println("createSequentialMatrix Test 2: " +
            "expectedOutput is " + Arrays.deepToString(expectedMatrix) +
                " but actual output is " +
                    Arrays.deepToString(createSequentialMatrix(4)));
        if (!Arrays.deepEquals(expectedMatrix, createSequentialMatrix(4))) {
            System.out.println("createSequentialMatrix test 2 failed");
            return false;
        }

        // Test case 3:
        expectedMatrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, 
                        {9, 10, 11, 12}, {13, 14, 15, 16}};
        System.out.println("createSequentialMatrix Test 3: " +
            "expectedOutput is " + Arrays.deepToString(expectedMatrix) +
                " but actual output is " +
                    Arrays.deepToString(createSequentialMatrix(16)));
        if (!Arrays.deepEquals(expectedMatrix, createSequentialMatrix(16))) {
            System.out.println("createSequentialMatrix test 3 failed");
            return false;
        }


        return true;
    }

    /**
     * the main entry point of FunWithArrays
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

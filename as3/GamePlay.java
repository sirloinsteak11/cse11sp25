///////////////////////////////////////////////////////////////////////////////
//
// Title:              GamePlay
// Files:              GamePlay.java
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
 * GamePlay class. Contains methods for calculating damage and printing
 * damage output
 * 
 * Bugs: None AFAIK
 * 
 * @author Deonn Almirol
 */
public class GamePlay {

    // Feel free to create extra variables if necessary.

    /* Variables used for calculateDamage */
    private final static int MODIFIER_LOW = -5;
    private final static int MODIFIER_HIGH = 10;
    private final static int WEAPON_MOD = 2;

    /* Variables used for dealDamage */
    private final static String INVALID_WEAPON = "Invalid weapon name";
    private final static String INVALID_DAMAGE = 
                            "Invalid damage calculation";
    private final static String ATTACK_MSG_BASE_1 = "Player used ";
    private final static String ATTACK_MSG_BASE_2 = " to attack!";
    private final static String MISS_TARGET_MSG = "Oops! Missed the target.";
    private final static String DAMAGE_DEALT_BASE = "Damage dealt: ";
    private final static String WEAPON_AXE = "AXE";
    private final static String WEAPON_CLAYMORE = "CLAYMORE";
    private final static String WEAPON_SWORD = "SWORD";
    private final static String WEAPON_DAGGER = "DAGGER";

    /**
     * Calculates total damage and returns it
     * 
     * @param diceRoll randomized base damage
     * @param modifier value to be added to base damage
     * @param isCriticalHit boolean that doubles damage if true
     * @param isHeavyWeapon boolean that adds 2 damage if true
     * @return the final damage value
     */
    public static int calculateDamage(int diceRoll, int modifier, 
            boolean isCriticalHit, boolean isHeavyWeapon) {
        int damage = 0;

        if (diceRoll < 1) {
            return -1;
        }

        if (modifier < MODIFIER_LOW || modifier > MODIFIER_HIGH) {
            return -1;
        }

        if (isCriticalHit == true) {
            damage += diceRoll * WEAPON_MOD;
        } else {
            damage += diceRoll;
        }

        if (isHeavyWeapon == true) {
            damage += WEAPON_MOD;
        } else {
            damage -= WEAPON_MOD;
        }

        damage += modifier;

        if (damage < 0) {
            return 0;
        }

        return damage;
    }

    /**
     * calls calculateDamage, formats and prints output to console
     * 
     * @param diceRoll randomized base damage
     * @param modifier value to be added to damage
     * @param isCriticalHit determines whether to double base damage
     * @param weapon string containing the weapon used
     */
    public static void dealDamage(int diceRoll, int modifier, 
            boolean isCriticalHit, String weapon) {
        int damage = 0;

        // -1: invalid weapon, 0: light weapon, 1: heavy weapon
        int valid_weapon = -1;

        if (weapon == WEAPON_DAGGER || weapon == WEAPON_SWORD) {
            valid_weapon = 0;
        }
        
        if (weapon == WEAPON_CLAYMORE || weapon == WEAPON_AXE) {
            valid_weapon = 1;
        }

        if (valid_weapon == -1) {
            System.out.print(INVALID_WEAPON + "\n");
            return;
        }

        // turning valid_weapon into boolean format
        boolean isHeavyWeapon = (valid_weapon != 0);

        damage = calculateDamage(diceRoll, modifier, isCriticalHit, 
                                    isHeavyWeapon);

        if (damage == -1) {
            System.out.print(INVALID_DAMAGE + "\n");
            return;
        }

        if (damage == 0) {
            System.out.print(String.format("%s%s%s\n", 
                                ATTACK_MSG_BASE_1, weapon, 
                                    ATTACK_MSG_BASE_2));
            System.out.print(MISS_TARGET_MSG + "\n");
            return;
        }

        System.out.print(String.format("%s%s%s\n", 
                                ATTACK_MSG_BASE_1, weapon , 
                                    ATTACK_MSG_BASE_2));
        System.out.print(String.format("%s%d\n", DAMAGE_DEALT_BASE, damage));

    }

    /**
     * Performs unit tests and returns false if any fails
     * 
     * @return whether all tests passed or not
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE
    public static boolean unitTests() {
        System.out.println();

        // Test(s) for calculateDamage
        // Test case 1: diceRoll = 3, modifier = 1, 
        // isCriticalHit = true, isHeavyWeapon = false
        int calcDice1 = 3;
        int calcMod1 = 1;
        boolean calcIsCrit1 = true;
        boolean isHeavyWeapon1 = false;
        int expectedCalcDamage1 = 5;
        int actualCalcDamage1 = calculateDamage(calcDice1, calcMod1, 
                calcIsCrit1, isHeavyWeapon1);
        System.out.println("calculateDamage Output 1: " + actualCalcDamage1);
        if (actualCalcDamage1 != expectedCalcDamage1) {
            System.out.println("FAILED: calculateDamage 1");
            return false;
        }

        // Test case 2: diceRoll = 4, modifier = 3, 
        // isCriticalHit = true, isHeavyWeapon = true
        int calcDice2 = 4;
        int calcMod2 = 3;
        boolean calcIsCrit2 = true;
        boolean isHeavyWeapon2 = true;
        int expectedCalcDamage2 = 13;
        int actualCalcDamage2 = calculateDamage(calcDice2, calcMod2, 
                calcIsCrit2, isHeavyWeapon2);
        System.out.println("calculateDamage Output 2: " + actualCalcDamage2);
        if (actualCalcDamage2 != expectedCalcDamage2) {
            System.out.println("FAILED: calculateDamage 2");
            return false;
        }

        // Test case 3: diceRoll = 0, modifier = 3, 
        // isCriticalHit = true, isHeavyWeapon = true
        int calcDice3 = 0;
        int calcMod3 = 3;
        boolean calcIsCrit3 = true;
        boolean isHeavyWeapon3 = true;
        int expectedCalcDamage3 = -1;
        int actualCalcDamage3 = calculateDamage(calcDice3, calcMod3, 
                calcIsCrit3, isHeavyWeapon3);
        System.out.println("calculateDamage Output 3: " + actualCalcDamage3);
        if (actualCalcDamage3 != expectedCalcDamage3) {
            System.out.println("FAILED: calculateDamage 3");
            return false;
        }

        // Test(s) for dealDamage
        // Test case 1: diceRoll = 3, modifier = 1, 
        // isCriticalHit = true, weapon = "DAGGER"
        int dealDice1 = 3;
        int dealMod1 = 1;
        boolean dealIsCrit1 = true;
        String weapon1 = WEAPON_DAGGER;
        String expectedDealDamage1 = 
                "Player used DAGGER to attack!\nDamage dealt: 5\n";
        System.out.println("Expected dealDamage Output 1:");
        System.out.println(expectedDealDamage1);
        System.out.println("-----------------");
        System.out.println("Actual dealDamage Output 1:");
        dealDamage(dealDice1, dealMod1, dealIsCrit1, weapon1);
        System.out.println();

        // Test case 2: diceRoll = 4, modifier = 3, 
        // isCriticalHit = true, weapon = "AXE"
        int dealDice2 = 4;
        int dealMod2 = 3;
        boolean dealIsCrit2 = true;
        String weapon2 = WEAPON_AXE;
        String expectedDealDamage2 = 
                "Player used AXE to attack!\nDamage dealt: 13\n";
        System.out.println("Expected dealDamage Output 2:");
        System.out.println(expectedDealDamage2);
        System.out.println("-----------------");
        System.out.println("Actual dealDamage Output 2:");
        dealDamage(dealDice2, dealMod2, dealIsCrit2, weapon2);
        System.out.println();

        // Test case 3: diceRoll = 0, modifier = 3, 
        // isCriticalHit = true, weapon = "CLAYMORE"
        int dealDice3 = 0;
        int dealMod3 = 3;
        boolean dealIsCrit3 = true;
        String weapon3 = WEAPON_CLAYMORE;
        String expectedDealDamage3 = 
                "Invalid damage dalculation";
        System.out.println("Expected dealDamage Output 3:");
        System.out.println(expectedDealDamage3);
        System.out.println("-----------------");
        System.out.println("Actual dealDamage Output 3:");
        dealDamage(dealDice3, dealMod3, dealIsCrit3, weapon3);
        System.out.println();

        // All test cases passed
        return true;
    }

    /**
     * Main entry point of GamePlay.java
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
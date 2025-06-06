///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Bandit
// Files:              Bandit.java
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

import java.util.Random;

/**
 * concrete bandit class that extends from abstract humanoid class
 * 
 * @author deonn
 */
public class Bandit extends Humanoid {

    public static final int REST_HEAL = 30;
    private Random rand;

    /**
     * no arg constructor for bandit
     *  */
    public Bandit() {
        rand = new Random();
    }

    /**
     * parameterized constructor for bandit class
     * 
     * @param armor armor to set
     * @param vitality vitality to set
     * @param speed speed to set
     * @param intelligence intelligence to set
     * @param weapon weapon to set
     */
    public Bandit(int armor, int vitality, double speed, int intelligence, 
                    String weapon) {

        super(armor, vitality, speed, intelligence, weapon);
        rand = new Random();
    }

    /**
     * adds 30 health to this instance
     *  */
    @Override
    public void rest() {
        this.setVitality(getVitality() + REST_HEAL);
    }

    /**
     * calculates power based on the equipped weapon
     * then checks if the hit is critical by using random numbers
     * 
     * @return the final power calculation
     */
    @Override
    public double calculatePower() {
        double power =  0.0;

        int vitality = this.getVitality();
        int intelligence = this.getIntelligence();
        int armor = this.getArmor();
        double speed = this.getSpeed();

        if (this.getWeapon().equals("Axe")) {
            power = 0.65 * vitality + 0.35 * intelligence - 0.1 * speed;
        }
        if (this.getWeapon().equals("Crossbow")) {
            power = 0.25 * vitality + 0.5 * intelligence + 0.25 * speed;
        }
        if (this.getWeapon().equals("Shield")) {
            power = 0.7 * armor + 0.2 * vitality + 0.1 * speed 
                    - 0.2 * intelligence;
        }

        double critChance = rand.nextDouble();

        if (critChance > 0.6) {
            power *= 2;
        }

        return power;
    }

    /**
     * calls calculatePower and does damage to armor or health if
     * damage is greater than the monsters armor
     * 
     * @param monster the monster to strike
     * @return the randomly generated strike value
     */
    @Override
    public int strike(Monster monster) {
        double power = calculatePower();

        int intelligence = getIntelligence();

        double rangeMin = power - 0.15 * intelligence;
        double rangeMax = power + 0.25 * intelligence;

        double randomValue = rangeMin + (rangeMax - rangeMin) 
                            * rand.nextDouble();

        int strikeValue = (int)Math.floor(randomValue);

        if (strikeValue <= 0) {
            return 0;
        }
        if (strikeValue < monster.getArmor()) {
            monster.setArmor(monster.getArmor() - strikeValue);
        }
        if (strikeValue > monster.getArmor()) {
            monster.setVitality(monster.getVitality() - (strikeValue - 
                                monster.getArmor()));
                                
            monster.setArmor(0);
        }

        return strikeValue;
    }

    /**
     * simply returns a call on strike
     * 
     * @param monster the monster to attack
     * @return the strike value of the attack
     */
    @Override
    public int attack(Monster monster) {
        return strike(monster);
    }

    /**
     * returns a hardcoded array of weapon names
     * 
     * @return all available weapons
     */
    @Override
    protected String[] getAvailableWeapons() {
        return new String[]{"Axe", "Crossbow", "Shield", "Stick"};
    }

    /**
     * randomly generates a number which determines which value to double
     * 0 - armor
     * 1 - vitality
     * 2 - speed
     *  */
    @Override
    public void applyArmoryEffect() {
        super.applyArmoryEffect();

        int randomChoice = rand.nextInt(3);

        if (randomChoice == 0) {
            this.setArmor(this.getArmor() * 2);
        }
        if (randomChoice == 1) {
            this.setVitality(this.getVitality() * 2);
        }
        if (randomChoice == 2) {
            this.setSpeed(this.getSpeed() * 2);
        }
    }

    /**
     * humaoids have no special death rattles therefore this method will
     * always return false
     * 
     * @return false
     */
    @Override
    public boolean handleDeathrattle() {
        return false;
    }
}
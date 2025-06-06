///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Jubilex
// Files:              Jubilex.java
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
 * concrete class Jubilex extending from ooze
 * 
 * bugs: none afaik
 * 
 * @author deonn
 */
public class Jubilex extends Ooze {
    private Random rand;

    private static final int REST_ARMOR = 10000;

    /**
     * no arg constructor for jubilex
     * 
     */
    public Jubilex() {
        this.rand = new Random();
    }

    /**
     * parameterized consutrctor for jubilex
     * 
     * @param armor armor to set
     * @param vitality vitality to set
     * @param speed speed to set
     * @param volume volume to set
     * @param acidity acidity to set
     */
    public Jubilex(int armor, int vitality, double speed, 
                   int volume, int acidity) {

        super(armor, vitality, speed, volume, acidity);
        this.rand = new Random();

    }
    
    /**
     * jubilex will add 10000 armor wehn resting
     *  */
    @Override
    public void rest() {
        this.setArmor(getArmor() + REST_ARMOR);
    }

    /**
     * calculates power and returns it
     * will mult power by 100 based on crit chance
     * 
     * @return 
     */
    @Override
    public double calculatePower() {
        int vitality = getVitality();
        int acidity = getAcidity();
        int volume = getVolume();

        double power = 70 * vitality + 350 * volume + 100 * acidity;

        double critChance = rand.nextDouble();

        if (critChance > 0.01) {
            power *= 100;
        }

        return power;
    }

    /**
     * returns true 95% of the time and false 5% of the time
     * 
     * @return true or false
     */
    @Override
    public boolean corrode() {
        double randChance = rand.nextDouble();

        if (randChance >= 0.95) {
            return false;
        }
        return true;
    }

    /**
     * calculates power and does damage to monster and returns the 
     * damage value
     * 
     * @param monster the monster to attack
     * @return the damage value
     */
    @Override
    public int attack(Monster monster)  {
        double power = calculatePower();

        int volume = getVolume();

        double rangeMin = power - 0.005 * volume;
        double rangeMax = power + 0.5 * volume;

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
}
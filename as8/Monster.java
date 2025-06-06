///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Monster
// Files:              Monster.java
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
 * abstract monster class
 * 
 * bugs: none afaik
 * 
 * @author Deonn Almirol
 */
public abstract class Monster implements Comparable<Monster> {
    private int armor;
    private int vitality;
    private double speed;
    protected boolean poisoned;

    /**
     * no arg constructor for monster class
     * 
     */
    protected Monster() {
        this.armor = 0;
        this.vitality = 0;
        this.speed = 0.0;
        this.poisoned = false;
    }

     /**
     * parameterized constructor for monster class.
     * @param armor the armor value
     * @param vitality the vitality value
     * @param speed the speed value
     */
    protected Monster(int armor, int vitality, double speed) {
        this.armor = armor;
        this.vitality = vitality;
        this.speed = speed;
        this.poisoned = false;
    }

    /**
     * gets the armor value
     * @return the armor of the monster
     */
    public int getArmor() {
        return armor;
    }

    /**
     * gets the vitality value
     * @return the vitality of the monster
     */
    public int getVitality() {
        return vitality;
    }

    /**
     * gets the speed value
     * @return the speed of the monster
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * sets the armor value
     * @param armor the armor to set
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * sets the vitality value
     * @param vitality the vitality to set
     */
    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    /**
     * sets the speed value
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * returns if the monster is poisoned.
     * @return if poisoned
     */
    public boolean isPoisoned() {
        return poisoned;
    }

    /**
     * sets poisoned field to true
     * 
     */
    public void applyPoison() {
        this.poisoned = true;
    }

    /**
     * set poisoned field to false
     * 
     */
    public void clearPoison() {
        this.poisoned = false;
    }

    /**
     * returns true if this instance's vitality is greater than 0
     * @return if monster is alive
     */
    public boolean isAlive() {
        return this.vitality > 0;
    }

    /**
     * performs psecial ability on target
     * 
     * @param target the target to perform on
     */
    public void performSpecialAbility(Monster target) {
        return;
    }

    /**
     * compares the average of this instance and the target and returns
     * values based on their comparisons
     * 
     * @param monster the monster to compare to
     * @return -1 if less than, 0 if equal than, 1 if greater than
     */
    @Override
    public int compareTo(Monster monster) {
        double thisAverage = ((double)this.armor + (double)this.vitality +
                            this.speed) / 3;

        double theirAverage = ((double)monster.armor + (double)monster.vitality
                            + monster.speed) / 3;

        if (thisAverage < theirAverage) {
            return -1;
        }

        if (thisAverage == theirAverage) {
            return 0;
        }

        if (thisAverage > theirAverage) {
            return 1;
        }

        return 0;
    }

    /**
     * returns a string representation of this object
     * @return the string representation
     */
    @Override
    public String toString() {
        return "(" + getClass().getName() + ")" + " armor: " + getArmor() +
                "; vitality: " + getVitality() + "; speed: " + getSpeed();
    }

    public abstract void rest();
    public abstract double calculatePower();
    public abstract int attack(Monster monster);
    public abstract void applyArmoryEffect();
    public abstract boolean handleDeathrattle();
}
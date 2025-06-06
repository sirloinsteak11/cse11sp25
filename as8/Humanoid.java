///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Humnaoid
// Files:              Humanoid.java
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
 * abstract class for Humanoid
 * 
 * bugs: none afaik
 * 
 * @author Deonn Almirol
 */
public abstract class Humanoid extends Monster {
    private int intelligence;
    private String weapon;

    private Random rand;

    /**
     * no arg constructor for Humanoid
     * 
     */
    protected Humanoid() {
        this.intelligence = 0;
        this.weapon = null;
        this.rand = new Random();
    }

    /**
     * parameterized constructor for humanoid class
     * 
     * @param armor the armor to set
     * @param vitality the vitality to set
     * @param speed the speed to set
     * @param intelligence the intelligence to set
     * @param weapon the weapon to set
     */
    protected Humanoid(int armor, int vitality, double speed, int intelligence,
                        String weapon) {
        
        super(armor, vitality, speed);
        this.intelligence = intelligence;
        this.weapon = weapon;
        this.rand = new Random();

    }

    /**
     * gets intelligence
     * 
     * @return this intelligence
     */
    public int getIntelligence() {
        return this.intelligence;
    }

    /**
     * gets the current weapon
     * 
     * @return this weapon
     */
    public String getWeapon() {
        return this.weapon;
    }

    /**
     * sets a new intelligence level
     * 
     * @param intelligence the intelligence to set
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * sets a new weapon string
     * 
     * @param weapon the weapon to set
     */
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    /**
     * gets a random weapon from available weapons and sets the current
     * weapon
     */
    @Override
    public void applyArmoryEffect() {
        String[] weaponList = getAvailableWeapons();
        String randWeapon = weaponList[this.rand.nextInt(weaponList.length)];

        setWeapon(randWeapon);

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

    public abstract int strike(Monster monster);
    protected abstract String[] getAvailableWeapons();
}
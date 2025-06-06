///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Doppelganger
// Files:              Doppelganger.java
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

import java.util.ArrayList;
import java.util.Random;

/**
 * concrete subclass Doppelganger which also implements cloneable interface
 * 
 * bugs: none afaik
 * 
 * @author deonn
 */
public class Doppelganger extends Humanoid implements Cloneable {
    private ArrayList<Doppelganger> clones;
    private Random rand;

    /**
     * no arg constructor for Doppelganger
     *  */
    public Doppelganger() {
        this.clones = new ArrayList<>();

        this.rand = new Random();
    }

    /**
     * parameterized constructor for Doppelganger
     * 
     * @param armor armor to set
     * @param vitality vitality to set
     * @param speed speed to set
     * @param intelligence intelligence to set
     * @param weapon weapon to set
     */
    public Doppelganger(int armor, int vitality, double speed, 
                        int intelligence, String weapon) {

        super(armor, vitality, speed, intelligence, weapon);
        this.clones = new ArrayList<>();
        this.rand = new Random();

    }

    /**
     * returns a deep copy of this Doppelganger
     * and returns it with an empty clones list
     * 
     * @return the cloned Doppelganger
     * @throws CloneNotSupportedException 
     */
    @Override
    protected Doppelganger clone() throws CloneNotSupportedException {
        Doppelganger clone = null;
        Doppelganger result = null;

        try {
            
            clone = (Doppelganger)super.clone();
            result = new Doppelganger(clone.getArmor(),
                                      clone.getVitality(),
                                      clone.getSpeed(),
                                      clone.getIntelligence(),
                                      clone.getWeapon());

        } catch (CloneNotSupportedException e) {
            System.out.println(e);
            throw e;
        }

        

        result.clones = new ArrayList<>();

        return clone;
    }

    /**
     * gets this instances clones list
     * 
     * @return clones list
     */
    public ArrayList<Doppelganger> getClones() {
        return this.clones;
    }

    /**
     * a Doppelganger is alive if it has more than 0 health or more than
     * 0 clones
     * 
     * @return whether the Doppelganger is alive
     */
    @Override
    public boolean isAlive() {
        if (this.getVitality() > 0 || this.getClones().size() > 0) {
            return true;
        }

        return false;
    }

    /**
     * adds 10 to this instances vitality and does the same
     * for each perpetual clone that is tied to this one
     *  */
    @Override
    public void rest() {
        this.setVitality(getVitality() + 10);

        for (Doppelganger clone : this.getClones()) {
            clone.rest();
        }
    }

    /**
     * 
     * calculates power for this instance and does the same for all
     * perpetual clones and adds their power to the total power
     * @return total power
     */
    @Override
    public double calculatePower() {
        double power = 0.0;

        int armor = getArmor();
        int vitality = getVitality();
        int intelligence = getIntelligence();
        double speed = getSpeed();

        String weapon = this.getWeapon();

        if (weapon.equals("Staff")) {
            power = 0.35 * vitality + 0.3 * intelligence - 0.6 * speed;
        }

        if (weapon.equals("Dagger")) {
            power = 0.05 * vitality + 0.15 * intelligence + 0.8 * speed;
        }

        if (weapon.equals("Rapier")) {
            power = 0.4 * armor + 0.2 * intelligence + 0.5 * speed;
        }

        for (Doppelganger clone : this.getClones()) {
            power += clone.calculatePower();
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

        double rangeMin = power - 0.5 * intelligence;
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
        return new String[]{"Staff", "Dagger", "Rapier", "Stick"};
    }

    /**
     * rrandomly generates 0 to 6 clones and adds them to clone list
     *  */
    @Override
    public void applyArmoryEffect() {
        super.applyArmoryEffect();

        double rangeMin = 0.0;
        double rangeMax = 6.0;

        double randomValue = rangeMin + (rangeMax - rangeMin) 
                            * rand.nextDouble();

        int numClones = (int)Math.floor(randomValue);

        for (int i = 0; i < numClones; i++) {
            try {
                this.getClones().add(this.clone());
            } catch (CloneNotSupportedException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * will inherit the stats of a clone if clones list is not empty and
     * returns true, otherwise simply returns false 
     * 
     * @return true if can come back to life, false if cannot
     */
    @Override
    public boolean handleDeathrattle() {
        if (this.getVitality() <= 0 && !this.clones.isEmpty()) {
            Doppelganger clone = this.clones.get(0);
            this.setVitality(clone.getVitality());
            this.setArmor(clone.getArmor());
            this.setIntelligence(clone.getIntelligence());
            this.setSpeed(clone.getSpeed());
            this.setWeapon(clone.getWeapon());

            this.clones.remove(clone);

            clearPoison();

            return true;
        }

        if (this.getVitality() > 0 || this.clones.isEmpty()) {
            return false;
        }

        return false;
    }
}
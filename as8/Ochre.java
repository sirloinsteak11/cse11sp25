///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Ochre
// Files:              Ochre.java
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
 * concrete class extending from ooze and implements
 * cloneable interface
 * 
 * @author deonn
 */
public class Ochre extends Ooze implements Cloneable {
    private ArrayList<Ochre> clones;
    private Random rand;

    /**
     * no arg constructor for ochre
     *  */
    public Ochre() {
        this.clones = new ArrayList<>();
        this.rand = new Random();
    }

    /**
     * parameterized constructor for ochre
     * 
     * @param armor 
     * @param vitality 
     * @param speed 
     * @param volume 
     * @param acidity 
     */
    public Ochre(int armor, int vitality, double speed, 
                 int volume, int acidity) {

        super(armor, vitality, speed, volume, acidity);
        this.clones = new ArrayList<>();
        this.rand = new Random();

    }

    /**
     * creates an ochre clone with half the volume of this instance
     * 
     * @return the ochre clone
     * @throws CloneNotSupportedException 
     */
    @Override
    protected Ochre clone() throws CloneNotSupportedException {
        if (this.getVolume() == 1) {
            throw new CloneNotSupportedException();
        }

        Ochre clone = (Ochre)super.clone();
        Ochre result = new Ochre(clone.getArmor(), clone.getVitality(), 
                                 clone.getSpeed(), clone.getVolume()/2, 
                                 clone.getAcidity());
        result.clones = new ArrayList<>();

        return result;

    }

    /**
     * returns this clones list
     * 
     * @return clones list
     */
    public ArrayList<Ochre> getClones() {
        return this.clones;
    }

    /**
     * returns true if health is greater than 0 or clones list
     * has more than 0
     * 
     */
    @Override
    public boolean isAlive()  {
        if (this.getVitality() > 0 || !this.getClones().isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * adds 20 armor to this and every clone
     *  */
    @Override
    public void rest() {
        this.setArmor(getVitality() + 20);

        for (Ochre clone : this.getClones()) {
            clone.rest();
        }
    }

    /**
     * sets power and then calls on all clones and adds the total power
     * 
     * @return the total power
     */
    @Override
    public double calculatePower() {
        int vitality = this.getVitality();
        int acidity = this.getAcidity();
        int volume = this.getVolume();

        double power = 0.7 * vitality + 0.35 * volume + acidity;

        for (Ochre clone : this.getClones()) {
            power += clone.calculatePower();
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
        boolean success = false;

        if (randChance <= 0.095) {
            return true;
        }

        for (Ochre clone : this.getClones()) {
            if (clone.corrode()) {
                success = true;
            }
        }

        return success;
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

        double rangeMin = power - 0.5 * volume;
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
            Ochre clone = this.clones.get(0);
            this.setVitality(clone.getVitality());
            this.setArmor(clone.getArmor());
            this.setSpeed(clone.getSpeed());
            this.setAcidity(clone.getAcidity());
            this.setVolume(clone.getVolume());

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
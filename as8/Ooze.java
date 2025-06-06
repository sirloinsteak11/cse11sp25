///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Ooze
// Files:              Ooze.java
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
 * abstract class for ooze extending from monster class
 * 
 * bugs: none afaik
 * 
 * @author deonn almirol
 */
public abstract class Ooze extends Monster {
    private int volume;
    private int acidity;

    /**
     * 
     * no arg constructor for ooze
     *  
     * */
    protected Ooze() {
        this.volume = 0;
        this.acidity = 0;
    }

    /**
     * parameterized constructor for ooze
     * 
     * @param armor armor to set
     * @param vitality vitality to set
     * @param speed speed to set
     * @param volume volume to set
     * @param acidity acidity to set
     */
    protected Ooze(int armor, int vitality, double speed, int volume, 
                    int acidity) {
        
        super(armor, vitality, speed);
        this.volume = volume;
        this.acidity = acidity;
        
    }

    /**
     * gets this volume
     * 
     * @return volume
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * returns this acidity
     * 
     * @return acidity
     */
    public int getAcidity() {
        return this.acidity;
    }

    /**
     * sets a new volume
     * 
     * @param volume volume to set
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    /**
     * sets a new acidity
     * 
     * @param acidity acidity to set
     */
    public void setAcidity(int acidity) {
        this.acidity = acidity;
    }

    /**
     * performs corrode ability on target and removes armor or applies poison
     * if no armor is present
     * 
     * @param target the target to perform on
     */
    @Override
    public void performSpecialAbility(Monster target) {
        boolean corrodeSuccess = corrode();

        if (corrodeSuccess && target.getArmor() > 0) {
            target.setArmor(0);
        }

        if (corrodeSuccess && target.getArmor() < 0) {
            target.applyPoison();
        }
    }

    /**
     * doubles the volume of this monster
     *  */
    @Override
    public void applyArmoryEffect() {
        int currentVolume = getVolume();

        this.setVolume(currentVolume * 2);
    }

    /**
     * ooze monsters do not have death rattle therefore returns false
     * every time
     * 
     * @return false
     */
    @Override
    public boolean handleDeathrattle() {
        return false;
    }

    public abstract boolean corrode();
}
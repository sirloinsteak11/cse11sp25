///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Rotorcraft
// Files:              Rotorcraft.java
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


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * abstract class extending from aircraft to represent
 * subclasses with rotors to fly
 * 
 * bugs: none afaik
 * 
 * @author Deonn Almirol
 */
public abstract class Rotorcraft extends Aircraft{
    private int numberOfRotors;
    private int mainRotorDiameter;
    private boolean isTailRotorPresent;

    /**
     * No arg constructor
     */
    protected Rotorcraft() {
        super();
        this.numberOfRotors = 1;
        this.mainRotorDiameter = 1;
        this.isTailRotorPresent = false;
    }
    
    /**
     * parameterized constructor
     * 
     * @param tailNumber the unique identifier
     * @param modelName the name of the aircraft
     * @param maxSpeed the max speed of the craft
     * @param numberOfRotors the number of rotors
     * @param mainRotorDiameter the diameter of the rotors
     * @param isTailRotorPresent whether the tail rotor is present
     */
    protected Rotorcraft(String tailNumber, String modelName,
                        int maxSpeed, int numberOfRotors,
                        int mainRotorDiameter, boolean isTailRotorPresent) {

        super(tailNumber, modelName, maxSpeed);
        this.numberOfRotors = numberOfRotors;
        this.mainRotorDiameter = mainRotorDiameter;
        this.isTailRotorPresent = isTailRotorPresent;
        
        if (numberOfRotors <= 0) {
            this.numberOfRotors = 1;
        }

        if (mainRotorDiameter <= 0) {
            this.mainRotorDiameter = 1;
        }
    }

    /**
     * getter for number of rotors
     * 
     * @return number of rotors
     */
    public int getNumberOfRotors() {
        return this.numberOfRotors;
    }

    /**
     * getter for this main rotor diameter
     * 
     * @return main rotor diameter
     */
    public int getMainRotorDiameter() {
        return this.mainRotorDiameter;
    }

    /**
     * getter for if tail rotor is present
     * 
     * @return boolean is tail rotor present
     */
    public boolean getIsTailRotorPresent() {
        return this.isTailRotorPresent;
    }

    /**
    * sets number of rotors
    * 
    * @param numberOfRotors the number of rotors
    */
    public void setNumberOfRotors(int numberOfRotors) {
        this.numberOfRotors = numberOfRotors;
    }

    /**
    * sets the diameter of the main rotor.
    * 
    * @param mainRotorDiameter the main rotor diameter
    */
    public void setMainRotorDiameter(int mainRotorDiameter) {
        this.mainRotorDiameter = mainRotorDiameter;
    }

    /**
    * sets tail rotor boolean
    * 
    * @param isTailRotorPresent whether tail rotor is present or not
    */
    public void setIsTailRotorPresent(boolean isTailRotorPresent) {
        this.isTailRotorPresent = isTailRotorPresent;
    }

    /**
     * does a deep check to ensure that this instance
     * matches the provided object
     * 
     * @param obj the object to check
     * @return if the object matches this instance
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Rotorcraft)) {
            return false;
        }

        Rotorcraft other = (Rotorcraft) obj;
        return super.equals(obj) && 
                this.numberOfRotors == other.numberOfRotors &&
                this.mainRotorDiameter == other.mainRotorDiameter &&
                this.isTailRotorPresent == other.isTailRotorPresent;
    }

    /**
     * provides a string representation of this object
     * 
     * @return the string representation
     */
    @Override
    public String toString() {
        String tailRotor = isTailRotorPresent ? "Yes" : "No";
        return super.toString() + String.format(
            "\n\tRotor Details: Rotors: %d, Main Diameter: %d m, "
            + "Tail Rotor: %s",
            numberOfRotors, mainRotorDiameter, tailRotor
        );
    }

    /**
     * engages the rotor system
     * will throw exception if helicopter is not secured
     *
     * @return rotor system engaged message
     * @throws Exception if the helicopter is not secured
     */
    public abstract String engageRotorSystem() throws Exception;

    /**
     * disengages the rotor system
     * throws exception if the helicopter is secured or landed
     *
     * @return rotor system disengaged message
     * @throws Exception if the helicopter is secured or landed
     */
    public abstract String disengageRotorSystem() throws Exception;

    /**
     * makes the helicopter hover for a number of seconds
     * throws exception is the heli is secured, if altitude is too high
     * or if duration is not positive
     *
     * @param durationSeconds the number of seconds to hover
     * @return helicopter hovering message
     * @throws Exception if status is secured, if altitude exceeds max hover,
     *                   or if the duration is non positive
     */
    public abstract String hover(int durationSeconds) throws Exception;

    /**
     * initiates the takeoff sequence for the aircraft
     * performs preflight checks and throws exceptions
     * if the aircraft does not meet take off criteria
     *
     * @throws Exception if the aircraft is not secured 
     *                   or exceeds passenger capacity
     */
    public abstract void takeOff() throws Exception;

    /**
     * initiates the landing sequence for the aircraft
     * throws exception if not in approach phase
     * otherwise sets landing gear down
     * and speed and altitude to 0
     *
     * @throws Exception if the aircraft is not 
     *                   in the correct status for landing
     */
    public abstract void land() throws Exception;

    /**
     * changes altitude but will throw exception if there are
     * undesirable conditions
     *
     * @param newAltitude the desired target altitude
     * @return the status after altitude change
     * @throws Exception if status, speed, or altitude is invalid
     */
    public abstract String changeAltitude(int newAltitude) throws Exception;

    /**
     * performs pre-flight check and throws exception if
     * the plane is deemed unfit to fly
     *
     * @return if all checks pass
     * @throws Exception if there are any invalid data fields
     */
    public abstract boolean performPreFlightCheck() throws Exception;

    /**
     * saves aircraft data to file.
     *
     * @param fileName the name of the fil
     * @throws IOException if file IO fails
     * @throws Exception if data (airline/flight number) is missing
     */
    public abstract void saveData(String fileName) 
        throws java.io.IOException, Exception;

    /**
     * loads aircraft data from  file.
     *
     * @param fileName the name of the file
     * @throws IOException if file cannot be read
     * @throws FileNotFoundException if the file does not exist
     * @throws Exception if file format is invalid
     */
    public abstract void loadData(String fileName)
        throws java.io.IOException, java.io.FileNotFoundException, Exception;
}

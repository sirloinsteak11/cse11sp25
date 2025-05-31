///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Helicopter
// Files:              Helicopter.java
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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * concrete class representing a helicopter
 * extending from rotorcraft
 * 
 * bugs: none afaik
 * 
 * @author Deonn Almirol
 */
public class Helicopter extends Rotorcraft {
    private String helicopterType;
    private int maxHoverAltitude;

    public static final int DEFAULT_HOVER_ALT = 1000;

    public static final int TOKEN_COUNT = 12;

    public static final double SPEED_RATE = 0.3;
    public static final int INITIAL_TAKEOFF_ALTITUDE = 10;
    public static final int MAX_ALTITUDE = 6000;

    /**
     * no arg constructor
     * 
     */
    public Helicopter() {
        super();
        this.helicopterType = "Unknown";
        this.maxHoverAltitude = DEFAULT_HOVER_ALT;
    }

    /**
     * parameterized constructor for helicopter
     * 
     * @param tailNumber identifer of helicopter
     * @param modelName model name of helicopter
     * @param maxSpeed max speed of helicopter
     * @param numberOfRotors number of rotors on helicopter
     * @param mainRotorDiameter main rotor diameter of copter
     * @param isTailRotorPresent boolean if tail rotor is present
     * @param helicopterType type of helicopter string
     * @param maxHoverAltitude max hover altitude of copter
     */
    public Helicopter(String tailNumber, String modelName, int maxSpeed,
                        int numberOfRotors, int mainRotorDiameter,
                        boolean isTailRotorPresent, String helicopterType,
                        int maxHoverAltitude) {

        super(tailNumber, modelName, maxSpeed, 
                numberOfRotors, mainRotorDiameter, isTailRotorPresent);
        this.helicopterType = helicopterType;
        this.maxHoverAltitude = maxHoverAltitude;
    }

    /**
     * sets the maximum hover altitude
     * only a positive value is accepted
     * if the provided value is not positive, nothing changes
     *
     * @param newAltitude the maximum hover altitude to set
     */
    public void setMaxHoverAltitude(int newAltitude) {
        if (newAltitude > 0) {
            this.maxHoverAltitude = newAltitude;
        }
    }

    /**
     * getter for maximum hover altitude.
     *
     * @return the maximum hover altitude
     */
    public int getMaxHoverAltitude() {
        return maxHoverAltitude;
    }

    /**
     * sets the type of the helicopter.
     *
     * @param helicopterType the helicopter type
     */
    public void setHelicopterType(String helicopterType) {
        this.helicopterType = helicopterType;
    }

    /**
     * getter for type of the helicopter.
     *
     * @return the helicopter type
     */
    public String getHelicopterType() {
        return helicopterType;
    }

    /**
     * engages the rotor system
     * will throw exception if helicopter is not secured
     *
     * @return rotor system engaged message
     * @throws Exception if the helicopter is not secured
     */
    public String engageRotorSystem() throws Exception {
        Exception notSecuredErr = new Exception("Rotors cannot be engaged "
                                                + "unless Secured for "
                                                + getTailNumber() + ".");

        if (!(this.getStatus().equals(Status.Secured))) {
            throw notSecuredErr;
        }

        return getTailNumber() + " rotor system engaged.";
    }

    /**
     * disengages the rotor system
     * throws exception if the helicopter is secured or landed
     *
     * @return rotor system disengaged message
     * @throws Exception if the helicopter is secured or landed
     */
    public String disengageRotorSystem() throws Exception {
        Exception notSecuredErr = new Exception("Rotors cannot be disengaged "
                                                + "unless landed or secured "
                                                + "for " + getTailNumber()
                                                + ".");

        if (!(this.getStatus().equals(Status.Secured)) &&
            !(this.getStatus().equals(Status.Landed))) {

            throw notSecuredErr;
        }

        return getTailNumber() + " rotor system disengaged.";
    }

    /**
     * makes the helicopter hover for a number of seconds
     * throws exception is the heli is secured, if altitude is too high
     * or if duration is not positive
     *
     * @param durationSeconds the number of seconds to hover
     * @return helicopter hovering message
     * @throws Exception if status is secured, if altitude exceeds 
     *                   max hover limit,
     *                   or if the duration is non positive
     */
    public String hover(int durationSeconds) throws Exception {
        Exception securedErr = new Exception(getTailNumber()
                                            + " must takeoff to hover.");

        Exception highAltitudeErr = new Exception("Too high to hover for "
                                                + getTailNumber() + ".");

        Exception durationErr = new Exception("Hover duration must "
                                            + "be positive.");

        if (this.getStatus().equals(Status.Secured)) {
            throw securedErr;
        }

        if (this.getCurrentAltitude() > this.maxHoverAltitude) {
            throw highAltitudeErr;
        }

        if (durationSeconds <= 0) {
            throw durationErr;
        }

        this.setStatus(Status.Hovering);
        this.setCurrentAltitude(0);

        return getTailNumber() + " hovering at "
                + getCurrentAltitude() + "m for "
                + durationSeconds + "s.";
    }

    /**
     * initiates the takeoff sequence for the aircraft
     * performs preflight checks and throws exceptions
     * if the aircraft does not meet take off criteria
     *
     * @throws Exception if the aircraft is not secured or 
     *                   exceeds passenger capacity
     */
    @Override
    public void takeOff() throws Exception {
        if (this.getStatus().equals(Status.Secured) == false) {
            throw new Exception("Helicopter " + getTailNumber()
                                + " not Secured.");
        }

        performPreFlightCheck();
        this.engageRotorSystem();

        this.setStatus(Status.Hovering);
        this.setCurrentAltitude(INITIAL_TAKEOFF_ALTITUDE);

        this.setCurrentSpeed(0);
    }

    /**
     * initiates the landing sequence for the aircraft
     * throws exception if not in approach phase
     * otherwise sets landing gear down
     * and speed and altitude to 0
     *
     * @throws Exception if the aircraft is not in the 
     *                   correct status for landing
     */
    @Override
    public void land() throws Exception {
        if (this.getStatus().equals(Status.Hovering) == false) {
            throw new Exception("Helicopter " + getTailNumber()
                                + " not hovering for landing.");
        }

        this.setCurrentAltitude(0);

        this.setStatus(Status.Landed);
        this.disengageRotorSystem();
        this.setStatus(Status.Secured);
        
    }

    /**
     * changes altitude but will throw exception if there are
     * undesirable conditions
     *
     * @param newAltitude the desired target altitude
     * @return the status after altitude change
     * @throws Exception if status, speed, or altitude is invalid
     */
    @Override
    public String changeAltitude(int newAltitude) throws Exception {
        if(this.getStatus().equals(Status.Secured) == true) {
            throw new Exception("Helicopter " + getTailNumber() + " Secured.");
        }

        if (newAltitude < 0 || newAltitude > MAX_ALTITUDE) {
            throw new Exception("Altitude out of range (0-6000m).");
        }

        if (this.getCurrentSpeed() <= 0 && 
            newAltitude > this.maxHoverAltitude) {

            throw new Exception("Exceeds max hover altitude.");
        }

        int oldAltitude = this.getCurrentAltitude();

        this.setCurrentAltitude(newAltitude);

        if (newAltitude > oldAltitude) {
            this.setStatus(Status.InFlight_Climb);
        }

        if (newAltitude < oldAltitude) {
            this.setStatus(Status.InFlight_Descent);
        }

        if (newAltitude == oldAltitude) {
            this.setStatus(Status.Hovering);
        }

        return this.getStatus();
    }

    /**
     * performs pre-flight check and throws exception if
     * the plane is deemed unfit to fly
     *
     * @return if all checks pass
     * @throws Exception if there are any invalid data fields
     */
    @Override
    public boolean performPreFlightCheck() throws Exception {
        Exception heliTypeErr = new Exception("Helicopter type not specified "
                                            + "for "
                                            + getTailNumber() + ".");

        if (this.helicopterType.equals("Unknown")) {
            throw heliTypeErr;
        }

        return true;
    }

    /**
     * saves aircraft data to file.
     *
     * @param fileName the name of the fil
     * @throws IOException if file IO fails
     * @throws Exception if data (airline/flight number) is missing
     */
    @Override
    public void saveData(String fileName)
            throws IOException, Exception {
        
        Exception dataErr = new Exception("Helicopter type missing for save.");

        if (this.helicopterType.equals("Unknown")) {
            throw dataErr;
        }

        String dataString = "Helicopter," + this.getTailNumber()
                            +","+this.getModelName()+","+this.getMaxSpeed()
                            +","+this.getCurrentSpeed()+","
                            +this.getCurrentAltitude()+","+this.getStatus()
                            +","+this.getNumberOfRotors()+","
                            +this.getMainRotorDiameter()+","
                            +this.getIsTailRotorPresent()
                            +","+this.getHelicopterType()+","
                            +this.getMaxHoverAltitude();

        try {
            PrintWriter output = new PrintWriter(fileName);

            output.print(dataString);
            output.close();
        }
        catch (IOException e) {
            throw e;
        }
    }

    /**
     * loads aircraft data from  file.
     *
     * @param fileName the name of the file
     * @throws IOException if file cannot be read
     * @throws FileNotFoundException if the file does not exist
     * @throws Exception if file format is invalid
     */
    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public void loadData(String fileName)
            throws IOException, FileNotFoundException, Exception {

        Exception dataFormatErr = new Exception("File " + fileName + 
                                                " does not contain "
                                                + "Helicopter data.");

        String data = null;
        String[] tokens = null;
        
        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                data = fileReader.nextLine();
            }
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            throw e;
        }

        tokens = data.split(",");

        if (tokens[0].equals("Helicopter") == false) {
            throw dataFormatErr;
        }

        if (tokens.length != TOKEN_COUNT) {
            throw dataFormatErr;
        }

        try {
            this.setTailNumber(tokens[1]);
            this.setModelName(tokens[2]);
            this.setMaxSpeed(Integer.parseInt(tokens[3]));
            this.setCurrentSpeed(Integer.parseInt(tokens[4]));
            this.setCurrentAltitude(Integer.parseInt(tokens[5]));
            this.setStatus(tokens[6]);
            this.setNumberOfRotors(Integer.parseInt(tokens[7]));
            this.setMainRotorDiameter(Integer.parseInt(tokens[8]));
            this.setIsTailRotorPresent(Boolean.parseBoolean(tokens[9]));
            this.setHelicopterType(tokens[10]);
            this.setMaxHoverAltitude(Integer.parseInt(tokens[11]));
        }
        catch (NumberFormatException e) {
            throw dataFormatErr;
        }
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

        if (!(obj instanceof Helicopter)) {
            return false;
        }

        Helicopter po = (Helicopter)obj;

        boolean typeCheck = false;

        if (po.getHelicopterType() == null || helicopterType == null) {
            typeCheck = false;
        } else {
            if (helicopterType.equals(po.getHelicopterType())) {
                typeCheck = true;
            }
        }

        return super.equals(obj)
                && maxHoverAltitude == po.getMaxHoverAltitude()
                && typeCheck;
    }

    /**
     * provides a string representation of this object
     * 
     * @return the string representation
     */
    @Override
    public String toString() {
        return super.toString() + "\n\tHelicopter Info: Type: "
                + helicopterType + ", Max Hover Alt: "
                + maxHoverAltitude + "m";
    }
}
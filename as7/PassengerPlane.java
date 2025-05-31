///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              PassengerPlane
// Files:              PassengerPlane.java
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


import java.io.IOException;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

/**
 * Concrete class representing a passenger plane, extending
 * FixedWingAircraft.
 * 
 * bugs: none afaik
 * 
 * @author Deonn Almirol
 */
public class PassengerPlane extends FixedWingAircraft {

    private int passengerCapacity;
    private int currentPassengerCount;
    private String airlineName;
    private String flightNumber;

    public static final int TOKEN_COUNT = 15;

    public static final double SPEED_RATE = 0.3;
    public static final int INITIAL_TAKEOFF_ALTITUDE = 1000;
    public static final int MAX_ALTITUDE = 13000;

    /**
     * Default constructor for PassengerPlane.
     */
    public PassengerPlane() {
        super();
        this.passengerCapacity = 1;
        this.currentPassengerCount = 0;
        this.airlineName = "Unknown";
        this.flightNumber = "Unknown";
    }

    /**
     * Parameterized constructor for PassengerPlane.
     *
     * @param tailNumber Unique identifier for the aircraft.
     * @param modelName Model name of the aircraft.
     * @param maxSpeed Maximum speed of the aircraft.
     * @param wingSpan Wingspan in meters.
     * @param numberOfEngines Number of engines.
     * @param engineType Type of engine.
     * @param airlineName Name of the airline.
     * @param flightNumber Flight number.
     * @param passengerCapacity Maximum passenger capacity.
     */
    public PassengerPlane(String tailNumber, String modelName, int maxSpeed,
                          int wingSpan, int numberOfEngines,
                          String engineType, String airlineName,
                          String flightNumber, int passengerCapacity) {
        super(tailNumber, modelName, maxSpeed, wingSpan, numberOfEngines,
                engineType);
        if (passengerCapacity > 0) {
            this.passengerCapacity = passengerCapacity;
        } else {
            this.passengerCapacity = 1;
        }
        this.currentPassengerCount = 0;
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
    }

    /**
     * Gets the maximum passenger capacity.
     *
     * @return The passenger capacity.
     */
    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }

    /**
     * Gets the current number of passengers on board.
     *
     * @return The current passenger count.
     */
    public int getCurrentPassengerCount() {
        return this.currentPassengerCount;
    }

    /**
     * Gets the name of the airline.
     *
     * @return The airline name.
     */
    public String getAirlineName() {
        return this.airlineName;
    }

    /**
     * Gets the flight number.
     *
     * @return The flight number.
     */
    public String getFlightNumber() {
        return this.flightNumber;
    }

    /**
     * Sets the maximum passenger capacity.
     *
     * @param passengerCapacity The new passenger capacity.
     */
    public void setPassengerCapacity(int passengerCapacity) {
        if (passengerCapacity >= 0) {
            this.passengerCapacity = passengerCapacity;
        }
        if (this.currentPassengerCount > this.passengerCapacity) {
            this.currentPassengerCount = this.passengerCapacity;
        }
    }

    /**
     * Sets the current number of passengers on board.
     *
     * @param currentPassengerCount The new current passenger count.
     */
    public void setCurrentPassengerCount(int currentPassengerCount) {
        if (currentPassengerCount >= 0 &&
                currentPassengerCount <= this.passengerCapacity) {
            this.currentPassengerCount = currentPassengerCount;
        }
    }

    /**
     * Sets the name of the airline.
     *
     * @param airlineName The new airline name.
     */
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    /**
     * Sets the flight number.
     *
     * @param flightNumber The new flight number.
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
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
            throw new Exception("Aircraft " + getTailNumber() +
                                " not secured for takeoff. Status: " +
                                getStatus());
        }

        performPreFlightCheck();
        setStatus(Status.InFlight_Climb);

        if (this.currentPassengerCount > this.passengerCapacity) {
            throw new Exception("Cannot takeoff " + getTailNumber() +
                                ": Exceeds passenger capacity.");
        }

        this.setCurrentSpeed((int)(Math.floor(
                                    SPEED_RATE * this.getMaxSpeed())));

        this.setCurrentAltitude(INITIAL_TAKEOFF_ALTITUDE);
        this.setIsLandingGearDown(false);
    }

    /**
     * initiates the landing sequence for the aircraft
     * throws exception if not in approach phase
     * otherwise sets landing gear down
     * and speed and altitude to 0
     *
     * @throws Exception if the aircraft is not 
     *                   in the correct status for landing
     */
    @Override
    public void land() throws Exception {
        if (this.getStatus().equals(Status.InFlight_Approach) == false) {
            throw new Exception("Aircraft " + getTailNumber() +
                                " not in approach phase for landing. " +
                                "Status: " + getStatus());
        }

        this.setIsLandingGearDown(true);

        this.setStatus(Status.Secured);
        this.setCurrentSpeed(0);
        this.setCurrentAltitude(0);
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
            throw new Exception("Aircraft " + getTailNumber() +
                                " is Secured and cannot change altitude.");
        }

        if (newAltitude < 0 || newAltitude > MAX_ALTITUDE) {
            throw new Exception("Target altitude " + newAltitude + "m for" +
                                getTailNumber() + " is out of operational" +
                                "range (0-13000m).");
        }

        if (this.getCurrentSpeed() <= 0 || 
            this.getCurrentSpeed() > MAX_ALTITUDE) {
                
            throw new Exception("Speed is invalid.");
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
            this.setStatus(Status.InFlight_Cruise);
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
        Exception flightNumErr = new Exception("Pre-flight check failed for " +
                                                getTailNumber() + 
                                                ": Flight number not set.");

        Exception airlineNameErr = new Exception("Pre-flight check failed for "
                                                + getTailNumber()
                                                + ": Airline name not set.");

        Exception landGearErr = new Exception("Pre-flight check failed for "
                                            + getTailNumber()
                                            + ": Landing gear not down.");

        if (flightNumber == null) {
            throw flightNumErr;
        }

        if (flightNumber.equals("")) {
            throw flightNumErr;
        }

        if (flightNumber.equals("Unknown")) {
            throw flightNumErr;
        }

        if (airlineName == null) {
            throw airlineNameErr;
        }

        if (airlineName.equals("")) {
            throw airlineNameErr;
        }

        if (airlineName.equals("Unknown")) {
            throw airlineNameErr;
        }

        if (this.isLandingGearDown() == false) {
            throw landGearErr;
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
        
        Exception dataErr = new Exception("Cannot save PassengerPlane "
                                        + getTailNumber()
                                        + ": Essential data "
                                        + "(airline/flight number) missing.");

        if (this.getAirlineName().equals("Unknown")) {
            throw dataErr;
        }

        if (this.getFlightNumber().equals("Unknown")) {
            throw dataErr;
        }

        String dataString = "PassengerPlane," + this.getTailNumber()
                            +","+this.getModelName()+","+this.getMaxSpeed()
                            +","+this.getCurrentSpeed()+","
                            +this.getCurrentAltitude()+","+this.getStatus()
                            +","+this.getWingSpan()+","
                            +this.getNumberOfEngines()+","+this.getEngineType()
                            +","+this.isLandingGearDown()+","
                            +this.getPassengerCapacity()+","
                            +this.getCurrentPassengerCount()+","
                            +this.getAirlineName()+","+this.getFlightNumber();

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

        Exception dataFormatErr = new Exception("File " + fileName
                                                + " does not contain "+
                                                "PassengerPlane data.");

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

        if (tokens[0].equals("PassengerPlane") == false) {
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
            this.setWingSpan(Integer.parseInt(tokens[7]));
            this.setNumberOfEngines(Integer.parseInt(tokens[8]));
            this.setEngineType(tokens[9]);
            this.setIsLandingGearDown(Boolean.parseBoolean(tokens[10]));
            this.setPassengerCapacity(Integer.parseInt(tokens[11]));
            this.setCurrentPassengerCount(Integer.parseInt(tokens[12]));
            this.setAirlineName(tokens[13]);
            this.setFlightNumber(tokens[14]);
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

        if (!(obj instanceof PassengerPlane)) {
            return false;
        }

        PassengerPlane po = (PassengerPlane)obj;

        boolean nameCheck = false;
        boolean numCheck = false;

        if (po.getAirlineName() == null || airlineName == null) {
            nameCheck = false;
        } else {
            if (airlineName.equals(po.getAirlineName())) {
                nameCheck = true;
            }
        }

        if (po.flightNumber == null || flightNumber == null) {
            numCheck = false;
        } else {
            if (flightNumber.equals(po.getFlightNumber())) {
                numCheck = true;
            }
        }

        return super.equals(obj)
                && passengerCapacity == po.getPassengerCapacity()
                && nameCheck && numCheck;
    }

    /**
     * provides a string representation of this object
     * 
     * @return the string representation
     */
    @Override
    public String toString() {
        return super.toString() + "\n\tPassenger Info: Flight " + flightNumber
                + " (" + airlineName + "), Occupancy: " + currentPassengerCount
                + "/" + passengerCapacity;
    }
}
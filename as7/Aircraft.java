///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Aircraft
// Files:              Aircraft.java
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
 * Abstract base class for all aircraft types in the system.
 */
public abstract class Aircraft {

    private String tailNumber;
    private String modelName;
    private int maxSpeed;
    private int currentSpeed;
    private int currentAltitude;
    private String status;

    /**
     * Shorthands for statuses in all abstract method implementations
     */
    final class Status {
        public static final String InFlight_Climb = "InFlight_Climb";
        public static final String InFlight_Descent = "InFlight_Descent";
        public static final String InFlight_Cruise = "InFlight_Cruise";
        public static final String InFlight_Approach = "InFlight_Approach";
        public static final String Hovering = "Hovering";
        public static final String Secured = "Secured";
        public static final String Landed = "Landed";
    }

    /**
     * Default constructor for Aircraft.
     */
    protected Aircraft() {
        this.status = "Secured";
        this.currentSpeed = 0;
        this.currentAltitude = 0;
        this.tailNumber = null;
        this.modelName = null;
        this.maxSpeed = 1;
    }

    /**
     * Parameterized constructor for Aircraft.
     *
     * @param tailNumber Unique identifier for the aircraft.
     * @param modelName Model name of the aircraft.
     * @param maxSpeed Maximum speed of the aircraft.
     */
    protected Aircraft(String tailNumber, String modelName, int maxSpeed) {
        this.tailNumber = tailNumber;
        this.modelName = modelName;
        this.status = "Secured";
        this.currentSpeed = 0;
        this.currentAltitude = 0;
        if (maxSpeed > 0) {
            this.maxSpeed = maxSpeed;
        } else {
            this.maxSpeed = 1;
        }
    }

    /**
     * Gets the tail number of the aircraft.
     *
     * @return The tail number.
     */
    public String getTailNumber() {
        return this.tailNumber;
    }

    /**
     * Gets the model name of the aircraft.
     *
     * @return The model name.
     */
    public String getModelName() {
        return this.modelName;
    }

    /**
     * Gets the maximum speed of the aircraft.
     *
     * @return The maximum speed.
     */
    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * Gets the current speed of the aircraft.
     *
     * @return The current speed.
     */
    public int getCurrentSpeed() {
        return this.currentSpeed;
    }

    /**
     * Gets the current altitude of the aircraft.
     *
     * @return The current altitude.
     */
    public int getCurrentAltitude() {
        return this.currentAltitude;
    }

    /**
     * Gets the current status of the aircraft.
     *
     * @return The current status.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the tail number of the aircraft.
     *
     * @param tailNumber The new tail number.
     */
    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    /**
     * Sets the model name of the aircraft.
     *
     * @param modelName The new model name.
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Sets the maximum speed of the aircraft.
     *
     * @param maxSpeed The new maximum speed.
     */
    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed > 0) {
            this.maxSpeed = maxSpeed;
        }
    }

    /**
     * Sets the current speed of the aircraft.
     *
     * @param currentSpeed The new current speed.
     */
    public void setCurrentSpeed(int currentSpeed) {
        if (currentSpeed >= 0 && currentSpeed <= this.maxSpeed) {
            this.currentSpeed = currentSpeed;
        }
    }

    /**
     * Sets the current altitude of the aircraft.
     *
     * @param currentAltitude The new current altitude.
     */
    public void setCurrentAltitude(int currentAltitude) {
        if (currentAltitude >= 0) {
            this.currentAltitude = currentAltitude;
        }
    }

    /**
     * Sets the status of the aircraft.
     *
     * @param status The new status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Provides a basic string representation of the Aircraft.
     *
     * @return String representation of the Aircraft.
     */
    @Override
    public String toString() {
        return "Model: " + this.modelName +
                ", Tail#: " + this.tailNumber + ", Status: " + this.status;
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
        if (!(obj instanceof Aircraft)) {
            return false;
        }
        Aircraft ao = (Aircraft)obj;

        return modelName.equals(ao.modelName) && 
            tailNumber == ao.tailNumber;
    }

    /**
     * initiates the takeoff sequence for the aircraft
     * performs preflight checks and throws exceptions
     * if the aircraft does not meet take off criteria
     *
     * @throws Exception if the aircraft is not 
     *                   secured or exceeds passenger capacity
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
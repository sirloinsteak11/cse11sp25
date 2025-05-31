///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              FixedWingAircraft
// Files:              FixedWingAircraft.java
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
 * Abstract base class for fixed-wing aircraft, extending Aircraft.
 */
public abstract class FixedWingAircraft extends Aircraft {

    private int wingSpan;
    private int numberOfEngines;
    private String engineType;
    private boolean isLandingGearDown;

    /**
     * Default constructor for FixedWingAircraft.
     */
    protected FixedWingAircraft() {
        super();
        this.wingSpan = 1;
        this.numberOfEngines = 1;
        this.engineType = "Unknown";
        this.isLandingGearDown = true;
    }

    /**
     * Parameterized constructor for Aircraft.
     *
     * @param tailNumber Unique identifier for the aircraft.
     * @param modelName Model name of the aircraft.
     * @param maxSpeed Maximum speed of the aircraft.
     * @param wingSpan length of wings
     * @param numberOfEngines number of engines
     * @param engineType type of engine string
     */
    protected FixedWingAircraft(String tailNumber, String modelName,
                                int maxSpeed, int wingSpan,
                                int numberOfEngines, String engineType) {
        
        super(tailNumber, modelName, maxSpeed);
        this.wingSpan = wingSpan;
        this.numberOfEngines = numberOfEngines;
        this.engineType = engineType;
        this.isLandingGearDown = true;

        if (wingSpan <= 0) {
            this.wingSpan = 1;
        }

        if (numberOfEngines <= 0) {
            this.numberOfEngines = 1;
        }
    }

    /**
     * Gets the wingspan.
     *
     * @return The wingspan.
     */
    public int getWingSpan() {
        return this.wingSpan;
    }

    /**
     * Gets the number of engines.
     *
     * @return The number of engines.
     */
    public int getNumberOfEngines() {
        return this.numberOfEngines;
    }

    /**
     * Gets the type of engine.
     *
     * @return The engine type.
     */
    public String getEngineType() {
        return this.engineType;
    }

    /**
     * Checks if the landing gear is down.
     *
     * @return true if the landing gear is down, false otherwise.
     */
    public boolean isLandingGearDown() {
        return this.isLandingGearDown;
    }

    /**
     * Sets the wingspan.
     *
     * @param wingSpan The new wingspan.
     */
    public void setWingSpan(int wingSpan) {
        if (wingSpan > 0) {
            this.wingSpan = wingSpan;
        } else {
            this.wingSpan = this.wingSpan;
        }
    }

    /**
     * Sets the number of engines.
     *
     * @param numberOfEngines The new number of engines.
     */
    public void setNumberOfEngines(int numberOfEngines) {
        if (numberOfEngines > 0) {
            this.numberOfEngines = numberOfEngines;
        } else {
            this.numberOfEngines = this.numberOfEngines;
        }
    }

    /**
     * Sets the type of engine.
     *
     * @param engineType The new engine type.
     */
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    /**
     * Sets the state of the landing gear.
     *
     * @param isLandingGearDown The new state of the landing gear.
     */
    public void setIsLandingGearDown(boolean isLandingGearDown) {
        this.isLandingGearDown = isLandingGearDown;
    }

    /**
     * provides a string representation of this object
     * 
     * @return the string representation
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + String.format
            ("\tWingspan: %d m, Engines: %d x %s, Gear Down: ",
            wingSpan, numberOfEngines, engineType) + 
            (isLandingGearDown ? "Yes" : "No");
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

        if ((obj instanceof FixedWingAircraft) == false) {
            return false;
        }

        FixedWingAircraft fwao = (FixedWingAircraft)obj;
        return super.equals(obj) && wingSpan == fwao.wingSpan &&
            numberOfEngines == fwao.numberOfEngines && 
            engineType.equals(fwao.engineType) &&
            isLandingGearDown == fwao.isLandingGearDown;
    }


    // Inherited abstract methods from Aircraft (to be implemented
    // by concrete subclasses)
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
     * @throws Exception if the aircraft is not in 
     *                   the correct status for landing
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
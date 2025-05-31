///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              PrivateJet
// Files:              PrivateJet.java
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
import java.util.Arrays;
import java.util.Scanner;

/**
 * concrete class representing PrivateJet 
 * extending from FixedWingAircraft
 * 
 * bugs: none afaik
 * 
 * @author Deonn Almirol
 */
public class PrivateJet extends FixedWingAircraft {
    private int maxRangeKm;
    private String ownerName;
    private ArrayList<String> amenities;

    public static final int DEFAULT_MAX_RANGE = 1000;
    public static final String DEFAULT_OWNER_NAME = "Unlisted";

    public static final double SPEED_RATE = 0.4;
    public static final int INITIAL_TAKEOFF_ALTITUDE = 2000;
    public static final int MAX_ALTITUDE = 13000;

    public static final int TOKEN_COUNT = 14;

    /**
     * No-arg default constructor
     */
    public PrivateJet() {
        super();
        this.maxRangeKm = DEFAULT_MAX_RANGE;
        this.ownerName = DEFAULT_OWNER_NAME;
        this.amenities = new ArrayList<String>();
    }
    
    /**
     * Parameterized constructor
     * 
     * @param tailNumber identifier of plane
     * @param modelName model of plane
     * @param maxSpeed max speed of plane
     * @param wingSpan wing span of plane
     * @param numberOfEngines number of engines on plane
     * @param engineType type of engine
     * @param maxRangeKm max range of plane
     * @param ownerName name of owner
     */
    public PrivateJet(String tailNumber, String modelName, int maxSpeed,
                        int wingSpan, int numberOfEngines, String engineType,
                        int maxRangeKm, String ownerName) {
    
        super(tailNumber, modelName, maxSpeed, wingSpan, 
                numberOfEngines, engineType);
        
        this.maxRangeKm = maxRangeKm;
        this.ownerName = ownerName;
        this.amenities = new ArrayList<String>();

    }

    /**
     * returns the list of amenities
     *
     * @return the list of amenities
     */
    public ArrayList<String> getAmenities() {
        return amenities;
    }

    /**
     * returns owner's name
     *
     * @return the name of the owner
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * returns the current maximum range in kilometers
     *
     * @return the max range in km
     */
    public int getMaxRangeKm() {
        return maxRangeKm;
    }

    /**
     * replaces the amenities list
     *
     * @param newAmenities the new list of amenities
     */
    public void setAmenities(ArrayList<String> newAmenities) {
        this.amenities = newAmenities;
    }

    /**
     * replaces the owner name
     *
     * @param newOwner the new owner name
     */
    public void setOwnerName(String newOwner) {
        this.ownerName = newOwner;
    }

    /**
     * replaces the existing maxRangeKm value
     * if the new value is 0 or negative, dont change the current value
     *
     * @param newMaxRange the new maximum range in kilometers
     */
    public void setMaxRangeKm(int newMaxRange) {
        if (newMaxRange > 0) {
            this.maxRangeKm = newMaxRange;
        }
    }

    /**
     * adds amenity to amenity list given that the list
     * is not null and not empty
     * 
     * @param amenity the amenity to add
     * @return whether the process was successful
     */
    public boolean addAmenity(String amenity) {
        if (this.amenities != null && this.amenities.isEmpty() == false) {
            this.amenities.add(amenity);
            return true;
        }

        return false;
    }

    /**
     * initiates the takeoff sequence for the aircraft
     * performs preflight checks and throws exceptions
     * if the aircraft does not meet take off criteria
     *
     * @throws Exception if the aircraft is not secured 
     *                   or exceeds passenger capacity
     */
    @Override
    public void takeOff() throws Exception {
        if (this.getStatus().equals(Status.Secured) == false) {
            throw new Exception("PrivateJet is not Secured.");
        }

        performPreFlightCheck();
        setStatus(Status.InFlight_Climb);

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
     * @throws Exception if the aircraft is not in the 
     *                   correct status for landing
     */
    @Override
    public void land() throws Exception {
        if (this.getStatus().equals(Status.InFlight_Approach) == false) {
            throw new Exception("PrivateJet is not in approach.");
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
            throw new Exception("PrivateJet is Secured.");
        }

        if (newAltitude < 0 || newAltitude > MAX_ALTITUDE) {
            throw new Exception("Altitude out of range (0-13000m).");
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
        Exception ownerNameErr = new Exception("Owner details missing for "
                                                + getTailNumber()
                                                + ".");

        Exception landGearErr = new Exception("Landing gear not down for "
                                                + getTailNumber() + ".");

        if (this.ownerName == null) {
            throw ownerNameErr;
        }

        if (this.ownerName.equals("")) {
            throw ownerNameErr;
        }

        if (this.ownerName.equals("Unlisted")) {
            throw ownerNameErr;
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
        
        Exception dataErr = new Exception("Owner name missing for save.");

        if (this.ownerName.equals("Unknown")) {
            throw dataErr;
        }

        String amenitiesString = "NONE";

        if (this.amenities.size() > 0) {
            amenitiesString = String.join(";", this.amenities);
        }

        String dataString = "PrivateJet," + this.getTailNumber()
                            +","+this.getModelName()+","+this.getMaxSpeed()
                            +","+this.getCurrentSpeed()+","
                            +this.getCurrentAltitude()+","+this.getStatus()
                            +","+this.getWingSpan()+","
                            +this.getNumberOfEngines()+","+this.getEngineType()
                            +","+this.isLandingGearDown()+","
                            +this.getMaxRangeKm()+","
                            +this.getOwnerName()+","+amenitiesString;

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

        Exception dataFormatErr = new Exception("File "
                                                + fileName
                                                + " does not contain "
                                                + "PrivateJet data.");

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

        if (tokens[0].equals("PrivateJet") == false) {
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
            this.setMaxRangeKm(Integer.parseInt(tokens[11]));
            this.setOwnerName(tokens[12]);
            
            if (!(tokens[13].equals("NONE"))) {
                String[] amenitiesSplit = tokens[13].split(";");

                for (int i = 0; i < amenitiesSplit.length; i++) {
                    this.addAmenity(amenitiesSplit[i]);
                }
            }
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

        if (!(obj instanceof PrivateJet)) {
            return false;
        }

        PrivateJet po = (PrivateJet)obj;

        boolean ownerCheck = false;
        boolean amenityCheck = true;

        if (po.getOwnerName() == null || ownerName == null) {
            ownerCheck = false;
        } else {
            if (ownerName.equals(po.getOwnerName())) {
                ownerCheck = true;
            }
        }

        if (po.getAmenities() == null || amenities == null) {
            amenityCheck = false;
        } else {
            ArrayList<String> list = po.getAmenities();

            for (int i = 0; i < amenities.size(); i++) {
                if (!(amenities.get(i).equals(list.get(i)))) {
                    amenityCheck = false;
                    break;
                }
            }
        }

        return super.equals(obj)
                && maxRangeKm == po.getMaxRangeKm()
                && ownerCheck && amenityCheck;
    }

    /**
     * provides a string representation of this object
     * 
     * @return the string representation
     */
    @Override
    public String toString() {
        return super.toString() + "\n\tPrivate Jet Info: Owner: "
                + ownerName + ", Max Range: "
                + maxRangeKm + " km, Amenities: "
                + amenities.toString();
    }
}

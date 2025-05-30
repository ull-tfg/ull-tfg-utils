package es.ull.utils.geojson;

import org.json.JSONArray;

import es.ull.utils.json.UllJson;
import es.ull.utils.lang.UllBoolean;
import es.ull.utils.lang.UllDouble;

/**
 * Represents a position in the Earth's surface defined by its longitude and latitude.
 * 
 */
public class UllGeoJsonPosition {

    private static final String ERROR_POSITION_MIN_COORDINATES = "Position must have at least 2 coordinates";
    private static final String ERROR_POSITION_MAX_COORDINATES = "Position must have at most 3 coordinates";
    private static final String ERROR_POSITION_COORDINATES_NOT_NUMERIC = "Position coordinates must be numeric values";
    private static final String ERROR_JSON_UNDEFINED = "JSON is not defined";
    // longitude
    public static final double LONGITUDE_MIN = -180.0;
    public static final double LONGITUDE_MAX = 180.0;
    public static final String ERROR_LONGITUDE_NOT_DEFINED = "Longitude is not defined";
    public static final String ERROR_LONGITUDE_MIN = "Longitude must be equals or greater than " + LONGITUDE_MIN;
    public static final String ERROR_LONGITUDE_MAX = "Longitude must be lesser or equals to " + LONGITUDE_MAX;
    public static final String ERROR_LONGITUDE_WRONG_FORMAT = "Longitude must be a numeric value in [" + LONGITUDE_MIN + "," + LONGITUDE_MAX + "]";
    // latitude
    public static final double LATITUDE_MIN = -90.0;
    public static final double LATITUDE_MAX = 90.0;
    public static final String ERROR_LATITUDE_NOT_DEFINED = "Latitude is not defined";
    public static final String ERROR_LATITUDE_MIN = "Latitude must be equals or greater than " + LATITUDE_MIN;
    public static final String ERROR_LATITUDE_MAX = "Latitude must be lesser or equals to " + LATITUDE_MAX;
    public static final String ERROR_LATITUDE_WRONG_FORMAT = "Latitude must be a numeric value in [" + LATITUDE_MIN + "," + LATITUDE_MAX + "]";
    // altitude
    private static final double ALTITUDE_NOT_DEFINED = -1.0;
    public static final double ALTITUDE_MIN = 0;
    public static final double ALTITUDE_MAX = Double.MAX_VALUE;
    public static final String ERROR_ALTITUDE_MIN = "Altitude must be equals or greater than " + ALTITUDE_MIN;
    public static final String ERROR_ALTITUDE_MAX = "Altitude must be lesser or equals to " + ALTITUDE_MAX;
    /**
     * Longitude of the position.
     */
    private double longitude;
    /**
     * Latitude of the position.
     */
    private double latitude;
    /**
     * Altitude of the position. It is an optional attribute.
     */
    private double altitude;

    public UllGeoJsonPosition() {
        this.longitude = 0.0;
        this.latitude = 0.0;
        this.altitude = ALTITUDE_NOT_DEFINED;
    }

    public UllGeoJsonPosition(double longitude, double latitude) {
        UllGeoJsonPosition.validate(longitude, latitude);
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = ALTITUDE_NOT_DEFINED;
    }

    public UllGeoJsonPosition(double longitude, double latitude, double altitude) {
        UllGeoJsonPosition.validate(longitude, latitude, altitude);
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    /**
     * Validates the longitude and latitude of the position.
     * 
     * @param longitude Longitude of the position.
     * @param latitude  Latitude of the position.
     */
    private static void validate(double longitude, double latitude) {
        UllGeoJsonPosition.validateLongitude(longitude);
        UllGeoJsonPosition.validateLatitude(latitude);
    }

    /**
     * Validates the longitude, latitude and altitude of the position.
     * 
     * @param longitude Longitude of the position.
     * @param latitude  Latitude of the position.
     * @param altitude  Altitude of the position.
     */
    private static void validate(double longitude, double latitude, double altitude) {
        UllGeoJsonPosition.validate(longitude, latitude);
        UllGeoJsonPosition.validateAltitude(altitude);
    }

    /**
     * Validates the longitude of the position.
     * 
     * @param longitude Longitude of the position.
     */
    private static void validateLongitude(double longitude) {
        if (longitude < LONGITUDE_MIN) {
            throw new IllegalArgumentException(ERROR_LONGITUDE_MIN);
        }
        if (longitude > LONGITUDE_MAX) {
            throw new IllegalArgumentException(ERROR_LONGITUDE_MAX);
        }
    }

    /**
     * Validates the latitude of the position.
     * 
     * @param latitude Latitude of the position.
     */
    private static void validateLatitude(double latitude) {
        if (latitude < LATITUDE_MIN) {
            throw new IllegalArgumentException(ERROR_LATITUDE_MIN);
        }
        if (latitude > LATITUDE_MAX) {
            throw new IllegalArgumentException(ERROR_LATITUDE_MAX);
        }
    }

    /**
     * Validates the altitude of the position.
     * 
     * @param altitude Altitude of the position.
     */
    private static void validateAltitude(double altitude) {
        if (altitude < ALTITUDE_MIN) {
            throw new IllegalArgumentException(ERROR_ALTITUDE_MIN);
        }
        if (altitude > ALTITUDE_MAX) {
            throw new IllegalArgumentException(ERROR_ALTITUDE_MAX);
        }
    }

    /**
     * Compares the position with another object.
     * 
     * @return True if the position is equals to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == this) {
            return true;
        }
        if (!(otherObject instanceof UllGeoJsonPosition)) {
            return false;
        }
        final UllGeoJsonPosition otherPosition = (UllGeoJsonPosition) otherObject;
        return this.longitude == otherPosition.longitude &&
                this.latitude == otherPosition.latitude &&
                this.altitude == otherPosition.altitude;
    }

    /**
     * Returns the longitude of the position.
     * 
     * @return The longitude of the position.
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Returns the latitude of the position.
     * 
     * @return The latitude of the position.
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Returns the altitude of the position.
     * 
     * @return The altitude of the position.
     */
    public double getAltitude() {
        return this.altitude;
    }

    /**
     * Returns true if the position has altitude, false otherwise.
     *
     * @return True if the position has altitude, false otherwise.
     */
    public boolean hasAltitude() {
        return this.altitude != ALTITUDE_NOT_DEFINED;
    }

    public UllGeoJsonPosition setLongitude(double longitude) {
        UllGeoJsonPosition.validateLongitude(longitude);
        final UllGeoJsonPosition newPosition = new UllGeoJsonPosition(longitude, this.latitude);
        newPosition.altitude = this.altitude;
        return newPosition;
    }

    public UllGeoJsonPosition setLatitude(double latitude) {
        UllGeoJsonPosition.validateLatitude(latitude);
        final UllGeoJsonPosition newPosition = new UllGeoJsonPosition(this.longitude, latitude);
        newPosition.altitude = this.altitude;
        return newPosition;
    }

    public UllGeoJsonPosition setAltitude(double altitude) {
        UllGeoJsonPosition.validateAltitude(altitude);
        return new UllGeoJsonPosition(this.longitude, this.latitude, altitude);
    }

    /**
     * Hash code of the position.
     * 
     * @return The hash code of the position.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Returns a random position in the Earth's surface.
     * 
     * @return A random position in the Earth's surface.
     */
    public static UllGeoJsonPosition random() {
        final double longitude = UllDouble.random(LONGITUDE_MIN, LONGITUDE_MAX);
        final double latitude = UllDouble.random(LATITUDE_MIN, LATITUDE_MAX);
        UllGeoJsonPosition position = new UllGeoJsonPosition(longitude, latitude);
        if (UllBoolean.random()) {
            position = position.setAltitude(UllDouble.random(ALTITUDE_MIN, ALTITUDE_MAX));
        }
        return position;
    }

    public static UllGeoJsonPosition from(JSONArray json) {
        if (json == null) {
            throw new IllegalArgumentException(ERROR_JSON_UNDEFINED);
        }
        if (json.length() < 2) {
            throw new IllegalArgumentException(ERROR_POSITION_MIN_COORDINATES);
        }
        if (json.length() > 3) {
            throw new IllegalArgumentException(ERROR_POSITION_MAX_COORDINATES);
        }
        if (!UllJson.canAllBeParsedToDouble(json)) {
            throw new IllegalArgumentException(ERROR_POSITION_COORDINATES_NOT_NUMERIC);
        }
        final double longitude = json.getDouble(0);
        final double latitude = json.getDouble(1);
        final UllGeoJsonPosition position = new UllGeoJsonPosition(longitude, latitude);
        if (json.length() == 3) {
            position.setAltitude(json.getDouble(2));
        }
        return position;
    }

    /**
     * Returns a JSON representation of the position.
     * 
     * @return A JSON representation of the position.
     */
    public JSONArray toJson() {
        final JSONArray json = new JSONArray();
        json.put(this.getLongitude());
        json.put(this.getLatitude());
        if (this.hasAltitude()) {
            json.put(this.getAltitude());
        }
        return json;
    }

    /**
     * Returns a string representation of the position.
     * 
     * @return A string representation of the position.
     */
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}

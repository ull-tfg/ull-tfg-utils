package es.ull.utils.geolocation;

import com.fasterxml.jackson.databind.JsonNode;

import es.ull.utils.lang.UllDouble;

/**
 * Represents a point in the Earth's surface defined by its longitude and
 * latitude.
 * 
 */
public class UllGeolocationPoint {

    // longitude
    public static final double LONGITUDE_MIN = -180.0;
    public static final double LONGITUDE_MAX = 180.0;
    public static final String ERROR_LONGITUDE_NOT_DEFINED = "Longitude is not defined";
    public static final String ERROR_LONGITUDE_MIN = "Longitude must be equals or greater than " + LONGITUDE_MIN;
    public static final String ERROR_LONGITUDE_MAX = "Longitude must be lesser or equals to " + LONGITUDE_MAX;
    public static final String ERROR_LONGITUDE_WRONG_FORMAT = "Longitude must be a numeric value in [" + LONGITUDE_MIN
            + "," + LONGITUDE_MAX + "]";
    // latitude
    public static final double LATITUDE_MIN = -90.0;
    public static final double LATITUDE_MAX = 90.0;
    public static final String ERROR_LATITUDE_NOT_DEFINED = "Latitude is not defined";
    public static final String ERROR_LATITUDE_MIN = "Latitude must be equals or greater than " + LATITUDE_MIN;
    public static final String ERROR_LATITUDE_MAX = "Latitude must be lesser or equals to " + LATITUDE_MAX;
    public static final String ERROR_LATITUDE_WRONG_FORMAT = "Latitude must be a numeric value in [" + LATITUDE_MIN
            + "," + LATITUDE_MAX + "]";
    /**
     * Longitude of the point.
     */
    private double longitude;
    /**
     * Latitude of the point.
     */
    private double latitude;

    /**
     * Creates a new instance of UllGeolocationPoint.
     * 
     * @param longitude The longitude of the point.
     * @param latitude  The latitude of the point.
     */
    public UllGeolocationPoint(double longitude, double latitude) {
        UllGeolocationPoint.validate(longitude, latitude);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Creates a new instance of UllGeolocationPoint from a JsonNode.
     * 
     * @param nodeGeolocation The JsonNode that contains the longitude and latitude.
     * @return A new instance of UllGeolocationPoint.
     */
    public static UllGeolocationPoint from(JsonNode nodeGeolocation) {
        validate(nodeGeolocation);
        double longitude = nodeGeolocation.get(JsonFields.LONGITUDE).asDouble();
        double latitude = nodeGeolocation.get(JsonFields.LATITUDE).asDouble();
        return new UllGeolocationPoint(longitude, latitude);
    }

    /**
     * Validates the longitude and latitude of a JsonNode.
     * 
     * @param nodeGeolocation The JsonNode that contains the longitude and latitude.
     */
    private static void validate(JsonNode nodeGeolocation) {
        if (nodeGeolocation.has(JsonFields.LONGITUDE)) {
            JsonNode nodeLongitude = nodeGeolocation.get(JsonFields.LONGITUDE);
            if (nodeLongitude.isNumber()) {
                double longitude = nodeLongitude.asDouble();
                validateLongitude(longitude);
            } else {
                throw new IllegalArgumentException(ERROR_LONGITUDE_WRONG_FORMAT);
            }
        } else {
            throw new IllegalArgumentException(ERROR_LONGITUDE_NOT_DEFINED);
        }
        if (nodeGeolocation.has(JsonFields.LATITUDE)) {
            JsonNode nodeLatitude = nodeGeolocation.get(JsonFields.LATITUDE);
            if (nodeLatitude.isNumber()) {
                double latitude = nodeLatitude.asDouble();
                validateLatitude(latitude);
            } else {
                throw new IllegalArgumentException(ERROR_LONGITUDE_WRONG_FORMAT);
            }
        } else {
            throw new IllegalArgumentException(ERROR_LATITUDE_NOT_DEFINED);
        }
    }

    /**
     * Validates the longitude and latitude.
     * 
     * @param longitude Longitude of the point.
     * @param latitude  Latitude of the point.
     */
    private static void validate(double longitude, double latitude) {
        UllGeolocationPoint.validateLongitude(longitude);
        UllGeolocationPoint.validateLatitude(latitude);
    }

    /**
     * Validates the longitude.
     * 
     * @param longitude Longitude of the point.
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
     * Validates the latitude.
     * 
     * @param latitude Latitude of the point.
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
     * Compares the point with another object.
     * 
     * @return True if the point is equals to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UllGeolocationPoint)) {
            return false;
        }
        UllGeolocationPoint otherPoint = (UllGeolocationPoint) other;
        return (this.longitude == otherPoint.longitude &&
                this.latitude == otherPoint.latitude);
    }

    /**
     * Returns the longitude of the point.
     * 
     * @return The longitude of the point.
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Returns the latitude of the point.
     * 
     * @return The latitude of the point.
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Sets the longitude of the point.
     * 
     * @param longitude The longitude of the point.
     * @return A new instance of UllGeolocationPoint with the new longitude.
     */
    public UllGeolocationPoint setLongitude(double longitude) {
        UllGeolocationPoint.validateLongitude(longitude);
        return new UllGeolocationPoint(longitude, this.latitude);
    }

    /**
     * Sets the latitude of the point.
     * 
     * @param latitude The latitude of the point.
     * @return A new instance of UllGeolocationPoint with the new latitude.
     */
    public UllGeolocationPoint setLatitude(double latitude) {
        UllGeolocationPoint.validateLatitude(latitude);
        return new UllGeolocationPoint(this.longitude, latitude);
    }

    /**
     * Hash code of the point.
     * 
     * @return The hash code of the point.
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
     * Returns a random point in the Earth's surface.
     * 
     * @return A random point in the Earth's surface.
     */
    public static UllGeolocationPoint random() {
        double longitude = UllDouble.random(LONGITUDE_MIN, LONGITUDE_MAX);
        double latitude = UllDouble.random(LATITUDE_MIN, LATITUDE_MAX);
        return new UllGeolocationPoint(longitude, latitude);
    }

    /**
     * Returns a string representation of the point.
     * 
     * @return A string representation of the point.
     */
    @Override
    public String toString() {
        return "UllGeolocationPoint={" +
                "longitude=" + this.getLongitude() + "," +
                "latitude=" + this.getLatitude() +
                "}";
    }
}

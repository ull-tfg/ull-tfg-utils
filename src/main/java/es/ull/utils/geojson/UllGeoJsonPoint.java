package es.ull.utils.geojson;

import com.fasterxml.jackson.databind.JsonNode;
import es.ull.utils.geojson.definition.UllGeoJsonGeometryType;
import es.ull.utils.geojson.input.UllGeoJsonFields;
import es.ull.utils.geojson.utils.UllGeoJsonUtils;
import es.ull.utils.jackson.UllJackson;
import es.ull.utils.json.UllJson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that represents a point in GeoJson format.
 * 
 */
public class UllGeoJsonPoint extends UllGeoJsonGeometry {

    public static final String ERROR_POSITION_NOT_DEFINED = "The position of the point is required";
    public static final String ERROR_DIMENSIONS = "The array must have at most three elements";
    public static final String ERROR_PARSE_DOUBLE = "The array must contain only numbers";
    public static final String ERROR_COORDINATES_NOT_DEFINED = "Coordinates are not defined";
    public static final String ERROR_COORDINATES_WRONG_FORMAT = "Coordinates must be an array";
    /**
     * Position that forms the point. It is a required attribute.
     */
    private UllGeoJsonPosition position;

    /**
     * Constructor for a point with a position.
     * 
     * @param position the position that forms the point.
     * @throws IllegalArgumentException if the position is null.
     */
    public UllGeoJsonPoint(UllGeoJsonPosition position) {
        this.validate(position);
        this.position = position;
    }

    /**
     * Constructor for a point with longitude and latitude.
     * 
     * @param longitude longitude of the point.
     * @param latitude  latitude of the point.
     */
    public UllGeoJsonPoint(double longitude, double latitude) {
        this.position = new UllGeoJsonPosition(longitude, latitude);
    }

    /**
     * Constructor for a point with longitude, latitude and altitude.
     * 
     * @param longitude longitude of the point.
     * @param latitude  latitude of the point.
     * @param altitude  altitude of the point.
     */
    public UllGeoJsonPoint(double longitude, double latitude, double altitude) {
        this.position = new UllGeoJsonPosition(longitude, latitude, altitude);
    }

    /**
     * Copy constructor for a point.
     * 
     * @param point the point to copy.
     */
    public UllGeoJsonPoint(UllGeoJsonPoint point) {
        this.validate(position);
        this.position = point.getPosition();
    }

    /**
     * Validate the constraints specific to point.
     * 
     * @param position the position that forms the point.
     */
    private void validate(UllGeoJsonPosition position) {
        if (position == null) {
            throw new IllegalArgumentException(ERROR_POSITION_NOT_DEFINED);
        }
    }

    /**
     * Method to compare two Points
     * 
     * @param otherObject the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof UllGeoJsonPoint)) {
            return false;
        }
        final UllGeoJsonPoint otherPoint = (UllGeoJsonPoint) otherObject;
        return this.position.equals(otherPoint.position);
    }

    /**
     * Method to obtain the hash code of the point.
     * 
     * @return the hash code of the point
     */
    @Override
    public int hashCode() {
        return this.position.hashCode();
    }

    /**
     * It gets the position that forms the point.
     * 
     * @return the position of the point.
     */
    public UllGeoJsonPosition getPosition() {
        return this.position;
    }

    /**
     * It obtains a point from a JsonNode.
     * 
     * @param json the JSON object to obtain the point from
     * @return the point obtained from the JSON object
     * @throws IllegalArgumentException if the JSON object is not valid
     */
    public static UllGeoJsonPoint from(JsonNode json) {
        UllGeoJsonUtils.validateType(json, UllGeoJsonGeometryType.POINT);
        if (!json.has(UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_NOT_DEFINED);
        }
        if (!UllJackson.canBeParsedToArray(json, UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_WRONG_FORMAT);
        }
        final JSONArray coordinates = new JSONArray(json.get(UllGeoJsonFields.COORDINATES).toString());
        final UllGeoJsonPosition positionGeoJson = UllGeoJsonPosition.from(coordinates);
        return new UllGeoJsonPoint(positionGeoJson);
    }

    /**
     * It obtains a point from a JSON object.
     * 
     * @param json the JSON object to obtain the point from
     * @return the point obtained from the JSON object
     * @throws IllegalArgumentException if the JSON object is not valid
     */
    public static UllGeoJsonPoint from(JSONObject json) {
        UllGeoJsonUtils.validateType(json, UllGeoJsonGeometryType.POINT);
        if (!json.has(UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_NOT_DEFINED);
        }
        if (!UllJson.canBeParsedToArray(json, UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_WRONG_FORMAT);
        }
        final JSONArray coordinates = json.getJSONArray(UllGeoJsonFields.COORDINATES);
        final UllGeoJsonPosition positionGeoJson = UllGeoJsonPosition.from(coordinates);
        return new UllGeoJsonPoint(positionGeoJson);
    }

    /**
     * It obtains a point from a string.
     * 
     * @param string the string to obtain the point from
     * @return the point obtained from the string
     */
    public static UllGeoJsonPoint from(String string) {
        final JSONObject json = new JSONObject(string);
        return UllGeoJsonPoint.from(json);
    }

    /**
     * It obtains a JSON representation of the point.
     * 
     * @return the JSON representation of the point
     */
    @Override
    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put(UllGeoJsonFields.TYPE, UllGeoJsonGeometryType.POINT.toString());
        json.put(UllGeoJsonFields.COORDINATES, this.getPosition().toJson());
        return json;
    }

    /**
     * It obtains a string representation of the point.
     * 
     * @return the string representation of the point.
     */
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}

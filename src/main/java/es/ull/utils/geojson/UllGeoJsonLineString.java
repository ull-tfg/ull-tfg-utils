package es.ull.utils.geojson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.ull.utils.geojson.definition.UllGeoJsonGeometryType;
import es.ull.utils.geojson.input.UllGeoJsonFields;
import es.ull.utils.geojson.utils.UllGeoJsonUtils;
import es.ull.utils.json.UllJson;

public class UllGeoJsonLineString extends UllGeoJsonGeometry {

    public static final String ERROR_DIMENSIONS = "The array must have at most three elements";
    public static final String ERROR_PARSE_DOUBLE = "The array must contain only numbers";
    public static final String ERROR_COORDINATES_NOT_DEFINED = "The coordinates of the LineString are required";
    public static final String ERROR_COORDINATES_WRONG_FORMAT = "Coordinates must be an array";
    public static final String ERROR_COORDINATES_ELEMENTS_WRONG_FORMAT = "Coordinates must be an array of points";
    public static final String ERROR_MINIMUM_POSITIONS = "The LineString must have at least two positions";
    /**
     * List of positions that form the LineString.
     */
    private List<UllGeoJsonPosition> positions;

    /**
     * Constructor for a LineString with an empty list of positions
     */
    public UllGeoJsonLineString() {
        this.positions = new ArrayList<>();
    }

    /**
     * Constructor for a LineString with a list of positions
     * 
     * @param positions the list of positions that form the LineString
     */
    public UllGeoJsonLineString(List<UllGeoJsonPosition> positions) {
        this.validate(positions);
        this.positions = positions;
    }

    /**
     * Constructor for a LineString with another LineString
     * 
     * @param lineString the LineString to copy
     * @throws IllegalArgumentException if the lineString is null
     */
    public UllGeoJsonLineString(UllGeoJsonLineString lineString) {
        this.positions = new ArrayList<>();
        for (UllGeoJsonPosition position : lineString.getPositions()) {
            this.positions.add(position);
        }
    }

    /**
     * Validate the constraints specific to LineString
     * 
     * @param positions the list of positions that form the LineString
     */
    private void validate(List<UllGeoJsonPosition> positions) {
        if (positions == null) {
            throw new IllegalArgumentException(ERROR_COORDINATES_NOT_DEFINED);
        }
        if (positions.size() < 2) {
            throw new IllegalArgumentException(ERROR_MINIMUM_POSITIONS);
        }
    }

    /**
     * It adds a position to the LineString
     * 
     * @param position the position to add
     */
    public void addPosition(UllGeoJsonPosition position) {
        this.positions.add(position);
    }

    /**
     * It checks if the LineString is equal to another object
     * 
     * @param otherObject the object to compare
     * @return true if the LineString is equal to the object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }
        final UllGeoJsonLineString otherLineString = (UllGeoJsonLineString) otherObject;
        return this.positions.equals(otherLineString.positions);
    }

    /**
     * Method to get the hash code of the LineString
     * 
     * @return the hash code of the LineString
     */
    @Override
    public int hashCode() {
        return this.positions.hashCode();
    }

    /**
     * Method to get the list of positions that form the LineString
     * 
     * @return the list of positions
     */
    public List<UllGeoJsonPosition> getPositions() {
        return Collections.unmodifiableList(this.positions);
    }

    /**
     * It gets a position from the LineString
     * 
     * @param index the index of the position
     * @return the position
     */
    public UllGeoJsonPosition getPosition(int index) {
        return this.positions.get(index);
    }

    /**
     * It gets the number of positions that form the LineString
     * 
     * @return the number of positions
     */
    public int getNumberOfPositions() {
        return this.positions.size();
    }

    /**
     * It obtains a LineString from a JSON object.
     * 
     * @param json the JSON object to obtain the LineString from
     * @return the LineString obtained from the JSON object
     * @throws IllegalArgumentException if the JSON object is not a valid LineString
     */
    protected static List<UllGeoJsonPosition> extractPositions(JSONObject json) {
        if (!json.has(UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_NOT_DEFINED);
        }
        if (!UllJson.canBeParsedToArray(json, UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_WRONG_FORMAT);
        }
        final JSONArray coordinates = json.getJSONArray(UllGeoJsonFields.COORDINATES);
        if (!UllJson.canAllBeParsedToArray(coordinates)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_ELEMENTS_WRONG_FORMAT);
        }
        return UllGeoJsonLineString.extractPositions(coordinates);
    }

    /**
     * It obtains a list of positions from a JSON array.
     * 
     * @param coordinates the JSON array to obtain the positions from
     * @return the list of positions obtained from the JSON array
     * @throws IllegalArgumentException if the JSON array is not valid
     * @throws IllegalArgumentException if the JSON array does not have at least two positions
     */
    protected static List<UllGeoJsonPosition> extractPositions(JSONArray coordinates) {
        if (coordinates.length() < 2) {
            throw new IllegalArgumentException(ERROR_MINIMUM_POSITIONS);
        }
        final List<UllGeoJsonPosition> positions = new ArrayList<>();
        for (int index = 0; index < coordinates.length(); index++) {
            final UllGeoJsonPosition position = UllGeoJsonPosition.from(coordinates.getJSONArray(index));
            positions.add(position);
        }
        return positions;
    }

    /**
     * It creates a LineString from a JSON object
     * 
     * @param json the JSON object to obtain the LineString from
     * @return the LineString obtained from the JSON object
     * @throws IllegalArgumentException if the JSON object is not a valid LineString
     */
    public static UllGeoJsonLineString from(JSONObject json) {
        UllGeoJsonUtils.validateType(json, UllGeoJsonGeometryType.LINE_STRING);
        final List<UllGeoJsonPosition> positions = UllGeoJsonLineString.extractPositions(json);
        return new UllGeoJsonLineString(positions);
    }

    /**
     * It creates a LineString from a string
     * 
     * @param string the string to obtain the LineString from
     */
    public static UllGeoJsonLineString from(String string) {
        final JSONObject json = new JSONObject(string);
        return UllGeoJsonLineString.from(json);
    }

    /**
     * Method to obtain a JSON representation of the LineString
     * 
     * @return the JSON representation of the LineString
     */
    @Override
    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put(UllGeoJsonFields.TYPE, UllGeoJsonGeometryType.LINE_STRING.toString());
        json.put(UllGeoJsonFields.COORDINATES, UllGeoJsonUtils.toJson(this.getPositions()));
        return json;
    }

    /**
     * It gets the string representation of the LineString
     * 
     * @return the string representation of the LineString
     */
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}

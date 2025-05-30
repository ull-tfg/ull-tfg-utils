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

/**
 * Class that represents a MultiPoint in GeoJson format.
 * 
 */
public class UllGeoJsonMultiPoint extends UllGeoJsonGeometry {

    public static final String ERROR_COORDINATES_NOT_DEFINED = "The coordinates of the MultiPoint are required";
    public static final String ERROR_COORDINATES_MINIMUM_POSITIONS = "The coordinates member of the MultiPoint must have at least one position";
    public static final String ERROR_COORDINATES_WRONG_FORMAT = "Coordinates must be an array";
    public static final String ERROR_POSITIONS_NOT_DEFINED = "The coordinates of the MultiPoint are required";
    public static final String ERROR_COORDINATES_WRONG_POSITIONS = "The coordinates of the MultiPoint must be an array of arrays";
    /**
     * List of positions that form the MultiPoint
     */
    private List<UllGeoJsonPosition> positions;

    /**
     * Constructor for a MultiPoint with an empty list of positions
     * 
     * @param positions the list of positions that form the MultiPoint
     */
    public UllGeoJsonMultiPoint() {
        this.positions = new ArrayList<>();
    }

    /**
     * Constructor for a MultiPoint with a list of positions
     * 
     * @param positions the list of positions that form the MultiPoint
     */
    public UllGeoJsonMultiPoint(List<UllGeoJsonPosition> positions) {
        this.validate(positions);
        this.positions = positions;
    }

    /**
     * Constructor for a MultiPoint with another MultiPoint
     * 
     * @param other the MultiPoint to copy
     * @throws IllegalArgumentException if the other MultiPoint is null
     */
    public UllGeoJsonMultiPoint(UllGeoJsonMultiPoint other) {
        this.positions = new ArrayList<>();
        for (UllGeoJsonPosition position : other.getPositions()) {
            this.positions.add(position);
        }
    }

    /**
     * Validate the constraints specific to MultiPoint
     * 
     * @param positions the list of positions that form the MultiPoint
     */
    private void validate(List<UllGeoJsonPosition> positions) {
        if (positions == null) {
            throw new IllegalArgumentException(ERROR_POSITIONS_NOT_DEFINED);
        }
    }

    /**
     * Method to add a position to the MultiPoint
     * 
     * @param position the position to add
     */
    public void addPoint(UllGeoJsonPosition position) {
        this.positions.add(position);
    }

    /**
     * Method to get the list of positions that form the MultiPoint
     * 
     * @return the list of positions
     */
    public List<UllGeoJsonPosition> getPositions() {
        return Collections.unmodifiableList(positions);
    }

    /**
     * Method to get the number of positions that form the MultiPoint
     * 
     * @return the number of positions
     */
    public int getNumberOfPositions() {
        return this.positions.size();
    }

    /**
     * It obtains a MultiPoint from a JSON object.
     * 
     * @param json the JSON object to obtain the MultiPoint from
     * @return the MultiPoint obtained from the JSON object
     */
    public static UllGeoJsonMultiPoint from(JSONObject json) {
        UllGeoJsonUtils.validateType(json, UllGeoJsonGeometryType.MULTI_POINT);
        if (!json.has(UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_NOT_DEFINED);
        }
        if (!UllJson.canBeParsedToArray(json, UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_WRONG_FORMAT);
        }
        final JSONArray coordinates = json.getJSONArray(UllGeoJsonFields.COORDINATES);
        if (coordinates.length() < 1) {
            throw new IllegalArgumentException(ERROR_COORDINATES_MINIMUM_POSITIONS);
        }
        if (!UllJson.canAllBeParsedToArray(coordinates)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_WRONG_POSITIONS);
        }
        final List<UllGeoJsonPosition> positions = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            final JSONArray coordinate = coordinates.getJSONArray(i);
            final UllGeoJsonPosition position = UllGeoJsonPosition.from(coordinate);
            positions.add(position);
        }
        return new UllGeoJsonMultiPoint(positions);
    }

    /**
     * It obtains a MultiPoint from a string.
     * 
     * @param string the string to obtain the MultiPoint from
     * @return the MultiPoint obtained from the string
     * @throws IllegalArgumentException if the string is not valid
     */
    public static UllGeoJsonMultiPoint from(String string) {
        final JSONObject json = new JSONObject(string);
        return UllGeoJsonMultiPoint.from(json);
    }

    /**
     * It obtains a JSON representation of the MultiPoint
     * 
     * @return the JSON representation of the MultiPoint
     */
    @Override
    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put(UllGeoJsonFields.TYPE, UllGeoJsonGeometryType.MULTI_POINT.toString());
        json.put(UllGeoJsonFields.COORDINATES, UllGeoJsonUtils.toJson(this.getPositions()));
        return json;
    }

    /**
     * It gets the string representation of the MultiPoint
     * 
     * @return the string representation of the MultiPoint
     */
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}

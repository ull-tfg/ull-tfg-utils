package es.ull.utils.geojson;

import es.ull.utils.geojson.definition.UllGeoJsonGeometryType;
import es.ull.utils.geojson.input.UllGeoJsonFields;
import es.ull.utils.geojson.utils.UllGeoJsonUtils;
import es.ull.utils.json.UllJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that represents a MultiLineString in GeoJson format.
 * 
 */
public class UllGeoJsonMultiLineString extends UllGeoJsonGeometry {

    public static final String ERROR_COORDINATES_NOT_DEFINED = "The coordinates of the MultiLineString are required";
    public static final String ERROR_COORDINATES_WRONG_FORMAT = "Coordinates must be an array";
    public static final String ERROR_LINESTRINGS_NOT_DEFINED = "The LineStrings of the MultiLineString are required";
    public static final String ERROR_COORDINATES_MINIMUM_POSITIONS = "The coordinates member of the MultiLineString must have at least one position";
    public static final String ERROR_COORDINATES_WRONG_POSITIONS = "The coordinates of the MultiLineString must be an array of arrays";
    /**
     * List of LineStrings that form the MultiLineString
     */
    private List<UllGeoJsonLineString> lineStrings;

    /**
     * Constructor for a MultiLineString with an empty list of LineStrings
     */
    public UllGeoJsonMultiLineString() {
        this.lineStrings = new ArrayList<>();
    }

    /**
     * Constructor for a MultiLineString with a list of LineStrings
     * 
     * @param lineStrings the list of LineStrings that form the MultiLineString
     */
    public UllGeoJsonMultiLineString(List<UllGeoJsonLineString> lineStrings) {
        this.validate(lineStrings);
        this.lineStrings = lineStrings;
    }

    public UllGeoJsonMultiLineString(UllGeoJsonMultiLineString multiLineString) {
        this.lineStrings = new ArrayList<>();
        for (UllGeoJsonLineString lineString : multiLineString.getLineStrings()) {
            this.lineStrings.add(lineString);
        }
    }

    /**
     * Validate the constraints specific to MultiLineString
     * 
     * @param lineStrings the list of LineStrings that form the MultiLineString
     */
    private void validate(List<UllGeoJsonLineString> lineStrings) {
        if (lineStrings == null) {
            throw new IllegalArgumentException(ERROR_LINESTRINGS_NOT_DEFINED);
        }
    }

    /**
     * Method to add a LineString to the MultiLineString
     * 
     * @param lineString the LineString to add
     */
    public void addLineString(UllGeoJsonLineString lineString) {
        this.lineStrings.add(lineString);
    }

    /**
     * Method to get the list of LineStrings that form the MultiLineString
     * 
     * @return the list of LineStrings
     */
    public List<UllGeoJsonLineString> getLineStrings() {
        return Collections.unmodifiableList(this.lineStrings);
    }

    /**
     * Method to get the number of LineStrings that form the MultiLineString
     * 
     * @return the number of LineStrings
     */
    public int getNumberOfLineStrings() {
        return this.lineStrings.size();
    }

    public static UllGeoJsonMultiLineString from(JSONObject json) {
        UllGeoJsonUtils.validateType(json, UllGeoJsonGeometryType.MULTI_LINE_STRING);
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
        final List<UllGeoJsonLineString> lineStrings = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            final JSONArray coordinate = coordinates.getJSONArray(i);
            final List<UllGeoJsonPosition> positions = UllGeoJsonLineString.extractPositions(coordinate);
            final UllGeoJsonLineString lineString = new UllGeoJsonLineString(positions);
            lineStrings.add(lineString);
        }
        return new UllGeoJsonMultiLineString(lineStrings);
    }

    /**
     * Method to obtain a JSON representation of the MultiLineString
     * 
     * @return the JSON representation of the MultiLineString
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put(UllGeoJsonFields.TYPE, UllGeoJsonGeometryType.MULTI_LINE_STRING.toString());
        final JSONArray linesJson = new JSONArray();
        for (UllGeoJsonLineString lineString : this.lineStrings) {
            final JSONArray lineJson = new JSONArray();
            for (UllGeoJsonPosition position : lineString.getPositions()) {
                lineJson.put(position.toJson());
            }
            linesJson.put(lineJson);
        }
        json.put(UllGeoJsonFields.COORDINATES, linesJson);
        return json;
    }

    /**
     * Method to obtain a String representation of the MultiLineString
     * 
     * @return the String representation of the MultiLineString
     */
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}

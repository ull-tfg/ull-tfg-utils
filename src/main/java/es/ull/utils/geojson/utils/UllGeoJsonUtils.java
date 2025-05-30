package es.ull.utils.geojson.utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import es.ull.utils.geojson.UllGeoJsonPosition;
import es.ull.utils.geojson.definition.UllGeoJsonGeometryType;
import es.ull.utils.geojson.input.UllGeoJsonFields;
import es.ull.utils.lang.UllClass;

/**
 * Utility class for GeoJSON operations.
 * 
 */
public class UllGeoJsonUtils {

    public static final String ERROR_TYPE_EMPTY = "GeoJSON type is empty";
    public static final String ERROR_TYPE_NOT_DEFINED = "GeoJSON type is not defined";
    public static final String ERROR_TYPE_NOT_VALID = "GeoJSON type is not valid";
    public static final String ERROR_TYPE_UNKNOWN = "GeoJSON type is unknown";
    public static final String ERROR_JSON_UNDEFINED = "JSON is not defined";

    /**
     * Private constructor to prevent instantiation of the utility class. This class should not be instantiated.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private UllGeoJsonUtils() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * Converts a list of coordinates to a JSON array.
     * 
     * @param coordinates the list of coordinates to convert
     * @return the JSON array representation of the coordinates
     */
    public static JSONArray toJson(List<UllGeoJsonPosition> coordinates) {
        final JSONArray json = new JSONArray();
        for (UllGeoJsonPosition position : coordinates) {
            json.put(position.toJson());
        }
        return json;
    }

    /**
     * Validates the type of a GeoJSON object.
     * 
     * @param json        the GeoJSON object to validate
     * @param typeToCheck the expected type to check against
     * @throws IllegalArgumentException if the type is not defined, empty, unknown, or not valid
     */
    public static void validateType(JsonNode json, UllGeoJsonGeometryType typeToCheck) {
        if (json == null) {
            throw new IllegalArgumentException(ERROR_JSON_UNDEFINED);
        }
        if (!json.has(UllGeoJsonFields.TYPE)) {
            throw new IllegalArgumentException(ERROR_TYPE_NOT_DEFINED);
        }
        Object type = json.get(UllGeoJsonFields.TYPE);
        if (type == null) {
            throw new IllegalArgumentException(ERROR_TYPE_EMPTY);
        }
        final String typeAsString = json.get(UllGeoJsonFields.TYPE).asText();
        if (!UllGeoJsonGeometryType.isValid(typeAsString)) {
            throw new IllegalArgumentException(ERROR_TYPE_UNKNOWN);
        }
        final UllGeoJsonGeometryType obtainedType = UllGeoJsonGeometryType.fromString(typeAsString);
        if (!obtainedType.equals(typeToCheck)) {
            throw new IllegalArgumentException(ERROR_TYPE_NOT_VALID);
        }
    }

    /**
     * Validates the type of a GeoJSON object.
     * 
     * @param json        the GeoJSON object to validate
     * @param typeToCheck the expected type to check against
     * @throws IllegalArgumentException if the type is not defined, empty, unknown, or not valid
     */
    public static void validateType(JSONObject json, UllGeoJsonGeometryType typeToCheck) {
        if (json == null) {
            throw new IllegalArgumentException(ERROR_JSON_UNDEFINED);
        }
        if (!json.has(UllGeoJsonFields.TYPE)) {
            throw new IllegalArgumentException(ERROR_TYPE_NOT_DEFINED);
        }
        Object type = json.get(UllGeoJsonFields.TYPE);
        if (type == null) {
            throw new IllegalArgumentException(ERROR_TYPE_EMPTY);
        }
        final String typeAsString = type.toString();
        if (!UllGeoJsonGeometryType.isValid(typeAsString)) {
            throw new IllegalArgumentException(ERROR_TYPE_UNKNOWN);
        }
        final UllGeoJsonGeometryType obtainedType = UllGeoJsonGeometryType.fromString(typeAsString);
        if (!obtainedType.equals(typeToCheck)) {
            throw new IllegalArgumentException(ERROR_TYPE_NOT_VALID);
        }
    }
}
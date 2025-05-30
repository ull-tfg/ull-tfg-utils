package es.ull.utils.geojson.definition;

import java.util.Random;

/**
 * Class that contains the types of GeoJson objects.
 * 
 */
public enum UllGeoJsonType {

    FEATURE("Feature"),
    FEATURE_COLLECTION("FeatureCollection"),
    LINE_STRING("LineString"),
    MULTI_LINE_STRING("MultiLineString"),
    MULTI_POINT("MultiPoint"),
    MULTI_POLYGON("MultiPolygon"),
    POINT("Point"),
    POLYGON("Polygon"),
    GEOMETRY_COLLECTION("GeometryCollection");

    private String type;

    private UllGeoJsonType(String type) {
        this.type = type;
    }

    /**
     * Random instance for generating random values.
     */
    private static final Random RANDOM = new Random();

    /**
     * Returns the GeoJson geometry type from a string.
     * 
     * @param stringToCheck the string to check
     * @return the GeoJson geometry type
     * @throws IllegalArgumentException if the string does not match any geometry type
     */
    public static UllGeoJsonType fromString(String stringToCheck) {
        if (stringToCheck == null) {
            throw new IllegalArgumentException();
        }
        stringToCheck = stringToCheck.trim();
        for (UllGeoJsonType types : values()) {
            if (types.type.equals(stringToCheck)) {
                return types;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns the index of the GeoJson geometry type.
     * 
     * @param stringToCheck the string to check
     * @return the index of the GeoJson geometry type
     */
    public static int indexOf(String stringToCheck) {
        return UllGeoJsonType
                .fromString(stringToCheck)
                .ordinal();
    }

    /**
     * Checks if the string is a valid GeoJson geometry type.
     * 
     * @param stringToCheck the string to check
     * @return {@code true} if the string is a valid GeoJson geometry type; {@code false} otherwise
     */
    public static boolean isValid(String stringToCheck) {
        if (stringToCheck == null) {
            return false;
        }
        stringToCheck = stringToCheck.trim();
        for (UllGeoJsonType types : values()) {
            if (types.type.equals(stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a random GeoJson type.
     * 
     * @return a random GeoJson type
     */
    public static UllGeoJsonType random() {
        return values()[RANDOM.nextInt(values().length)];
    }

    /**
     * Returns the string representation of the GeoJson type.
     * 
     * @return the string representation of the GeoJson type
     */
    @Override
    public String toString() {
        return this.type;
    }
}

package es.ull.utils.geojson;

import es.ull.utils.geojson.definition.UllGeoJsonGeometryType;
import es.ull.utils.geojson.input.UllGeoJsonFields;
import es.ull.utils.geojson.utils.UllGeoJsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that represents a polygon in GeoJson format.
 * 
 * @author Ull Analytics (development@ull.edu.es)
 */
public class UllGeoJsonPolygon extends UllGeoJsonGeometry {

    public static final String ERROR_COORDINATES_NOT_DEFINED = "The coordinates of the polygon are required";
    public static final String ERROR_COORDINATES_WRONG_FORMAT = "Coordinates must be an array";
    public static final String ERROR_RING_NOT_DEFINED = "The ring is not defined";
    public static final String ERROR_INVALID_COORDINATE = "Invalid %s value: %s";
    /**
     * The exterior ring of the polygon
     */
    private UllGeoJsonLinearRing exteriorRing;
    /**
     * List of interior rings of the polygon
     */
    private List<UllGeoJsonLinearRing> interiorRings;

    /**
     * Constructor for a polygon with an exterior ring and an empty list of interior
     * 
     * @param exteriorRing the exterior ring of the polygon
     */
    public UllGeoJsonPolygon(UllGeoJsonLinearRing exteriorRing) {
        this.validate(exteriorRing);
        this.exteriorRing = exteriorRing;
        this.interiorRings = new ArrayList<>();
    }

    /**
     * Copy constructor to create a new polygon based on an existing one
     * 
     * @param other the polygon to copy
     */
    public UllGeoJsonPolygon(UllGeoJsonPolygon other) {
        this.exteriorRing = new UllGeoJsonLinearRing(other.exteriorRing);
        this.interiorRings = new ArrayList<>(other.interiorRings);
    }

    /**
     * It validates the constraints specific to the polygon
     * 
     * @param ring the ring to validate
     */
    private void validate(UllGeoJsonLinearRing ring) {
        if (ring == null) {
            throw new IllegalArgumentException(ERROR_RING_NOT_DEFINED);
        }
    }

    /**
     * It checks if the polygon is equal to another object
     * 
     * @param otherObject the object to compare
     * @return true if the polygon is equal to the object, false otherwise
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
        final UllGeoJsonPolygon otherPolygon = (UllGeoJsonPolygon) otherObject;
        if (!this.exteriorRing.equals(otherPolygon.exteriorRing)) {
            return false;
        }
        if (this.interiorRings.size() != otherPolygon.interiorRings.size()) {
            return false;
        }
        for (int i = 0; i < this.interiorRings.size(); i++) {
            if (!this.interiorRings.get(i).equals(otherPolygon.interiorRings.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * It generates the hash code of the polygon
     * 
     * @return the hash code of the polygon
     */
    @Override
    public int hashCode() {
        int result = this.exteriorRing.hashCode();
        for (UllGeoJsonLinearRing ring : this.interiorRings) {
            result = 31 * result + ring.hashCode();
        }
        return result;
    }

    /**
     * It adds an interior ring to the polygon
     * 
     * @param ring the interior ring to add
     */
    public void addInteriorRing(UllGeoJsonLinearRing ring) {
        this.validate(ring);
        this.interiorRings.add(ring);
    }

    /**
     * Method to get the exterior ring of the polygon
     * 
     * @return the exterior ring
     */
    public UllGeoJsonLinearRing getExteriorRing() {
        return this.exteriorRing;
    }

    /**
     * Method to get the list of interior rings of the polygon
     * 
     * @return the list of interior rings
     */
    public List<UllGeoJsonLinearRing> getInteriorRings() {
        return Collections.unmodifiableList(this.interiorRings);
    }

    /**
     * Method to get the number of interior rings of the polygon
     * 
     * @return the number of interior rings
     */
    public int getNumberOfInteriorRings() {
        return this.interiorRings.size();
    }

    /**
     * Method to check if the polygon has interior rings
     * 
     * @return true if the polygon has interior rings, false otherwise
     */
    public boolean hasInteriorRings() {
        return !this.interiorRings.isEmpty();
    }

    /**
     * It generates a random polygon
     * 
     * @return the random polygon
     */
    public static UllGeoJsonPolygon random() {
        UllGeoJsonLinearRing exterior = UllGeoJsonLinearRing.random();
        UllGeoJsonPolygon polygon = new UllGeoJsonPolygon(exterior);
        final int numberOfInteriorRings = new Random().nextInt(10);
        for (int i = 0; i < numberOfInteriorRings; i++) {
            final UllGeoJsonLinearRing interior = UllGeoJsonLinearRing.random();
            polygon.addInteriorRing(interior);
        }
        return polygon;
    }

    /**
     * Method to create a Polygon from a JSON object
     * 
     * @param string the JSON string representation of the Polygon
     * @return the Polygon created from the JSON string
     * @throws IllegalArgumentException if the JSON string is not valid or does not represent a Polygon
     */
    public static UllGeoJsonPolygon from(String string) {
        final JSONObject json = new JSONObject(string);
        return UllGeoJsonPolygon.from(json);
    }

    /**
     * Method to create a Polygon from a JSON object
     * 
     * @param json the JSON object to create the Polygon from
     * @return the Polygon created from the JSON object
     * @throws IllegalArgumentException if the JSON object is not valid or does not represent a Polygon
     */
    private static UllGeoJsonPolygon from(JSONObject json) {
        UllGeoJsonUtils.validateType(json, UllGeoJsonGeometryType.POLYGON);
        if (!json.has(UllGeoJsonFields.COORDINATES)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_NOT_DEFINED);
        }
        if (!json.get(UllGeoJsonFields.COORDINATES).getClass().equals(JSONArray.class)) {
            throw new IllegalArgumentException(ERROR_COORDINATES_WRONG_FORMAT);
        }
        final JSONArray coordinates = json.getJSONArray(UllGeoJsonFields.COORDINATES);
        if (coordinates.length() == 0) {
            throw new IllegalArgumentException(String.format(ERROR_INVALID_COORDINATE, UllGeoJsonFields.COORDINATES, coordinates));
        }
        final UllGeoJsonLinearRing ring = UllGeoJsonLinearRing.from(coordinates.getJSONArray(0));
        return new UllGeoJsonPolygon(ring);
    }

    /**
     * Method to obtain a JSON representation of the Polygon
     * 
     * @return the JSON representation of the Polygon
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put(UllGeoJsonFields.TYPE, UllGeoJsonGeometryType.POLYGON.toString());
        final JSONArray coordinates = new JSONArray();
        coordinates.put(UllGeoJsonUtils.toJson(this.exteriorRing.getPositions()));
        for (UllGeoJsonLinearRing ring : this.interiorRings) {
            coordinates.put(UllGeoJsonUtils.toJson(ring.getPositions()));
        }
        json.put(UllGeoJsonFields.COORDINATES, coordinates);
        return json;
    }

    /**
     * It obtains a String representation of the Polygon
     * 
     * @return the String representation of the Polygon
     */
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}
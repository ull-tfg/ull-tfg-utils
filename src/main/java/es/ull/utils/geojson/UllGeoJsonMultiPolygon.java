package es.ull.utils.geojson;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.ull.utils.geojson.definition.UllGeoJsonGeometryType;
import es.ull.utils.geojson.input.UllGeoJsonFields;
import es.ull.utils.lang.UllInteger;

/**
 * Class that represents a MultiPolygon in GeoJson format.
 * 
 */
public class UllGeoJsonMultiPolygon extends UllGeoJsonGeometry {

    private static final String ERROR_POLYGONS_NOT_DEFINED = "The polygons must not be null";
    private static final String ERROR_POLYGONS_EMPTY = "The polygons cannot be empty";
    /**
     * List of polygons that form the MultiPolygon
     */
    private List<UllGeoJsonPolygon> polygons;

    /**
     * Constructor for a MultiPolygon with an empty list of polygons
     *
     * @param polygons the list of polygons that form the MultiPolygon
     */
    public UllGeoJsonMultiPolygon(List<UllGeoJsonPolygon> polygons) {
        this.validate(polygons);
        this.polygons = polygons;
    }

    /**
     * Validate the constraints specific to MultiPolygon
     * 
     * @param polygons the list of polygons that form the MultiPolygon
     */
    private void validate(List<UllGeoJsonPolygon> polygons) {
        if (polygons == null) {
            throw new IllegalArgumentException(ERROR_POLYGONS_NOT_DEFINED);
        }
        if (polygons.isEmpty()) {
            throw new IllegalArgumentException(ERROR_POLYGONS_EMPTY);
        }
    }

    /**
     * Method to clone the MultiPolygon
     * 
     * @return the cloned MultiPolygon
     */
    @Override
    public Object clone() {
        UllGeoJsonMultiPolygon newMultiPolygon = new UllGeoJsonMultiPolygon(this.polygons);
        return newMultiPolygon;
    }

    /**
     * It checks if the MultiPolygon is equal to another object
     * 
     * @param otherObject the object to compare
     * @return true if the MultiPolygon is equal to the object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof UllGeoJsonMultiPolygon)) {
            return false;
        }
        UllGeoJsonMultiPolygon otherMultiPolygon = (UllGeoJsonMultiPolygon) otherObject;
        return this.polygons.equals(otherMultiPolygon.getPolygons());
    }

    /**
     * Method to get the list of polygons that form the MultiPolygon
     * 
     * @return the list of polygons
     */
    public List<UllGeoJsonPolygon> getPolygons() {
        return this.polygons;
    }

    /**
     * Method to get the number of polygons that form the MultiPolygon
     * 
     * @return the number of polygons
     */
    public int getNumberOfPolygons() {
        return this.polygons.size();
    }

    /**
     * It generates a random MultiPolygon
     * 
     * @return the random MultiPolygon
     */
    public static UllGeoJsonMultiPolygon random() {
        List<UllGeoJsonPolygon> polygons = new ArrayList<>();
        for (int i = 1; i <= UllInteger.random(1, 5); i++) {
            polygons.add(UllGeoJsonPolygon.random());
        }
        return new UllGeoJsonMultiPolygon(polygons);
    }

    public static UllGeoJsonMultiPolygon from(String text) {
        return null;
    }

    /**
     * Method to obtain a JSON representation of the MultiPolygon
     * 
     * @return the JSON representation of the MultiPolygon
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put(UllGeoJsonFields.TYPE, UllGeoJsonGeometryType.MULTI_POLYGON);
        JSONArray coordinates = new JSONArray();
        for (UllGeoJsonPolygon polygon : this.polygons) {
            JSONArray polygonCoordinates = new JSONArray();
            UllGeoJsonLinearRing exteriorRing = polygon.getExteriorRing();
            JSONArray exterior = new JSONArray();
            for (UllGeoJsonPosition position : exteriorRing.getPositions()) {
                List<Double> coord = List.of(position.getLongitude(), position.getLatitude());
                exterior.put(coord);
            }
            polygonCoordinates.put(exterior);
            for (UllGeoJsonLinearRing ring : polygon.getInteriorRings()) {
                JSONArray interior = new JSONArray();
                for (UllGeoJsonPosition position : ring.getPositions()) {
                    List<Double> coord = List.of(position.getLongitude(), position.getLatitude());
                    interior.put(coord);
                }
                polygonCoordinates.put(interior);
            }
            coordinates.put(polygonCoordinates);
        }
        json.put(UllGeoJsonFields.COORDINATES, coordinates);
        return json;
    }

    /**
     * Method to obtain a String representation of the MultiPolygon
     * 
     * @return the String representation of the MultiPolygon
     */
    @Override
    public String toString() {
        String representation = "UllGeoJsonMultiPolygon={";
        for (int i = 0; i < this.polygons.size(); i++) {
            UllGeoJsonPolygon polygon = this.polygons.get(i);
            representation += polygon.toString();
            if (i != this.polygons.size() - 1) {
                representation += ",";
            }
        }
        representation += "}";
        return representation;
    }
}

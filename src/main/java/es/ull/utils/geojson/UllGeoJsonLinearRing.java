package es.ull.utils.geojson;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.ull.utils.lang.UllInteger;

public class UllGeoJsonLinearRing extends UllGeoJsonLineString {

    public static final String ERROR_MINIMUM_POSITIONS = "A LinearRing must have at least four positions";
    public static final String ERROR_FIRST_LAST_NOT_IDENTICAL = "The first and last positions in a linear ring must be identical";
    public static final String ERROR_JSON_NOT_DEFINED = "JSON object cannot be null";
    public static final int MINIMUM_NUMBER_OF_POSITIONS = 4;

    /**
     * Constructor for a LinearRing
     * 
     * @param positions the list of positions that form the LinearRing
     * @throws IllegalArgumentException if the constraints are not met
     */
    public UllGeoJsonLinearRing(List<UllGeoJsonPosition> positions) throws IllegalArgumentException {
        super(positions);
        validateLinearRing();
    }

    /**
     * Constructor for a LinearRing with another LinearRing
     * 
     * @param linearRing the LinearRing to copy
     * @throws IllegalArgumentException if the linearRing is null
     */
    public UllGeoJsonLinearRing(UllGeoJsonLinearRing linearRing) throws IllegalArgumentException {
        super(linearRing);
        validateLinearRing();
    }

    /**
     * Validate the constraints specific to LinearRing
     * 
     * @throws IllegalArgumentException if the constraints are not met
     */
    private void validateLinearRing() throws IllegalArgumentException {
        final int coordinates = super.getNumberOfPositions();
        if (coordinates < MINIMUM_NUMBER_OF_POSITIONS) {
            throw new IllegalArgumentException(ERROR_MINIMUM_POSITIONS);
        }
        final UllGeoJsonPosition firstPosition = super.getPosition(0);
        final UllGeoJsonPosition lastPosition = super.getPosition(coordinates - 1);
        if (!firstPosition.equals(lastPosition)) {
            throw new IllegalArgumentException(ERROR_FIRST_LAST_NOT_IDENTICAL);
        }
    }

    /**
     * It obtains a list of positions from a JSON object.
     * 
     * @param json the JSON object to obtain the positions from
     * @return the list of positions obtained from the JSON object
     * @throws IllegalArgumentException if the JSON object is not valid
     */
    public static UllGeoJsonLinearRing from(JSONObject json) {
        final List<UllGeoJsonPosition> positions = UllGeoJsonLineString.extractPositions(json);
        return new UllGeoJsonLinearRing(positions);
    }

    /**
     * It obtains a LinearRing from a JSON string.
     * 
     * @param string the JSON string to obtain the LinearRing from
     * @return the LinearRing obtained from the JSON string
     * @throws IllegalArgumentException if the JSON string is not valid
     */
    public static UllGeoJsonLinearRing from(String string) {
        final JSONObject json = new JSONObject(string);
        return UllGeoJsonLinearRing.from(json);
    }

    /**
     * It creates a LinearRing from a JSON array of coordinates.
     * 
     * @param coordinates the JSON array of coordinates to obtain the LinearRing
     *                    from
     * @return the LinearRing obtained from the JSON array
     * @throws IllegalArgumentException if the JSON array is not valid
     */
    public static UllGeoJsonLinearRing from(JSONArray coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException(ERROR_JSON_NOT_DEFINED);
        }
        if (coordinates.length() < MINIMUM_NUMBER_OF_POSITIONS) {
            throw new IllegalArgumentException(ERROR_MINIMUM_POSITIONS);
        }
        List<UllGeoJsonPosition> positions = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            positions.add(UllGeoJsonPosition.from(coordinates.getJSONArray(i)));
        }
        if (!positions.get(0).equals(positions.get(positions.size() - 1))) {
            throw new IllegalArgumentException(ERROR_FIRST_LAST_NOT_IDENTICAL);
        }
        return new UllGeoJsonLinearRing(positions);
    }

    /**
     * Method to check if the ring is valid according to the right-hand rule.
     * 
     * @return true if the ring is valid, false otherwise
     */
    public boolean isValidRing(boolean isExterior) {
        return isExterior ? this.isCounterClockwise() : this.isClockwise();
    }

    /**
     * Method to check if the positions form a counterclockwise ring
     * 
     * @return true if the ring is counterclockwise, false otherwise
     */
    private boolean isCounterClockwise() {
        return this.calculateArea() > 0;
    }

    /**
     * It checks if the positions form a clockwise ring
     * 
     * @return true if the ring is clockwise, false otherwise
     */
    private boolean isClockwise() {
        return this.calculateArea() < 0;
    }

    /**
     * It calculates the signed area of the linear ring. The formula used helps
     * determine the orientation of the linear ring.
     * 
     * @return the signed area of the linear ring
     */
    private double calculateArea() {
        double area = 0.0;
        List<UllGeoJsonPosition> coordinates = this.getPositions();
        for (int i = 0; i < coordinates.size() - 1; i++) {
            UllGeoJsonPosition p1 = coordinates.get(i);
            UllGeoJsonPosition p2 = coordinates.get(i + 1);
            area += (p2.getLongitude() - p1.getLongitude()) * (p2.getLatitude() + p1.getLatitude());
        }
        return area / 2.0;
    }

    /**
     * It generates a random LinearRing
     * 
     * @return the random LinearRing
     */
    public static UllGeoJsonLinearRing random() {
        List<UllGeoJsonPosition> positions = new ArrayList<>();
        for (int i = 1; i <= UllInteger.random(MINIMUM_NUMBER_OF_POSITIONS - 1, 10); i++) {
            positions.add(UllGeoJsonPosition.random());
        }
        positions.add(positions.get(0));
        return new UllGeoJsonLinearRing(positions);
    }

    /**
     * Method to get the string representation of the LinearRing
     * 
     * @return the string representation of the LinearRing
     */
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}

package es.ull.utils.geojson;

import org.json.JSONObject;

import es.ull.utils.geojson.definition.UllGeoJsonType;
import es.ull.utils.geojson.input.UllGeoJsonFields;

/**
 * Class representing a GeoJSON FeatureCollection.
 * 
 */
public class UllGeoJsonFeatureCollection extends UllGeoJsonGeometry {

    /**
     * Error message for undefined features.
     */
    public static final String ERROR_FEATURES_UNDEFINED = "Features are undefined";
    /**
     * Error message for empty features.
     */
    public static final String ERROR_FEATURES_EMPTY = "Features are empty";
    /**
     * Features of the feature collection. It is a required attribute.
     */
    private UllGeoJsonFeature[] features;

    /**
     * Constructor for creating a GeoJSON FeatureCollection.
     * 
     * @param features the features of the feature collection. It cannot be null or empty.
     */
    public UllGeoJsonFeatureCollection(UllGeoJsonFeature[] features) {
        this.validateFeatures(features);
        this.features = features;
    }

    /**
     * Validates the features of the feature collection.
     * 
     * @param features the features of the feature collection. It cannot be null or empty.
     * @throws IllegalArgumentException if the features are null or empty.
     */
    private void validateFeatures(UllGeoJsonFeature[] features) {
        if (features == null) {
            throw new IllegalArgumentException(ERROR_FEATURES_UNDEFINED);
        }
        if (features.length == 0) {
            throw new IllegalArgumentException(ERROR_FEATURES_EMPTY);
        }
    }

    /**
     * Returns the features of the feature collection.
     * 
     * @return the features of the feature collection.
     */
    public UllGeoJsonFeature[] getFeatures() {
        return this.features;
    }

    /**
     * Sets the features of the feature collection.
     * 
     * @param features the features of the feature collection. It cannot be null or empty.
     */
    public void setFeatures(UllGeoJsonFeature[] features) {
        this.validateFeatures(features);
        this.features = features;
    }

    /**
     * Converts the feature collection to a JSON object.
     * 
     * @return the JSON object representing the feature collection.
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put(UllGeoJsonFields.TYPE, UllGeoJsonType.FEATURE_COLLECTION.toString());
        json.put(UllGeoJsonFields.FEATURES, new JSONObject());
        json.put(UllGeoJsonFields.PROPERTIES, new JSONObject());
        return json;
    }

    /**
     * Checks if two GeoJSON feature collections are equal.
     * 
     * @param otherObject the object to compare with.
     * @return true if the feature collections are equal, false otherwise.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof UllGeoJsonFeatureCollection)) {
            return false;
        }
        UllGeoJsonFeatureCollection otherFeatureCollection = (UllGeoJsonFeatureCollection) otherObject;
        if (this.features.length != otherFeatureCollection.features.length) {
            return false;
        }
        for (int i = 0; i < this.features.length; i++) {
            if (!this.features[i].equals(otherFeatureCollection.features[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the hash code of the feature collection.
     * 
     * @return the hash code of the feature collection.
     */
    @Override
    public int hashCode() {
        int result = 17;
        for (UllGeoJsonFeature feature : features) {
            result = 31 * result + (feature != null ? feature.hashCode() : 0);
        }
        return result;
    }

    /**
     * Returns a string representation of the feature collection.
     * 
     * @return a string representation of the feature collection.
     */
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}

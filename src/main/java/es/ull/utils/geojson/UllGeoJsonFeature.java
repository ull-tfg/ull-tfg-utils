package es.ull.utils.geojson;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;

import es.ull.utils.geojson.definition.UllGeoJsonType;
import es.ull.utils.geojson.input.UllGeoJsonFields;

/**
 * Class representing a GeoJSON Feature.
 */
public class UllGeoJsonFeature extends UllGeoJsonGeometry {

    public static final String ERROR_GEOMETRY_UNDEFINED = "Geometry is undefined";
    public static final String ERROR_PROPERTY_KEY_UNDEFINED = "Property key is undefined";
    public static final String ERROR_PROPERTY_VALUE_UNDEFINED = "Property value is undefined";
    /**
     * Geometry of the feature. It is a required attribute.
     */
    private UllGeoJsonGeometry geometry;
    /**
     * Properties of the feature. It is a required attribute.
     */
    private Map<String, Object> properties;
    /**
     * ID of the feature. It is an optional attribute.
     */
    private String id;

    /**
     * Constructor for creating a GeoJSON Feature.
     * 
     * @param geometry the geometry of the feature. It cannot be null.
     */
    public UllGeoJsonFeature(UllGeoJsonGeometry geometry) {
        this.validateGeometry(geometry);
        this.geometry = geometry;
        this.properties = new HashMap<>();
    }

    /**
     * Validates the geometry of the feature.
     * 
     * @param geometry the geometry of the feature. It cannot be null.
     * @throws IllegalArgumentException if the geometry is null.
     */
    private void validateGeometry(UllGeoJsonGeometry geometry) {
        if (geometry == null) {
            throw new IllegalArgumentException(ERROR_GEOMETRY_UNDEFINED);
        }
    }

    /**
     * Validates the key of the property.
     * 
     * @param key the key of the property. It cannot be null.
     * @throws IllegalArgumentException if the key is null.
     */
    private void validateKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException(ERROR_PROPERTY_KEY_UNDEFINED);
        }
    }

    /**
     * Validates the value of the property.
     * 
     * @param value the value of the property. It cannot be null.
     * @throws IllegalArgumentException if the value is null.
     */
    private void validateValue(Object value) {
        if (value == null) {
            throw new IllegalArgumentException(ERROR_PROPERTY_VALUE_UNDEFINED);
        }
    }

    /**
     * Sets the geometry of the feature.
     * 
     * @return the geometry of the feature.
     */
    public UllGeoJsonGeometry getGeometry() {
        return this.geometry;
    }

    public Optional<String> getId() {
        return Optional.ofNullable(this.id);
    }

    /**
     * Adds a property to the feature.
     * 
     * @param key   the key of the property. It cannot be null.
     * @param value the value of the property. It cannot be null.
     */
    public void addProperty(String key, Object value) {
        this.validateKey(key);
        this.validateValue(value);
        this.properties.put(key, value);
    }

    /**
     * Returns the value of a property with the given key.
     * 
     * @param key the key of the property. It cannot be null.
     * @return the value of the property.
     */
    public Object getProperty(String key) {
        this.validateKey(key);
        return this.properties.get(key);
    }

    /**
     * Returns the properties of the feature.
     * 
     * @return the properties of the feature.
     */
    public Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    /**
     * Checks if the feature has a property with the given key.
     * 
     * @param key the key of the property to check. It cannot be null.
     * @return true if the feature has the property, false otherwise.
     */
    public boolean hasProperty(String key) {
        return this.properties.containsKey(key);
    }

    /**
     * Removes a property from the feature.
     * 
     * @param key the key of the property to remove. It cannot be null.
     */
    public void removeProperty(String key) {
        this.properties.remove(key);
    }

    /**
     * Checks if the feature has properties.
     * 
     * @return true if the feature has properties, false otherwise.
     */
    public boolean hasProperties() {
        return !this.properties.isEmpty();
    }

    /**
     * Returns the number of properties of the feature.
     * 
     * @return the number of properties of the feature.
     */
    public int getNumberOfProperties() {
        return this.properties.size();
    }

    /**
     * Converts the feature to a JSON object.
     * 
     * @return the JSON object representing the feature.
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put(UllGeoJsonFields.TYPE, UllGeoJsonType.FEATURE.toString());
        json.put(UllGeoJsonFields.GEOMETRY, this.geometry.toJson());
        json.put(UllGeoJsonFields.PROPERTIES, new JSONObject(properties));
        if (this.hasId()) {
            json.put(UllGeoJsonFields.ID, this.id);
        }
        return json;
    }

    public boolean hasId() {
        return this.id != null && !this.id.isEmpty();
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
    }

    /**
     * Checks if two GeoJSON features are equal.
     * 
     * @param otherObject the object to compare with.
     * @return true if the features are equal, false otherwise.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof UllGeoJsonFeature)) {
            return false;
        }
        final UllGeoJsonFeature other = (UllGeoJsonFeature) otherObject;
        return this.geometry.equals(other.geometry) && this.properties.equals(other.properties);
    }

    /**
     * Returns the hash code of the feature.
     * 
     * @return the hash code of the feature.
     */
    @Override
    public int hashCode() {
        return 31 * geometry.hashCode() + properties.hashCode();
    }

    /**
     * Returns a string representation of the feature.
     */
    @Override
    public String toString() {
        return toJson().toString();
    }
}

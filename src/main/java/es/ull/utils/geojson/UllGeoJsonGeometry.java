package es.ull.utils.geojson;

import org.json.JSONObject;

/**
 * Abstract class that represents a geometry in GeoJson format.
 * 
 */
public abstract class UllGeoJsonGeometry {

    public abstract JSONObject toJson();
}

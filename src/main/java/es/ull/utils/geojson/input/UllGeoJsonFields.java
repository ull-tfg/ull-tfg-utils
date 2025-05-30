package es.ull.utils.geojson.input;

import es.ull.utils.lang.UllClass;

/**
 * Class that contains the fields of a GeoJson object.
 * 
 */
public class UllGeoJsonFields {

    public static final String COORDINATES = "coordinates";
    public static final String GEOMETRY = "geometry";
    public static final String FEATURES = "features";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String PROPERTIES = "properties";
    public static final String TYPE = "type";
    public static final String ID = "id";

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllGeoJsonFields() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }
}

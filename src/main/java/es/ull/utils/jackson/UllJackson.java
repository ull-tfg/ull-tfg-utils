package es.ull.utils.jackson;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import es.ull.utils.lang.UllClass;

public class UllJackson {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllJackson() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * Checks if the given JSON node is an array.
     * 
     * @param array the JSON node to check
     * @return true if the JSON node is an array, false otherwise
     */
    public static boolean areArrays(ArrayNode array) {
        for (final JsonNode element : array) {
            if (!element.isArray()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts a JsonNode to a JSONObject.
     * 
     * @param jsonNode the JsonNode to convert
     * @return the JSONObject representation of the JsonNode
     */
    public static JSONObject toJsonObject(JsonNode jsonNode) {
        final String jsonAsString = jsonNode.toString();
        return new JSONObject(jsonAsString);
    }

    /**
     * Converts a JsonNode to a JSONArray.
     * 
     * @param jsonNode the JsonNode to convert
     * @return the JSONArray representation of the JsonNode
     */
    public static JSONArray toJsonArray(JsonNode jsonNode) {
        final String jsonAsString = jsonNode.toString();
        return new JSONArray(jsonAsString);
    }

    /**
     * Checks if the given JSON node can be parsed to an array.
     * 
     * @param json        the JSON node to check
     * @param coordinates the coordinates to check for
     * @return true if the JSON node can be parsed to an array, false otherwise
     */
    public static boolean canBeParsedToArray(JsonNode json, String coordinates) {
        if (json == null) {
            return false;
        }
        if (json.isArray()) {
            return true;
        }
        if (json.isObject()) {
            if (json.has(coordinates)) {
                JsonNode coordinatesNode = json.get(coordinates);
                return coordinatesNode.isArray();
            }
        }
        return false;
    }
}

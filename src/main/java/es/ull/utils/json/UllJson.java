package es.ull.utils.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.ull.utils.io.UllFile;

public class UllJson {

    public static final String FILE_EXTENSION = "json";

    public static boolean canBeParsedToArray(JSONObject jsonObject, String key) {
        try {
            jsonObject.getJSONArray(key);
        } catch (JSONException exception) {
            return false;
        }
        return true;
    }

    public static boolean canAllBeParsedToArray(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonArray.getJSONArray(i);
            } catch (JSONException exception) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a JSON array can be parsed to a double.
     * 
     * @param jsonArray the JSON array to check
     * @return true if the JSON array can be parsed to a double, false otherwise
     */
    public static boolean canAllBeParsedToDouble(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonArray.getDouble(i);
            } catch (JSONException exception) {
                return false;
            }
        }
        return true;
    }

    public static Optional<JSONObject> objectFromURI(URI uri) {
        String scheme = uri.getScheme();
        Optional<JSONObject> optional = Optional.empty();
        if (scheme.equals("http") || scheme.equals("https")) {
            JSONObject json = null;
            try {
                json = UllJson.objectFromURL(uri.toURL());
            } catch (Exception exception) {
                json = null;
            }
            optional = Optional.ofNullable(json);
        }
        if (scheme.equals("file")) {
            File file = new File(uri.getPath());
            optional = UllFile.toJsonObject(file);
        }
        return optional;
    }

    public static JSONObject objectFromURL(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONObject(response.toString());
        } else {
            String errorMessage = String.format("Error accessing JSON. Response code '%d'", responseCode);
            throw new Exception(errorMessage);
        }
    }
}

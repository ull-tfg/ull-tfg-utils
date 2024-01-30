package es.ull.utils.net;

import java.net.URI;
import java.util.Optional;

import org.json.JSONObject;

import es.ull.utils.json.UllJson;

public class UllURI {

    public static Optional<JSONObject> toJsonObject(URI uri) {
        return UllJson.objectFromURI(uri);
    }
}

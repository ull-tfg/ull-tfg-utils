package es.ull.utils.rest.http;

import es.ull.utils.date.UllDate;
import es.ull.utils.rest.error.ApiError;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;

public class UllHttpServletResponse {

    public static void modifyResponseAsForbiddenError(
            HttpServletResponse response,
            String path,
            String message) throws IOException {
        ApiError body = new ApiError();
        body.setStatus(HttpStatus.FORBIDDEN);
        body.setPath(path);
        body.setTimestamp(UllDate.now());
        body.setMessage(message);
        JSONObject jsonBody = new JSONObject();
        jsonBody.put(ApiError.STATUS, body.getStatus());
        jsonBody.put(ApiError.PATH, body.getPath());
        jsonBody.put(ApiError.ERROR, body.getError());
        jsonBody.put(ApiError.MESSAGE, body.getMessage());
        jsonBody.put(ApiError.TIMESTAMP, body.getTimestamp().toString());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(jsonBody.toString());
    }
}

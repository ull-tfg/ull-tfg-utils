package es.ull.utils.rest.error;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import es.ull.utils.date.UllDate;

public class ApiError {

    public static final String STATUS = "status";
    public static final String ERROR = "error";
    public static final String PATH = "path";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";
    public static final String DETAILS = "details";
    //
    public static final String MESSAGE_ACCESS_DENIED = "Access Denied";
    public static final String MESSAGE_BAD_REQUEST = "Bad request";
    public static final String MESSAGE_DELETE_NOT_SUPPORTED =
            "Request method 'DELETE' not supported";
    public static final String MESSAGE_FORBIDDEN = "Forbidden";
    public static final String MESSAGE_GET_NOT_SUPPORTED = "Request method 'GET' not supported";
    public static final String MESSAGE_HEAD_NOT_SUPPORTED = "Request method 'HEAD' not supported";
    public static final String MESSAGE_MEDIA_TYPE_NOT_ACCEPTABLE =
            "Acceptable MIME type: " + MediaType.APPLICATION_JSON_VALUE;
    public static final String MESSAGE_METHOD_NOT_ALLOWED = "Method Not Allowed";
    public static final String MESSAGE_NOT_ACCEPTABLE = "Could not find acceptable representation";
    public static final String MESSAGE_NOT_FOUND = "Not found";
    public static final String MESSAGE_NOT_READABLE = "The resource cannot be read";
    public static final String MESSAGE_OPTIONS_NOT_SUPPORTED =
            "Request method 'OPTIONS' not supported";
    public static final String MESSAGE_PATCH_NOT_SUPPORTED = "Request method 'PATCH' not supported";
    public static final String MESSAGE_POST_NOT_SUPPORTED = "Request method 'POST' not supported";
    public static final String MESSAGE_PUT_NOT_SUPPORTED = "Request method 'PUT' not supported";
    public static final String MESSAGE_UNSUPPORTED_MEDIA_TYPE =
            "Content type must be " + MediaType.APPLICATION_JSON_VALUE;
    public static final String MESSAGE_VALIDATION_ERRORS = "Validation errors";
    public static final String MESSAGE_ENDPOINT_NOT_VALID = "Endpoint is not valid";
    // Code 405
    public static final String MESSAGE_DELETE_NOT_ALLOWED = "Method 'DELETE' not allowed";
    public static final String MESSAGE_GET_NOT_ALLOWED = "Method 'GET' not allowed";
    public static final String MESSAGE_HEAD_NOT_ALLOWED = "Method 'HEAD' not allowed";
    public static final String MESSAGE_PATCH_NOT_ALLOWED = "Method 'PATCH' not allowed";
    public static final String MESSAGE_POST_NOT_ALLOWED = "Method 'POST' not allowed";
    public static final String MESSAGE_PUT_NOT_ALLOWED = "Method 'PUT' not allowed";
    public static final String MESSAGE_OPTIONS_NOT_ALLOWED = "Method 'OPTIONS' not allowed";
    public static final String MESSAGE_TRACE_NOT_ALLOWED = "Method 'TRACE' not allowed";
    //
    private int status;
    private String error;
    private String path;
    private String message;
    private LocalDateTime timestamp;
    private List<ApiSubError> details;

    public ApiError() {
        this.status = HttpStatus.BAD_REQUEST.value();
    }

    public List<ApiSubError> getDetails() {
        return this.details;
    }

    public String getError() {
        if (this.error != null) {
            return this.error;
        } else {
            return HttpStatus.resolve(this.getStatus()).getReasonPhrase();
        }
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }

    public int getStatus() {
        return this.status;
    }

    public LocalDateTime getTimestamp() {
        if (this.timestamp != null) {
            return this.timestamp;
        } else {
            return UllDate.now();
        }
    }

    public void setDetails(List<ApiSubError> details) {
        this.details = details;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ApiError=[" + "status=" + status + ", " + "error=" + error + ", " + "path=" + path
                + ", " + "timestamp=" + timestamp + ", " + "message=" + message + ", " + "details="
                + this.details + "]";
    }
}

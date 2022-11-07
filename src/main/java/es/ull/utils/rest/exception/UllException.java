package es.ull.utils.rest.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import es.ull.utils.rest.error.ApiSubError;

public class UllException extends RuntimeException implements Serializable {

    private int status;
    private String message;
    private List<ApiSubError> subErrors;

    public UllException() {
        super();
        this.setStatus(HttpStatus.BAD_REQUEST);
    }

    public UllException addSubError(ApiSubError subError) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }
        this.subErrors.add(subError);
        return this;
    }

    public int getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public List<ApiSubError> getSubErrors() {
        return this.subErrors;
    }

    public UllException setMessage(String message) {
        this.message = message;
        return this;
    }

    public UllException setStatus(HttpStatus status) {
        this.status = status.value();
        return this;
    }

    public UllException setSubErrors(List<ApiSubError> subErrors) {
        this.subErrors = subErrors;
        return this;
    }
}

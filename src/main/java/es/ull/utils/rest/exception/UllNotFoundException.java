package es.ull.utils.rest.exception;

import org.springframework.http.HttpStatus;
import es.ull.utils.rest.error.ApiError;

public class UllNotFoundException extends UllException {

    public UllNotFoundException() {
        super();
        super.setStatus(HttpStatus.NOT_FOUND);
        super.setMessage(ApiError.MESSAGE_NOT_FOUND);
    }
}

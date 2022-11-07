package es.ull.utils.rest.exception;

import es.ull.utils.rest.error.ApiError;
import org.springframework.http.HttpStatus;

public class UllBadRequestException extends UllException {

    public UllBadRequestException() {
        super();
        super.setStatus(HttpStatus.BAD_REQUEST);
        super.setMessage(ApiError.MESSAGE_BAD_REQUEST);
    }
}

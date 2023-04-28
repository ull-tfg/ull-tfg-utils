package es.ull.utils.rest;

import java.util.List;

import es.ull.utils.rest.error.ApiError;
import es.ull.utils.rest.error.ApiSubError;
import es.ull.utils.rest.exception.UllException;

public class UllRestUtils {

    public static void throwExceptionIfErrors(List<ApiSubError> errors) {
        UllRestUtils.throwExceptionIfErrors(
                errors,
                UllException.class,
                ApiError.MESSAGE_BAD_REQUEST);
    }

    public static void throwExceptionIfErrors(
            List<ApiSubError> errors,
            String message) {
        UllRestUtils.throwExceptionIfErrors(
                errors,
                UllException.class,
                message);
    }

    public static void throwExceptionIfErrors(
            List<ApiSubError> errors,
            Class<? extends UllException> clazz) {
        UllRestUtils.throwExceptionIfErrors(
                errors,
                clazz,
                ApiError.MESSAGE_BAD_REQUEST);
    }

    public static void throwExceptionIfErrors(
            List<ApiSubError> errors,
            Class<? extends UllException> clazz,
            String message) {
        if (errors == null) {
            return;
        }
        if (!errors.isEmpty()) {
            UllException exception = null;
            try {
                exception = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                exception = new UllException();
            }
            if (message != null) {
                exception.setMessage(message);
            }
            exception.setSubErrors(errors);
            throw exception;
        }
    }
}
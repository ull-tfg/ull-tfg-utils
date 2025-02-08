package es.ull.utils.rest.exception;

import es.ull.utils.date.UllDate;
import es.ull.utils.rest.error.ApiError;
import es.ull.utils.rest.error.ApiSubError;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class UllExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UllExceptionHandler.class);

    public static ApiError createBody(
            HttpStatus status,
            String path,
            String message) {
        ApiError body = new ApiError();
        body.setStatus(status);
        body.setPath(path);
        body.setTimestamp(UllDate.now());
        body.setMessage(message);
        return body;
    }

    public static ApiError createBody(
            HttpStatus status,
            String path,
            String message,
            List<ApiSubError> details) {
        ApiError body = UllExceptionHandler.createBody(status, path, message);
        body.setDetails(details);
        return body;
    }

    public static ApiError createBody(
            UllException exception,
            WebRequest request) {
        return UllExceptionHandler.createBody(
                HttpStatus.resolve(exception.getStatus()),
                ((ServletWebRequest) request).getRequest().getRequestURI(), exception.getMessage(),
                exception.getSubErrors());
    }

    @ExceptionHandler(UllException.class)
    protected ResponseEntity<ApiError> handleUllException(
            UllException exception,
            WebRequest request) {
        logger.info("UllException");
        return ResponseEntity.badRequest()
                .body(UllExceptionHandler.createBody(exception, request));
    }

    @ExceptionHandler(UllBadRequestException.class)
    protected ResponseEntity<ApiError> handleUllBadRequestException(
            UllBadRequestException exception,
            WebRequest request) {
        logger.info("UllBadRequestException");
        return ResponseEntity.badRequest()
                .body(UllExceptionHandler.createBody(exception, request));
    }

    @ExceptionHandler(UllNotFoundException.class)
    protected ResponseEntity<ApiError> handleUllNotFoundException(
            UllNotFoundException exception,
            WebRequest request) {
        logger.info("UllNotFoundException");
        return ResponseEntity.status(exception.getStatus())
                .body(UllExceptionHandler.createBody(exception, request));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        logger.info("HttpRequestMethodNotSupportedException");
        final String[] supportedMethods = exception.getSupportedMethods();
        if (supportedMethods != null) {
            headers.add(HttpHeaders.ALLOW, StringUtils.arrayToDelimitedString(supportedMethods, ", "));
        }
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        final String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        final String message = exception.getMessage();
        return ResponseEntity.status(status)
                .body(UllExceptionHandler.createBody((HttpStatus) status, path, message));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        logger.info("HttpMediaTypeNotAcceptableException");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ApiError body = UllExceptionHandler.createBody(
                (HttpStatus) status,
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                exception.getMessage());
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        logger.info("HttpMediaTypeNotSupportedException");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ApiError body = UllExceptionHandler.createBody(
                (HttpStatus) status,
                ((ServletWebRequest) request).getRequest().getRequestURI(), ApiError.MESSAGE_UNSUPPORTED_MEDIA_TYPE);
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        logger.info("HttpMessageNotReadableException");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ApiError body = UllExceptionHandler.createBody(
                (HttpStatus) status,
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                ApiError.MESSAGE_NOT_READABLE);
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        logger.info("NoHandlerFoundException");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ApiError body = UllExceptionHandler.createBody(
                (HttpStatus) status,
                ((ServletWebRequest) request).getRequest().getRequestURI(), ApiError.MESSAGE_ENDPOINT_NOT_VALID);
        logger.info(headers.toString());
        logger.info(body.toString());
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }
}
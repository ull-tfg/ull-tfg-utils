package es.ull.utils.rest.serialization;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import es.ull.utils.rest.error.ApiError;
import es.ull.utils.rest.error.ApiSubError;
import es.ull.utils.rest.error.ApiSubErrorMessage;
import es.ull.utils.rest.exception.UllException;


public abstract class UllJsonDeserializer<T> extends JsonDeserializer<T> {

    public static final String MESSAGE_REQUIRED_ATTRIBUTE_NOT_USED = "Required attribute '%s' was validated but not used";
    public static final String MESSAGE_OPTIONAL_ATTRIBUTE_NOT_USED = "Optional attribute '%s' was validated but not used";
    private final Map<String, Object> requiredAttributes;
    private final Map<String, Object> optionalAttributes;
    private final Map<String, Boolean> countRequiredAttributes;
    private final Map<String, Boolean> countOptionalAttributes;

    public UllJsonDeserializer() {
        this.requiredAttributes = new HashMap<>();
        this.optionalAttributes = new HashMap<>();
        this.countRequiredAttributes = new HashMap<>();
        this.countOptionalAttributes = new HashMap<>();
    }

    protected void addRequiredAttribute(String key, Object value) {
        this.requiredAttributes.put(key, value);
        this.countRequiredAttributes.put(key, true);
    }

    protected void addOptionalAttribute(String key, Object value) {
        this.optionalAttributes.put(key, value);
        this.countOptionalAttributes.put(key, true);
    }

    protected Optional<List<ApiSubError>> validate(JsonNode json) {
        List<ApiSubError> errors = new ArrayList<>();
        return (errors.isEmpty()) ? Optional.empty() : Optional.of(errors);
    }

    public <T> List<ApiSubError> validateOptionalBooleanForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateOptionalBooleanForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            if (value != null) {
                                this.addOptionalAttribute(fieldName, value);
                            }
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateRequiredBooleanForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateRequiredBooleanForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            this.addRequiredAttribute(fieldName, value);
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateOptionalDoubleForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateOptionalDoubleForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            if (value != null) {
                                this.addOptionalAttribute(fieldName, value);
                            }
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateRequiredDoubleForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateRequiredDoubleForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            this.addRequiredAttribute(fieldName, value);
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateOptionalFloatForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateOptionalFloatForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            if (value != null) {
                                this.addOptionalAttribute(fieldName, value);
                            }
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateRequiredFloatForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateRequiredFloatForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            this.addRequiredAttribute(fieldName, value);
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateOptionalIntegerForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateOptionalIntegerForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            if (value != null) {
                                this.addOptionalAttribute(fieldName, value);
                            }
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateRequiredIntegerForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateRequiredIntegerForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            this.addRequiredAttribute(fieldName, value);
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateOptionalStringForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateOptionalStringForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            if (value != null) {
                                this.addOptionalAttribute(fieldName, value);
                            }
                            return List.of();
                        });
    }

    public <T> List<ApiSubError> validateRequiredStringForObject(JsonNode json, String fieldName, Class<T> clazz) {
        return UllJsonValidator.validateRequiredStringForObject(json, fieldName, clazz)
                .fold(
                        (List<ApiSubError> errors) -> errors,
                        (T value) -> {
                            this.addRequiredAttribute(fieldName, value);
                            return List.of();
                        });
    }

    public List<ApiSubError> validateOptionalURL(JsonNode json, String fieldName) {
        return UllJsonValidator.validateOptionalURL(json, fieldName)
                .fold(
                        (ApiSubError errors) -> List.of(errors),
                        (URL value) -> {
                            if (value != null) {
                                this.addOptionalAttribute(fieldName, value);
                            }
                            return List.of();
                        });
    }

    public List<ApiSubError> validateRequiredURL(JsonNode json, String fieldName) {
        return UllJsonValidator.validateRequiredURL(json, fieldName)
                .fold(
                        (ApiSubError errors) -> List.of(errors),
                        (URL value) -> {
                            this.addRequiredAttribute(fieldName, value);
                            return List.of();
                        });
    }

    public List<ApiSubError> validateOptionalURLArray(JsonNode json, String fieldName) {
        return UllJsonValidator.validateOptionalURLArray(json, fieldName)
                .fold(
                        (ApiSubError errors) -> List.of(errors),
                        (URL[] value) -> {
                            if (value != null) {
                                this.addOptionalAttribute(fieldName, value);
                            }
                            return List.of();
                        });
    }

    public List<ApiSubError> validateRequiredURLArray(JsonNode json, String fieldName) {
        return UllJsonValidator.validateRequiredURLArray(json, fieldName)
                .fold(
                        (ApiSubError errors) -> List.of(errors),
                        (URL[] value) -> {
                            this.addRequiredAttribute(fieldName, value);
                            return List.of();
                        });
    }

    protected Object getRequiredAttribute(String key) {
        this.countRequiredAttributes.put(key, false);
        return this.requiredAttributes.get(key);
    }

    protected <R> R getRequiredAttribute(String key, Class<R> clazz) {
        this.countRequiredAttributes.put(key, false);
        return clazz.cast(this.requiredAttributes.get(key));
    }

    protected Object getOptionalAttribute(String key) {
        this.countOptionalAttributes.put(key, false);
        return this.optionalAttributes.get(key);
    }

    protected <R> R getOptionalAttribute(String key, Class<R> clazz) {
        this.countOptionalAttributes.put(key, false);
        return clazz.cast(this.optionalAttributes.get(key));
    }

    protected boolean hasRequiredAttribute(String key) {
        return this.requiredAttributes.containsKey(key);
    }

    protected boolean hasOptionalAttribute(String key) {
        return this.optionalAttributes.containsKey(key);
    }

    @Override
    public final T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        this.optionalAttributes.clear();
        this.requiredAttributes.clear();
        this.countOptionalAttributes.clear();
        this.countRequiredAttributes.clear();
        final Optional<List<ApiSubError>> errors = this.validate(node);
        if (errors.isPresent()) {
            throw new UllException()
                    .setMessage(ApiError.MESSAGE_VALIDATION_ERRORS)
                    .setSubErrors(errors.get());
        }
        final T deserializedObject = this.parse(node);
        this.countRequiredAttributes.forEach((key, value) -> {
            if (value) {
                throw new UllException()
                        .setMessage(MESSAGE_REQUIRED_ATTRIBUTE_NOT_USED)
                        .setSubErrors(List.of(new ApiSubErrorMessage(key)));
            }
        });
        this.countOptionalAttributes.forEach((key, value) -> {
            if (value) {
                throw new UllException()
                        .setMessage(MESSAGE_OPTIONAL_ATTRIBUTE_NOT_USED)
                        .setSubErrors(List.of(new ApiSubErrorMessage(key)));
            }
        });
        return deserializedObject;
    }

    protected abstract T parse(JsonNode node);
}
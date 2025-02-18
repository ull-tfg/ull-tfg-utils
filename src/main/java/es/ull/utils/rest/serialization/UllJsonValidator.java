package es.ull.utils.rest.serialization;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

import es.ull.utils.lang.UllEither;
import es.ull.utils.lang.UllUUID;
import es.ull.utils.net.UllURL;
import es.ull.utils.reflection.UllReflection;
import es.ull.utils.rest.error.ApiSubError;
import es.ull.utils.rest.error.ApiSubErrorMessageRejectedValueField;

public class UllJsonValidator {

    private static final String ERROR_BOOLEAN_WRONG_FORMAT = "It must be a Boolean field";
    private static final String ERROR_DOUBLE_WRONG_FORMAT = "It must be a double field";
    private static final String ERROR_FLOAT_WRONG_FORMAT = "It must be a float field";
    private static final String ERROR_INTEGER_WRONG_FORMAT = "It must be an integer field";
    private static final String ERROR_LONG_WRONG_FORMAT = "It must be a long field";
    private static final String ERROR_TEXT_WRONG_FORMAT = "It must be a textual field";
    private static final String ERROR_UUID_WRONG_FORMAT = "It must be a valid UUID field";
    private static final String ERROR_UUID_ARRAY_WRONG_FORMAT = "It must be a valid array of UUID";
    private static final String ERROR_URL_WRONG_FORMAT = "It must be a valid URL";
    private static final String ERROR_URL_ARRAY_WRONG_FORMAT = "It must be a valid array of URL";
    private static final String ERROR_FIELD_NOT_FOUND = "Field '%s' not found";

    private static <T> UllEither<ApiSubError, T> errorFieldNotFound(String fieldName) {
        final String errorFieldNotFoundMessage = String.format(ERROR_FIELD_NOT_FOUND, fieldName);
        final ApiSubErrorMessageRejectedValueField error = ApiSubErrorMessageRejectedValueField
                .of(errorFieldNotFoundMessage, fieldName);
        return UllEither.left(error);
    }

    private static UllEither<ApiSubError, Boolean> fieldToBoolean(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isBoolean()) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_BOOLEAN_WRONG_FORMAT, field,
                    fieldName);
            return UllEither.left(error);
        }
        final boolean fieldValue = field.asBoolean();
        return UllEither.right(fieldValue);
    }

    private static UllEither<ApiSubError, Double> fieldToDouble(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isDouble()) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_DOUBLE_WRONG_FORMAT, field,
                    fieldName);
            return UllEither.left(error);
        }
        final double fieldValue = field.asDouble();
        return UllEither.right(fieldValue);
    }

    private static UllEither<ApiSubError, Integer> fieldToInteger(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isInt()) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_INTEGER_WRONG_FORMAT, field,
                    fieldName);
            return UllEither.left(error);
        }
        final int fieldValue = field.asInt();
        return UllEither.right(fieldValue);
    }

    private static UllEither<ApiSubError, Float> fieldToFloat(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isFloat()) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_FLOAT_WRONG_FORMAT, field,
                    fieldName);
            return UllEither.left(error);
        }
        final float fieldValue = field.floatValue();
        return UllEither.right(fieldValue);
    }

    private static UllEither<ApiSubError, Long> fieldToLong(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isLong()) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_LONG_WRONG_FORMAT, field,
                    fieldName);
            return UllEither.left(error);
        }
        final long fieldValue = field.asLong();
        return UllEither.right(fieldValue);
    }

    public static UllEither<ApiSubError, String> fieldToString(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isTextual()) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_TEXT_WRONG_FORMAT, field,
                    fieldName);
            return UllEither.left(error);
        }
        final String fieldValue = field.asText();
        return UllEither.right(fieldValue);
    }

    public static UllEither<ApiSubError, UUID> fieldToUUID(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isTextual() || !UllUUID.canBeParsed(field.asText())) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_UUID_WRONG_FORMAT, field,
                    fieldName);
            return UllEither.left(error);
        }
        final String textField = field.asText();
        final UUID fieldValue = UUID.fromString(textField);
        return UllEither.right(fieldValue);
    }

    public static UllEither<ApiSubError, UUID[]> fieldToUUIDArray(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isArray()) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_UUID_ARRAY_WRONG_FORMAT, fieldName);
            return UllEither.left(error);
        }
        final UUID[] uuids = new UUID[field.size()];
        for (int i = 0; i < field.size(); i++) {
            final JsonNode element = field.get(i);
            if (element.isTextual() && UllUUID.canBeParsed(element.asText())) {
                uuids[i] = UUID.fromString(element.asText());
            } else {
                final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_UUID_WRONG_FORMAT, element,
                        fieldName);
                return UllEither.left(error);
            }
        }
        return UllEither.right(uuids);
    }

    public static UllEither<ApiSubError, URL> fieldToURL(
            JsonNode json,
            String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isTextual() || !UllURL.canBeParsed(field.asText())) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_URL_WRONG_FORMAT, field, fieldName);
            return UllEither.left(error);
        }
        final URL fieldValue = UllURL.fromString(field.asText());
        return UllEither.right(fieldValue);
    }

    public static UllEither<ApiSubError, URL[]> fieldToURLArray(JsonNode json, String fieldName) {
        final JsonNode field = json.get(fieldName);
        if (!field.isArray()) {
            final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_URL_ARRAY_WRONG_FORMAT, fieldName);
            return UllEither.left(error);
        }
        final URL[] urls = new URL[field.size()];
        for (int i = 0; i < field.size(); i++) {
            final JsonNode element = field.get(i);
            if (element.isTextual() && UllURL.canBeParsed(element.asText())) {
                urls[i] = UllURL.fromString(element.asText());
            } else {
                final ApiSubError error = ApiSubErrorMessageRejectedValueField.of(ERROR_URL_WRONG_FORMAT, element,
                        fieldName);
                return UllEither.left(error);
            }
        }
        return UllEither.right(urls);
    }

    public static <T> UllEither<List<ApiSubError>, T> validateOptionalBooleanForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateOptionalBoolean(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (Boolean value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            if (value != null) {
                                try {
                                    obtainedObject = UllReflection.createObject(clazz, value);
                                } catch (Exception exception) {
                                    errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                                }
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateRequiredBooleanForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateRequiredBoolean(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (Boolean value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            try {
                                obtainedObject = UllReflection.createObject(clazz, value);
                            } catch (Exception exception) {
                                errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateOptionalDoubleForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateOptionalDouble(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (Double value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            if (value != null) {
                                try {
                                    obtainedObject = UllReflection.createObject(clazz, value);
                                } catch (Exception exception) {
                                    errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                                }
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateRequiredDoubleForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateRequiredDouble(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (Double value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            try {
                                obtainedObject = UllReflection.createObject(clazz, value);
                            } catch (Exception exception) {
                                errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateOptionalIntegerForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateOptionalInteger(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (Integer value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            if (value != null) {
                                try {
                                    obtainedObject = UllReflection.createObject(clazz, value);
                                } catch (Exception exception) {
                                    errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                                }
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateRequiredIntegerForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateRequiredInteger(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (Integer value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            try {
                                obtainedObject = UllReflection.createObject(clazz, value);
                            } catch (Exception exception) {
                                errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateOptionalFloatForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateOptionalFloat(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (Float value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            if (value != null) {
                                try {
                                    obtainedObject = UllReflection.createObject(clazz, value);
                                } catch (Exception exception) {
                                    errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                                }
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateRequiredFloatForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateRequiredFloat(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (Float value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            try {
                                obtainedObject = UllReflection.createObject(clazz, value);
                            } catch (Exception exception) {
                                errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateOptionalStringForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateOptionalString(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (String value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            if (value != null) {
                                try {
                                    obtainedObject = UllReflection.createObject(clazz, value);
                                } catch (Exception exception) {
                                    errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                                }
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateRequiredStringForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateRequiredString(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (String value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            try {
                                obtainedObject = UllReflection.createObject(clazz, value);
                            } catch (Exception exception) {
                                errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateOptionalURLForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateOptionalURL(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (URL value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            if (value != null) {
                                try {
                                    obtainedObject = UllReflection.createObject(clazz, value);
                                } catch (Exception exception) {
                                    errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                                }
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static <T> UllEither<List<ApiSubError>, T> validateRequiredURLForObject(JsonNode json, String fieldName,
            Class<T> clazz) {
        return UllJsonValidator
                .validateRequiredURL(json, fieldName)
                .fold(
                        (ApiSubError error) -> UllEither.left(List.of(error)),
                        (URL value) -> {
                            final List<ApiSubError> errors = new ArrayList<>();
                            T obtainedObject = null;
                            try {
                                obtainedObject = UllReflection.createObject(clazz, value);
                            } catch (Exception exception) {
                                errors.add(ApiSubErrorMessageRejectedValueField.of(exception, value, fieldName));
                            }
                            return (errors.isEmpty()) ? UllEither.right(obtainedObject) : UllEither.left(errors);
                        });
    }

    public static UllEither<ApiSubError, Boolean> validateOptionalBoolean(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToBoolean(json, fieldName);
    }

    public static UllEither<ApiSubError, Boolean> validateRequiredBoolean(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToBoolean(json, fieldName);
    }

    public static UllEither<ApiSubError, Double> validateOptionalDouble(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToDouble(json, fieldName);
    }

    public static UllEither<ApiSubError, Double> validateRequiredDouble(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToDouble(json, fieldName);
    }

    public static UllEither<ApiSubError, Integer> validateOptionalInteger(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToInteger(json, fieldName);
    }

    public static UllEither<ApiSubError, Integer> validateRequiredInteger(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToInteger(json, fieldName);
    }

    public static UllEither<ApiSubError, Float> validateOptionalFloat(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToFloat(json, fieldName);
    }

    public static UllEither<ApiSubError, Float> validateRequiredFloat(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToFloat(json, fieldName);
    }

    public static UllEither<ApiSubError, String> validateOptionalString(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToString(json, fieldName);
    }

    public static UllEither<ApiSubError, String> validateRequiredString(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToString(json, fieldName);
    }

    public static UllEither<ApiSubError, UUID> validateOptionalUUID(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToUUID(json, fieldName);
    }

    public static UllEither<ApiSubError, UUID> validateRequiredUUID(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToUUID(json, fieldName);
    }

    public static UllEither<ApiSubError, UUID[]> validateOptionalUUIDArray(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToUUIDArray(json, fieldName);
    }

    public static UllEither<ApiSubError, UUID[]> validateRequiredUUIDArray(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToUUIDArray(json, fieldName);
    }

    public static UllEither<ApiSubError, URL> validateOptionalURL(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToURL(json, fieldName);
    }

    public static UllEither<ApiSubError, URL> validateRequiredURL(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToURL(json, fieldName);
    }

    public static UllEither<ApiSubError, URL[]> validateOptionalURLArray(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return UllEither.empty();
        }
        return fieldToURLArray(json, fieldName);
    }

    public static UllEither<ApiSubError, URL[]> validateRequiredURLArray(
            JsonNode json,
            String fieldName) {
        if (!json.has(fieldName)) {
            return errorFieldNotFound(fieldName);
        }
        return fieldToURLArray(json, fieldName);
    }
}
package es.ull.utils.rest.serialization;

import java.util.List;
import java.util.UUID;

import es.ull.utils.lang.UllEither;
import es.ull.utils.lang.UllUUID;
import es.ull.utils.reflection.UllReflection;
import es.ull.utils.rest.error.ApiSubError;
import es.ull.utils.rest.error.ApiSubErrorMessage;

public class UllStringValidator {

    public static final String ERROR_UUID_NOT_DEFINED = "UUID cannot be obtained because String is not defined";
    public static final String ERROR_UUID_EMPTY = "UUID cannot be obtained because String is empty";
    public static final String ERROR_UUID_WRONG_FORMAT = "UUID cannot be obtained because String is not valid '%s'";

    public static <T> UllEither<ApiSubError, T> validateRequiredStringForObject(String value, Class<T> clazz) {
        if (value == null) {
            return UllEither.left(new ApiSubErrorMessage("It must be a valid string"));
        }
        if (value.isEmpty()) {
            return UllEither.left(new ApiSubErrorMessage("It must be a valid string"));
        }
        T object = null;
        try {
            object = UllReflection.createObject(clazz, value);
        } catch (Exception e) {
            return UllEither.left(new ApiSubErrorMessage("It must be a valid string"));
        }
        return UllEither.right(object);
    }

    public static UllEither<ApiSubError, UUID> validateUUID(String id) {
        if (id == null) {
            return UllEither.left(new ApiSubErrorMessage(ERROR_UUID_NOT_DEFINED));
        }
        if (id.isEmpty()) {
            return UllEither.left(new ApiSubErrorMessage(ERROR_UUID_EMPTY));
        }
        if (!UllUUID.canBeParsed(id)) {
            final String message = String.format(ERROR_UUID_WRONG_FORMAT, id);
            return UllEither.left(new ApiSubErrorMessage(message));
        }
        return UllEither.right(UUID.fromString(id));
    }

    public static UllEither<ApiSubError, UUID[]> validateUUID(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return UllEither.left(new ApiSubErrorMessage(ERROR_UUID_WRONG_FORMAT));
        }
        final UUID[] uuids = new UUID[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            final String id = ids.get(i);
            if (!UllUUID.canBeParsed(id)) {
                final String message = String.format(ERROR_UUID_WRONG_FORMAT, id);
                return UllEither.left(new ApiSubErrorMessage(message));
            }
            uuids[i] = UUID.fromString(ids.get(i));
        }
        return UllEither.right(uuids);
    }
}
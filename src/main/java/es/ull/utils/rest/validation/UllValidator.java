package es.ull.utils.rest.validation;

import es.ull.utils.rest.error.ApiSubError;
import es.ull.utils.string.UllString;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public interface UllValidator<T> {

    public static final String PARSE_ERROR = "It is not possible to parse the data.";

    public static String getNotProvidedEntity(String entity) {
        return "The " + entity + " is not provided.";
    }

    public static boolean isAlphanumeric(String string) {
        return UllString.isAlphanumeric(string);
    }

    public static boolean isAlphanumericAndSpaces(String string) {
        return UllString.isAlphanumericAndSpaces(string);
    }

    public static boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public abstract List<ApiSubError> validate(T target);
}

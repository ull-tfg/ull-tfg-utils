package es.ull.utils.lang;

import java.util.UUID;

public class UllUUID {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllUUID() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * Checks if the given string can be parsed as a valid UUID.
     * <p>
     * This method attempts to parse the provided string into a {@link UUID}. If the string is not a valid UUID format, it returns {@code false}.
     * 
     * @param uuid the string representation of a UUID to validate.
     * @return {@code true} if the string can be parsed as a UUID, {@code false} otherwise.
     * @throws NullPointerException if the input string is {@code null}.
     */
    public static boolean canBeParsed(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

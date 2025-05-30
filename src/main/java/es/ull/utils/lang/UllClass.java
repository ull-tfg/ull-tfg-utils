package es.ull.utils.lang;

public class UllClass {

    public static final String ERROR_UTILITY_CLASS = "This is a utility class and cannot be instantiated";

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllClass() {
        throw new UnsupportedOperationException(ERROR_UTILITY_CLASS);
    }
}

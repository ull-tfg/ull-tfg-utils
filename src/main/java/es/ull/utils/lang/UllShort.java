package es.ull.utils.lang;

public class UllShort {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllShort() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * @param stringToParse
     * @return
     */
    public static boolean canBeParsed(String stringToParse) {
        if (stringToParse == null) {
            return false;
        }
        try {
            Short.parseShort(stringToParse);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * @param value
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static short limitToRange(short value, short lowerBound, short upperBound) {
        if (value < lowerBound) {
            return lowerBound;
        } else if (value > upperBound) {
            return upperBound;
        } else {
            return value;
        }
    }

    /**
     * Returns a random short value.
     * 
     * @return a random short value.
     */
    public static short random() {
        return (short) (Math.random() * Short.MAX_VALUE);
    }

    /**
     * Returns a random short value with a given probability.
     * 
     * @param probability the probability of the short value.
     * @return a random short value with a given probability.
     */
    public static short random(double probability) {
        return (short) (Math.random() * Short.MAX_VALUE * probability);
    }

    /**
     * Converts an array of wrapper shorts to an array of primitive shorts.
     * 
     * @param array Array of wrapper shorts.
     * @return Array of primitive shorts.
     */
    public static short[] toPrimitive(Short[] array) {
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of primitive shorts to an array of wrapper shorts.
     * 
     * @param array Array of primitive shorts.
     * @return Array of wrapper shorts.
     */
    public static Short[] toWrapper(short[] array) {
        Short[] wrapperArray = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = array[i];
        }
        return wrapperArray;
    }
}

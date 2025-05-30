package es.ull.utils.lang;

public class UllLong {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllLong() {
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
            Long.parseLong(stringToParse);
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
    public static long limitToRange(long value, long lowerBound, long upperBound) {
        if (value < lowerBound) {
            return lowerBound;
        } else if (value > upperBound) {
            return upperBound;
        } else {
            return value;
        }
    }

    /**
     * Returns a random long value.
     * 
     * @return a random long value.
     */
    public static long random() {
        return (long) (Math.random() * Long.MAX_VALUE);
    }

    /**
     * Returns a random long value with a given probability.
     * 
     * @param probability the probability of the long value.
     * @return a random long value with a given probability.
     */
    public static long random(double probability) {
        return (long) (Math.random() * Long.MAX_VALUE * probability);
    }

    /**
     * Converts an array of wrapper longs to an array of primitive longs.
     * 
     * @param array Array of wrapper longs.
     * @return Array of primitive longs.
     */
    public static long[] toPrimitive(Long[] array) {
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of primitive longs to an array of wrapper longs.
     * 
     * @param array Array of primitive longs.
     * @return Array of wrapper longs.
     */
    public static Long[] toWrapper(long[] array) {
        Long[] wrapperArray = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = array[i];
        }
        return wrapperArray;
    }

}

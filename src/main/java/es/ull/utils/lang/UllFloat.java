package es.ull.utils.lang;

public class UllFloat {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllFloat() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * Checks if a string can be parsed as a float.
     * 
     * @param stringToParse the string to parse.
     * @return true if the string can be parsed as a float, false otherwise.
     */
    public static boolean canBeParsed(String stringToParse) {
        if (stringToParse == null) {
            return false;
        }
        try {
            Float.parseFloat(stringToParse);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * It limits a float value to a given range.
     * 
     * @param value      the float value to limit.
     * @param lowerBound the lower bound of the range. This value is included in the range.
     * @param upperBound the upper bound of the range. This value is included from the range.
     * @return the limited float value.
     */
    public static float limitToRange(float value, float lowerBound, float upperBound) {
        if (value < lowerBound) {
            return lowerBound;
        } else if (value > upperBound) {
            return upperBound;
        } else {
            return value;
        }
    }

    /**
     * Returns a random float value.
     * 
     * @return a random float value.
     */
    public static float random() {
        return (float) (Math.random() * Float.MAX_VALUE);
    }

    /**
     * Returns a random float value with a given probability.
     * 
     * @param probability the probability of the float value.
     * @return a random float value with a given probability.
     */
    public static float random(double probability) {
        return (float) (Math.random() * Float.MAX_VALUE * probability);
    }

    /**
     * Converts an array of wrapper floats to an array of primitive floats.
     * 
     * @param array Array of wrapper floats.
     * @return Array of primitive floats.
     */
    public static float[] toPrimitive(Float[] array) {
        float[] floatArray = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            floatArray[i] = array[i];
        }
        return floatArray;
    }

    /**
     * Converts an array of primitive floats to an array of wrapper floats.
     * 
     * @param array Array of primitive floats.
     * @return Array of wrapper floats.
     */
    public static Float[] toWrapper(float[] array) {
        final Float[] wrapperArray = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = array[i];
        }
        return wrapperArray;
    }
}

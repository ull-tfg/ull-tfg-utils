package es.ull.utils.lang;

import java.util.Arrays;
import java.util.Random;

public class UllDouble {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllDouble() {
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
            Double.parseDouble(stringToParse);
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
    public static double limitToRange(double value, double lowerBound, double upperBound) {
        if (value < lowerBound) {
            return lowerBound;
        } else if (value > upperBound) {
            return upperBound;
        } else {
            return value;
        }
    }

    /**
     * Generates a random double value in the range [rangeMin, rangeMax).
     *
     * @param rangeMin the minimum value of the range (included).
     * @param rangeMax the maximum value of the range (excluded).
     * @return a random double value in the range [rangeMin, rangeMax).
     */
    public static double random(double rangeMin, double rangeMax) {
        return rangeMin + (rangeMax - rangeMin) * new Random().nextDouble();
    }

    /**
     * Converts an array of wrapper doubles to an array of primitive doubles.
     * 
     * @param array Array of wrapper doubles.
     * @return Array of primitive doubles.
     */
    public static double[] toPrimitive(Double[] array) {
        return Arrays.stream(array)
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    /**
     * Converts an array of primitive doubles to an array of wrapper doubles.
     * 
     * @param array Array of primitive doubles.
     * @return Array of wrapper doubles.
     */
    public static Double[] toWrapper(double[] array) {
        Double[] wrapperArray = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = array[i];
        }
        return wrapperArray;
    }
}

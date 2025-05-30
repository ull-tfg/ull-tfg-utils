package es.ull.utils.lang;

import java.util.Arrays;
import java.util.Random;

import es.ull.utils.random.UllRandom;

public class UllInteger {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllInteger() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * Returns the absolute value of an integer.
     * 
     * @param number the integer
     * @return the absolute value of the number
     */
    public static int abs(int number) {
        return number < 0 ? -number : number;
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
            Integer.parseInt(stringToParse);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * It returns the number of digits of a given number.
     * 
     * @param number The number to count the digits.
     * @return The number of digits of the given number.
     */
    public int lengthOf(int number) {
        return String.valueOf(number).length();
    }

    /**
     * It limits the value to the specified range.
     * 
     * @param value      The value to limit.
     * @param lowerBound The lower bound of the range.
     * @param upperBound The upper bound of the range.
     * @return The value limited to the specified range.
     */
    public static int limitToRange(int value, int lowerBound, int upperBound) {
        if (value < lowerBound) {
            return lowerBound;
        } else if (value > upperBound) {
            return upperBound;
        } else {
            return value;
        }
    }

    /**
     * Checks if a given integer is even.
     * 
     * @param number the integer to check
     * @return true if the number is even, false otherwise
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Checks if an integer is within a specified range (inclusive).
     * 
     * @param number the integer to check
     * @param min    the minimum value (inclusive)
     * @param max    the maximum value (inclusive)
     * @return true if the number is in the range, false otherwise
     */
    public static boolean isInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    /**
     * Checks if a given integer is odd.
     * 
     * @param number the integer to check
     * @return true if the number is odd, false otherwise
     */
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    /**
     * Generates a random integer.
     * 
     * @return A randomly generated integer.
     */
    public static int random() {
        return new Random().nextInt();
    }

    public static int random(int rangeMax) {
        return UllRandom.random(0, rangeMax);
    }

    /**
     * Generates a random integer within the specified range [rangeMin, rangeMax).
     *
     * @param rangeMin The inclusive lower bound of the range.
     * @param rangeMax The exclusive upper bound of the range.
     * @return A random integer within the specified range.
     * @throws IllegalArgumentException If {@code rangeMin} is greater than or equal to {@code rangeMax}.
     */
    public static int random(int rangeMin, int rangeMax) {
        if (rangeMin >= rangeMax) {
            throw new IllegalArgumentException("Invalid range: rangeMin must be less than rangeMax");
        }
        return new Random().nextInt((rangeMax - rangeMin)) + rangeMin;
    }

    /**
     * Converts an array of wrapper integers to an array of primitive integers.
     * 
     * @param array Array of wrapper integers.
     * @return Array of primitive integers.
     */
    public static int[] toPrimitive(Integer[] array) {
        return Arrays.stream(array)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    /**
     * Converts an Integer object to an int, returning a default value if null.
     * 
     * @param number       the Integer object
     * @param defaultValue the default value if number is null
     * @return the int value or the default value
     */
    public static int toPrimitive(Integer number, int defaultValue) {
        return number != null ? number : defaultValue;
    }

    /**
     * Converts an array of primitive integers to an array of wrapper integers.
     * 
     * @param array Array of primitive integers.
     * @return Array of wrapper integers.
     */
    public static Integer[] toWrapper(int[] array) {
        Integer[] wrapperArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = array[i];
        }
        return wrapperArray;
    }
}

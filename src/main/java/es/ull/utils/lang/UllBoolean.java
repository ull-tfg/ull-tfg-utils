package es.ull.utils.lang;

import java.util.Random;

public class UllBoolean {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllBoolean() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * Returns true if all values in the array are false.
     * 
     * @param array Array of primitive Boolean.
     * @return true if all values in the array are false.
     */
    public static boolean allFalse(boolean[] array) {
        for (boolean value : array) {
            if (value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if all values in the array are true.
     * 
     * @param array Array of primitive Boolean.
     * @return true if all values in the array are true.
     */
    public static boolean allTrue(boolean[] array) {
        for (boolean value : array) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Counts the number of false values in the array.
     * 
     * @param array Array of primitive Boolean.
     * @return the count of false values in the array.
     */
    public static int countFalse(boolean[] array) {
        int count = 0;
        for (boolean value : array) {
            if (!value) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of true values in the array.
     * 
     * @param array Array of primitive Boolean.
     * @return the count of true values in the array.
     */
    public static int countTrue(boolean[] array) {
        int count = 0;
        for (boolean value : array) {
            if (value) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns true if the array contains at least one false value.
     * 
     * @param array Array of primitive Boolean.
     * @return true if the array contains at least one false value.
     */
    public static boolean hasFalse(boolean[] array) {
        for (boolean value : array) {
            if (!value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the array contains at least one true value.
     * 
     * @param array Array of primitive Boolean.
     * @return true if the array contains at least one true value.
     */
    public static boolean hasTrue(boolean[] array) {
        for (boolean value : array) {
            if (value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a new array with all values negated.
     * 
     * @param array Array of primitive Boolean.
     * @return a new array with all values negated.
     */
    public static boolean[] negate(boolean[] array) {
        boolean[] negatedArray = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            negatedArray[i] = !array[i];
        }
        return negatedArray;
    }

    /**
     * Returns a random boolean value.
     * 
     * @return a random boolean value.
     */
    public static boolean random() {
        return new Random().nextBoolean();
    }

    /**
     * Returns a random boolean value with a given probability.
     * 
     * @param probability the probability of the boolean value.
     * @return a random boolean value with a given probability.
     */
    public static boolean random(double probability) {
        final Random random = new Random();
        return (random.nextDouble() < probability);
    }

    /**
     * Converts an array of primitive Boolean to an array of wrapper Boolean.
     * 
     * @param array Array of primitive Boolean.
     * @return Array of wrapper Boolean.
     */
    public static Boolean[] toWrapper(boolean[] array) {
        final Boolean[] wrapperArray = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = array[i];
        }
        return wrapperArray;
    }

    /**
     * Converts an array of wrapper Boolean to an array of primitive Boolean.
     * 
     * @param array Array of wrappers of Boolean.
     * @return Array of primitive Boolean.
     */
    public static boolean[] toPrimitive(Boolean[] array) {
        final boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
}

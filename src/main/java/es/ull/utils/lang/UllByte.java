package es.ull.utils.lang;

public class UllByte {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllByte() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * @param value
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static byte limitToRange(byte value, byte lowerBound, byte upperBound) {
        if (value < lowerBound) {
            return lowerBound;
        } else if (value > upperBound) {
            return upperBound;
        } else {
            return value;
        }
    }

    /**
     * Returns a random byte value.
     * 
     * @return a random byte value.
     */
    public static byte random() {
        return (byte) (Math.random() * Byte.MAX_VALUE);
    }

    /**
     * Returns a random byte value with a given probability.
     * 
     * @param probability the probability of the byte value.
     * @return a random byte value with a given probability.
     */
    public static byte random(double probability) {
        return (byte) (Math.random() * Byte.MAX_VALUE * probability);
    }

    /**
     * Converts an array of wrapper bytes to an array of primitive bytes.
     * 
     * @param array Array of wrapper bytes.
     * @return Array of primitive bytes.
     */
    public static byte[] toPrimitive(Byte[] array) {
        final byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Converts an array of primitive bytes to an array of wrapper bytes.
     * 
     * @param array Array of primitive bytes.
     * @return Array of wrapper bytes.
     */
    public static Byte[] toWrapper(byte[] array) {
        final Byte[] wrapperArray = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = array[i];
        }
        return wrapperArray;
    }
}
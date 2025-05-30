package es.ull.utils.lang;

import java.util.Random;

public class UllChar {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private UllChar() {
        throw new UnsupportedOperationException(UllClass.ERROR_UTILITY_CLASS);
    }

    /**
     * Checks if the character is a digit.
     * 
     * @param characterToCheck the character to check.
     * @return {@code true} if the character is a digit; {@code false} otherwise.
     */
    public static boolean isDigit(char characterToCheck) {
        return Character.isDigit(characterToCheck);
    }

    /**
     * Checks if the character is a letter.
     * 
     * @param characterToCheck the character to check.
     * @return {@code true} if the character is a letter; {@code false} otherwise.
     */
    public static boolean isLetter(char characterToCheck) {
        return Character.isLetter(characterToCheck);
    }

    /**
     * Generates a random alphabetic character.
     * 
     * @return a random alphabetic character.
     */
    public static char randomAlphabetic() {
        final int randomInt = new Random().nextInt(26);
        final char referenceLetter = (UllBoolean.random()) ? 'A' : 'a';
        final char randomChar = (char) (randomInt + referenceLetter);
        return randomChar;
    }

    /**
     * Converts an array of wrapper characters to an array of primitive chars.
     * 
     * @param array Array of wrapper characters.
     * @return Array of primitive characters.
     */
    public static char[] toPrimitive(Character[] array) {
        final char[] result = new char[array.length];
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
    public static Character[] toWrapper(char[] array) {
        final Character[] wrapperArray = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = array[i];
        }
        return wrapperArray;
    }
}

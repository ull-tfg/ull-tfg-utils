package es.ull.utils.string;

import org.apache.commons.lang3.RandomStringUtils;

public class UllString {

    public static String random(int length) {
        return RandomStringUtils.randomAscii(length);
    }

    public static String randomAscii(int length) {
        return RandomStringUtils.randomAscii(length);
    }

    public static String randomAlphabetic(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String randomAlphanumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String simplify(String string) {
        return string
                .replaceAll("\t", " ")
                .replaceAll(" +", " ")
                .trim();
    }
}

package es.ull.utils.string;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String random(int length, Pattern pattern) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        while (sb.length() < length) {
            Matcher matcher = pattern.matcher(Character.toString((char) random.nextInt(128)));
            if (matcher.matches()) {
                sb.append(matcher.group());
            }
        }
        return sb.toString();
    }

    public static String random(int length, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return UllString.random(length, pattern);
    }
}

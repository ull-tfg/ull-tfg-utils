package es.ull.utils.net;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UllURL {

    /**
     * Checks if a string can be parsed as a URL.
     * 
     * @param urlString the string to be checked.
     * @return true if the string can be parsed, false otherwise.
     */
    public static boolean canBeParsed(String urlString) {
        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Converts a string into a URL object.
     * 
     * @param urlString the string to be converted.
     * @return a URL object if the string is valid, null otherwise.
     */
    public static URL fromString(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException exception) {
            System.err.println("Invalid URL: " + urlString);
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a URL object into an InputStream object.
     * 
     * @param url the URL object to be converted.
     * @return an InputStream object.
     */
    public static InputStream toInputStream(URL url) {
        try {
            return url.openStream();
        } catch (IOException e) {
            System.err.println("Error while opening the URL stream: " + url);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a URL object into a File object.
     * 
     * @param url the URL object to be converted.
     * @return a File object.
     */
    public static File toFile(URL url) {
        return new File(url.getFile());
    }
}

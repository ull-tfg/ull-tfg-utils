package es.ull.utils.io;

import es.ull.utils.json.UllJson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.json.JSONObject;

public class UllFile {

    public static String getFileExtension(String fileName) {
        String extension = "";
        if (fileName.contains(".")) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        }
        return extension;
    }

    public static String getFileExtension(File file) {
        return UllFile.getFileExtension(file.getName());
    }

    public static boolean hasExtension(File file, String extension) {
        return UllFile
                .getFileExtension(file)
                .equals(extension.toLowerCase());
    }

    public static boolean isJsonFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        return UllFile.hasExtension(file, UllJson.FILE_EXTENSION);
    }

    public static Optional<JSONObject> toJsonObject(File file) {
        if (!UllFile.isJsonFile(file)) {
            return Optional.empty();
        }
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException exception) {
            return Optional.empty();
        }
        JSONObject json = new JSONObject(content.toString());
        return Optional.of(json);
    }
}

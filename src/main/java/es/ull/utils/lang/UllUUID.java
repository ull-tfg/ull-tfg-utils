package es.ull.utils.lang;

import java.util.UUID;

public class UllUUID {

    public static boolean canBeParsed(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

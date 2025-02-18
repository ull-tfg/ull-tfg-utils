package es.ull.utils.reflection;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.UUID;

public class UllReflection {

    public static <T> T createObject(Class<T> clazz, byte value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(Byte.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, boolean value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(Boolean.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, char value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(Character.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, double value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(Double.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, float value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(Float.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, int value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(Integer.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, long value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(Long.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, short value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(Short.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, String value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(String.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, UUID value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(UUID.class);
        return constructor.newInstance(value);
    }

    public static <T> T createObject(Class<T> clazz, URL value) throws Exception {
        final Constructor<T> constructor = clazz.getConstructor(URL.class);
        return constructor.newInstance(value);
    }
}
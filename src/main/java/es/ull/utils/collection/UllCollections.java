package es.ull.utils.collection;

import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.collections4.Equator;

/**
 * This class provides a set of methods to work with collections.
 * 
 */
public class UllCollections {

    /**
     * Converts an Iterator to a Stream.
     * 
     * @param <T>      the type of elements in the Iterator
     * @param iterator the Iterator to convert
     * @return a Stream containing the elements of the Iterator
     */
    public static <T> Stream<T> asStream(Iterator<T> iterator) {
        return UllCollections.asStream(iterator, false);
    }

    /**
     * Converts an Iterator to a Stream with an option for parallel processing.
     * 
     * @param <T>      the type of elements in the Iterator
     * @param iterator the Iterator to convert
     * @param parallel if true, the Stream will be parallel; if false, it will be sequential
     * @return a Stream containing the elements of the Iterator
     */
    public static <T> Stream<T> asStream(Iterator<T> iterator, boolean parallel) {
        Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

    /**
     * Checks if a collection contains an element using a custom equator.
     * 
     * @param <T>        the type of elements in the collection
     * @param collection the collection to check
     * @param element    the element to check for
     * @param equator    the equator to use for comparison
     * @return true if the collection contains the element, false otherwise
     */
    public static <T> boolean contains(final Collection<T> collection, T element, Equator<T> equator) {
        for (final T e : collection) {
            if (equator.equate(e, element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Filters a map based on a predicate applied to its keys.
     * 
     * @param map       the map to filter
     * @param predicate the predicate to apply to the keys
     * @return a new map containing only the entries where the key satisfies the predicate
     */
    public static Map<Integer, Object> filter(Map<Integer, Object> map) {
        return map.entrySet()
                .stream()
                .filter((entry) -> entry.getKey() == 2)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Inverts a map, swapping keys and values.
     * 
     * @param map the map to invert
     * @return a new map with keys and values swapped
     */
    public static Map<String, Integer> invert(Map<Integer, String> map) {
        Map<String, Integer> newMap = new HashMap<>();
        map.forEach((Integer integer, String string) -> {
            newMap.put(string, integer);
        });
        return newMap;
    }

    /**
     * Converts a map's keys to a list.
     * 
     * @param <K> Type of keys in the map
     * @param <V> Type of values in the map
     * @param map the map whose keys are to be converted
     * @return a list containing the keys of the map
     */
    public static <K, V> List<K> keysToList(Map<K, V> map) {
        return new ArrayList<>(map.keySet());
    }

    /**
     * Returns a random element from a collection.
     * 
     * @param <T>        Type of elements in the collection
     * @param collection the collection from which to select a random element
     * @return a random element from the collection
     */
    public static <T> T random(Collection<T> collection) {
        int num = new Random().nextInt(collection.size());
        for (T t : collection) {
            if (--num < 0) {
                return t;
            }
        }
        throw new AssertionError();
    }

    /**
     * Returns a random element from an enum class.
     * 
     * @param <T>       Type of the enum
     * @param clazzEnum the enum class from which to select a random element
     * @return a random element from the enum class
     */
    public static <T extends Enum<?>> T randomElementFromEnum(Class<T> clazzEnum) {
        final int value = new Random().nextInt(clazzEnum.getEnumConstants().length);
        return clazzEnum.getEnumConstants()[value];
    }

    /**
     * Returns a random sublist from a list.
     * 
     * @param <T>  Type of elements in the list
     * @param list the list from which to select a random sublist
     * @return a random sublist containing a random number of elements from the original list
     */
    public static <T> List<T> randomSublist(List<T> list) {
        if (list.size() > 0) {
            int numberOfElementsToSelect = new Random().nextInt(list.size());
            return list.stream()
                    .collect(Collectors.collectingAndThen(
                            Collectors.toList(),
                            collected -> {
                                Collections.shuffle(collected);
                                return collected.stream();
                            }))
                    .limit(numberOfElementsToSelect)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Returns a random sublist from a list with a specified number of elements.
     * 
     * @param <T>                      Type of elements in the list
     * @param list                     the list from which to select a random sublist
     * @param numberOfElementsToSelect the number of elements to select from the list
     * @return a random sublist containing the specified number of elements from the original list
     */
    public static <T> List<T> randomSublist(List<T> list, int numberOfElementsToSelect) {
        return list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        collected -> {
                            Collections.shuffle(collected);
                            return collected.stream();
                        }))
                .limit(numberOfElementsToSelect)
                .collect(Collectors.toList());
    }

    /**
     * Returns a random subset of an enum class.
     * 
     * @param <T>       Type of the enum
     * @param clazzEnum the enum class from which to select a random subset
     * @return a random subset of elements from the enum class, with a random size
     */
    public static <T extends Enum<?>> Set<T> randomSubset(Class<T> clazzEnum) {
        int size = clazzEnum.getEnumConstants().length;
        int numberOfElementsToSelect = new Random().nextInt(size);
        return UllCollections.randomSubset(clazzEnum, numberOfElementsToSelect);
    }

    /**
     * Returns a random subset of an enum class with a specified number of elements.
     * 
     * @param <T>                      Type of the enum
     * @param clazzEnum                the enum class from which to select a random subset
     * @param numberOfElementsToSelect the number of elements to select from the enum class
     * @return a random subset of elements from the enum class, with the specified size
     */
    public static <T extends Enum<?>> Set<T> randomSubset(Class<T> clazzEnum, int numberOfElementsToSelect) {
        Set<T> subset = new HashSet<>();
        int size = clazzEnum.getEnumConstants().length;
        if (size < numberOfElementsToSelect) {
            numberOfElementsToSelect = size;
        }
        while (subset.size() < numberOfElementsToSelect) {
            T element = randomElementFromEnum(clazzEnum);
            subset.add(element);
        }
        return subset;
    }

    /**
     * Removes duplicates from a list based on a key mapper function.
     * 
     * @param <T>       Type of elements in the list
     * @param <K>       Type of keys produced by the key mapper function
     * @param list      the list from which to remove duplicates
     * @param keyMapper a function that maps each element to a key used for comparison
     * @return a new list containing only unique elements based on the keys produced by the key mapper function
     */
    public static <T, K> List<T> removeDuplicates(List<T> list, Function<? super T, ? extends K> keyMapper) {
        return UllCollections.toList(UllCollections.toMap(list, keyMapper));
    }

    /**
     * Creates a collector that collects elements into a stream with shuffled order.
     * 
     * @param <T> the type of elements in the stream
     * @return a collector that collects elements into a stream with shuffled order
     */
    public static <T> Collector<T, ?, Stream<T>> shuffledStream() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                collected -> {
                    Collections.shuffle(collected);
                    return collected.stream();
                });
    }

    /**
     * Converts an Iterable to a Collection.
     * 
     * @param <E>      Type of elements in the Iterable
     * @param iterator the Iterable to convert
     * @return a Collection containing the elements of the Iterable
     */
    public static <E> Collection<E> toCollection(Iterable<E> iterator) {
        return UllCollections.toList(iterator);
    }

    /**
     * Converts a Collection to an array of the specified type.
     * 
     * @param <T>        Type of elements in the collection
     * @param type       the class of the type
     * @param <T>        Type of elements in the collection
     * @param type       the class of the type
     * @param collection the collection to convert
     * @return an array of the specified type containing the elements of the collection
     */
    public static <T> T[] toArray(Class<T> type, Collection<T> collection) {
        final T[] array = (T[]) Array.newInstance(type, collection.size());
        int i = 0;
        for (T d : collection) {
            array[i] = d;
            i++;
        }
        return array;
    }

    /**
     * Converts a variable number of elements to a list.
     * 
     * @param <T>      Type of elements
     * @param elements the elements to convert to a list
     * @return a list containing the provided elements
     */
    public static <T> List<T> toList(T... elements) {
        return Arrays.asList(elements);
    }

    /**
     * Converts a collection to a list.
     * 
     * @param <T>        Type of elements in the collection
     * @param collection the collection to convert
     * @return a list containing the elements of the collection
     */
    public static <T> List<T> toList(Collection<T> collection) {
        return new ArrayList<>(collection);
    }

    /**
     * Converts a map to a list of its values.
     * 
     * @param <K> Type of keys in the map
     * @param <V> Type of values in the map
     * @param map the map to convert
     * @return a list containing the values of the map
     */
    public static <K, V> List<V> toList(Map<K, V> map) {
        return map.values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Converts an Iterable to a List.
     * 
     * @param <E>      Type of elements in the Iterable
     * @param iterator the Iterable to convert
     * @return a List containing the elements of the Iterable
     */
    public static <E> List<E> toList(Iterable<E> iterator) {
        final List<E> list = new ArrayList<>();
        for (E item : iterator) {
            list.add(item);
        }
        return list;
    }

    /**
     * Converts an array of characters to a List of Characters.
     * 
     * @param array the array of characters to convert
     * @return a List of Characters containing the elements of the array
     */
    public static List<Character> toList(char[] array) {
        final List<Character> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Converts an array of bytes to a List of Bytes.
     * 
     * @param array the array of bytes to convert
     * @return a List of Bytes containing the elements of the array
     */
    public static List<Byte> toList(byte[] array) {
        final List<Byte> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Converts an array of shorts to a List of Shorts.
     * 
     * @param array the array of shorts to convert
     * @return a List of Shorts containing the elements of the array
     */
    public static List<Short> toList(short[] array) {
        final List<Short> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Converts an array of integers to a List of Integers.
     * 
     * @param array the array of integers to convert
     * @return a List of Integers containing the elements of the array
     */
    public static List<Integer> toList(int[] array) {
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Converts an array of longs to a List of Longs.
     * 
     * @param array the array of longs to convert
     * @return a List of Longs containing the elements of the array
     */
    public static List<Long> toList(long[] array) {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Converts an array of floats to a List of Floats.
     * 
     * @param array the array of floats to convert
     * @return a List of Floats containing the elements of the array
     */
    public static List<Float> toList(float[] array) {
        final List<Float> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Converts an array of doubles to a List of Doubles.
     * 
     * @param array the array of doubles to convert
     * @return a List of Doubles containing the elements of the array
     */
    public static List<Double> toList(double[] array) {
        final List<Double> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Converts an array of booleans to a List of Booleans.
     * 
     * @param array the array of booleans to convert
     * @return a List of Booleans containing the elements of the array
     */
    public static List<Boolean> toList(boolean[] array) {
        final List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Converts a list to a map using a key mapper function.
     * 
     * @param <T>       Type of elements in the list
     * @param <K>       Type of keys produced by the key mapper function
     * @param list      the list to convert
     * @param keyMapper a function that maps each element to a key used for the map
     * @return a map where keys are produced by the key mapper function and values are the elements of the list
     */
    public static <T, K> Map<K, T> toMap(List<T> list, Function<? super T, ? extends K> keyMapper) {
        return list.stream()
                .collect(Collectors.toMap(
                        keyMapper,
                        p -> p,
                        (p, q) -> p));
    }

    /**
     * Converts a map's values to a list.
     * 
     * @param <K> Type of keys in the map
     * @param <V> Type of values in the map
     * @param map the map whose values are to be converted
     * @return a list containing the values of the map
     */
    public static <K, V> List<V> valuesToList(Map<K, V> map) {
        return new ArrayList<>(map.values());
    }
}

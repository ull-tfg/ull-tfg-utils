package es.ull.utils.collection;

import java.util.Objects;

/**
 * Tuple2 class is a class that represents a tuple of two elements.
 * 
 * @param <T1> Type of the first element of the tuple.
 * @param <T2> Type of the second element of the tuple.
 */
public class Tuple2<T1, T2> {

    /**
     * First element of the tuple.
     */
    private T1 element0;
    /**
     * Second element of the tuple.
     */
    private T2 element1;

    /**
     * Constructor for a tuple with two elements.
     * 
     * @param element0 First element of the tuple.
     * @param element1 Second element of the tuple.
     */
    public Tuple2(T1 element0, T2 element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    /**
     * Factory method to create a tuple with two elements.
     * 
     * @param <L>      Type of the first element of the tuple.
     * @param <M>      Type of the second element of the tuple.
     * @param element0 First element of the tuple.
     * @param element1 Second element of the tuple.
     * @return A new instance of Tuple2 with the specified elements.
     */
    public static <L, M> Tuple2<L, M> of(L element0, M element1) {
        return new Tuple2<>(element0, element1);
    }

    /**
     * Checks if this tuple is equal to another object.
     * 
     * @param otherObject The object to compare with.
     * @return true if the other object is equal to this tuple, false otherwise.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }
        final Tuple2<?, ?> otherTuple = (Tuple2<?, ?>) otherObject;
        return this.element0.equals(otherTuple.element0) && this.element1.equals(otherTuple.element1);
    }

    /**
     * Returns the first element of the tuple.
     * 
     * @return The first element of the tuple.
     */
    public T1 get0() {
        return this.element0;
    }

    /**
     * Returns the second element of the tuple.
     * 
     * @return The second element of the tuple.
     */
    public T2 get1() {
        return this.element1;
    }

    /**
     * Returns the hash code of the tuple.
     * 
     * @return The hash code of the tuple.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.get0(), this.get1());
    }

    /**
     * Sets the first element of the tuple.
     * 
     * @param element0 first element of the tuple.
     */
    public void set0(T1 element0) {
        this.element0 = element0;
    }

    /**
     * Sets the second element of the tuple.
     * 
     * @param element1 second element of the tuple.
     */
    public void set1(T2 element1) {
        this.element1 = element1;
    }

    /**
     * Returns a string representation of the tuple.
     * 
     * @return String representation of the tuple.
     */
    @Override
    public String toString() {
        return "(" + this.get0() + ", " + this.get1() + ")";
    }
}

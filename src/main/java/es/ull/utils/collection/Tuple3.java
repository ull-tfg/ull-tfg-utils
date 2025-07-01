package es.ull.utils.collection;

import java.util.Objects;
import java.util.function.Function;

/**
 * Tuple3 class is a class that represents a tuple of three elements.
 * 
 * @param <L>
 * @param <M>
 * @param <R>
 */
public class Tuple3<L, M, R> extends Tuple2<L, M> {

    /**
     * Third element of the tuple.
     */
    private R element2;

    /**
     * Constructor for a tuple with three elements.
     * 
     * @param element0 First element of the tuple.
     * @param element1 Second element of the tuple.
     * @param element2 Third element of the tuple.
     */
    public Tuple3(L element0, M element1, R element2) {
        super(element0, element1);
        this.element2 = element2;
    }

    /**
     * Factory method to create a tuple with three elements.
     * 
     * @param <L>
     * @param <M>
     * @param <R>
     * @param element0
     * @param element1
     * @param element2
     * @return
     */
    public static <L, M, R> Tuple3<L, M, R> of(L element0, M element1, R element2) {
        return new Tuple3<>(element0, element1, element2);
    }

    /**
     * @param otherObject
     * @return
     */
    @Override
    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) {
            return false;
        }
        final Tuple3<?, ?, ?> otherTuple = (Tuple3<?, ?, ?>) otherObject;
        return Objects.equals(this.element2, otherTuple.element2);
    }

    /**
     * It returns the third element of the tuple.
     * 
     * @return the third element of the tuple.
     */
    public R get2() {
        return this.element2;
    }

    /**
     * It returns the hash code of the tuple.
     * 
     * @return the hash code of the tuple.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.get0(), super.get1(), this.get2());
    }

    /**
     * Sets the third element of the tuple.
     * 
     * @param element2 the third element of the tuple.
     */
    public void set2(R element2) {
        this.element2 = element2;
    }

    /**
     * It returns a string representation of the tuple.
     * 
     * @return the string representation of the tuple.
     */
    @Override
    public String toString() {
        return "(" + super.get0() + ", " + super.get1() + ", " + this.get2() + ")";
    }

    /**
     * It returns a string representation of the tuple using a parser.
     * 
     * @param parser the parser to use.
     * @return the string representation of the tuple.
     */
    public String toString(Function<Tuple3<L, M, R>, String> parser) {
        return parser.apply(this);
    }
}

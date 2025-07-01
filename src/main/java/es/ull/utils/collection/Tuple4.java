package es.ull.utils.collection;

import java.util.Objects;

/**
 * Tuple4 class is a class that represents a tuple of four elements.
 * 
 * @param <L> Type
 * @param <M> Type
 * @param <R> Type
 * @param <S> Type
 */
public class Tuple4<L, M, R, S> extends Tuple3<L, M, R> {

    /**
     * Fourth element of the tuple.
     */
    private S element3;

    /**
     * Constructor for Tuple4.
     * @param element0 
     * @param element1
     * @param element2
     * @param element3
     */
    public Tuple4(L element0, M element1, R element2, S element3) {
        super(element0, element1, element2);
        this.element3 = element3;
    }

    public static <L, M, R, S> Tuple4 of(L element0, M element1, R element2, S element3) {
        return new Tuple4<>(element0, element1, element2, element3);
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final Tuple4<?, ?, ?, ?> other = (Tuple4<?, ?, ?, ?>) obj;
        if (!Objects.equals(this.element3, other.element3)) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public S get3() {
        return this.element3;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.get0(), super.get1(), super.get2(), this.get3());
    }

    /**
     * @param element3
     */
    public void set3(S element3) {
        this.element3 = element3;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "(" + this.get0() + ", " + this.get1() + ", " + this.get2() + ", " + this.get3() + ")";
    }
}

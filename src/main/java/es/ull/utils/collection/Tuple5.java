package es.ull.utils.collection;

import java.util.Objects;

/**
 * Tuple5 class is a class that represents a tuple of five elements.
 * 
 * @param <L> Type
 * @param <M> Type
 * @param <R> Type
 * @param <S> Type
 * @param <U> Type
 */
public class Tuple5<L, M, R, S, U> extends Tuple4<L, M, R, S> {

    /**
     *
     */
    private U element4;

    /**
     * @param element0
     * @param element1
     * @param element2
     * @param element3
     * @param element4
     */
    public Tuple5(L element0, M element1, R element2, S element3, U element4) {
        super(element0, element1, element2, element3);
        this.element4 = element4;
    }

    public static <L, M, R, S, U> Tuple5 of(L element0, M element1, R element2, S element3, U element4) {
        return new Tuple5<>(element0, element1, element2, element3, element4);
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
        final Tuple5<?, ?, ?, ?, ?> other = (Tuple5<?, ?, ?, ?, ?>) obj;
        if (!Objects.equals(this.element4, other.element4)) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public U get4() {
        return this.element4;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.get0(), super.get1(), super.get2(), super.get3(), this.get4());
    }

    /**
     * @param element4
     */
    public void set4(U element4) {
        this.element4 = element4;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "(" + this.get0() + ", " + this.get1() + ", " + this.get2() + ", " + this.get3() + ", " + this.get4() + ")";
    }
}

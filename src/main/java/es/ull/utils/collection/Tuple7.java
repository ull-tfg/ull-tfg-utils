package es.ull.utils.collection;

import java.util.Objects;

/**
 * Tuple7 class is a class that represents a tuple of seven elements.
 * 
 * @param <L> Type
 * @param <M> Type
 * @param <R> Type
 * @param <S> Type
 * @param <U> Type
 * @param <V> Type
 * @param <W> Type
 */
public class Tuple7<L, M, R, S, U, V, W> extends Tuple6<L, M, R, S, U, V> {

    /**
     * 
     */
    private W element6;

    /**
     * @param element0
     * @param element1
     * @param element2
     * @param element3
     * @param element4
     * @param element5
     * @param element6
     */
    public Tuple7(L element0, M element1, R element2, S element3, U element4, V element5, W element6) {
        super(element0, element1, element2, element3, element4, element5);
        this.element6 = element6;
    }

    public static <L, M, R, S, U, V, W, X> Tuple7 of(L element0, M element1, R element2, S element3, U element4, V element5, W element6) {
        return new Tuple7<>(element0, element1, element2, element3, element4, element5, element6);
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
        final Tuple7<?, ?, ?, ?, ?, ?, ?> other = (Tuple7<?, ?, ?, ?, ?, ?, ?>) obj;
        if (!Objects.equals(this.element6, other.element6)) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public W get6() {
        return this.element6;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.get0(), super.get1(), super.get2(), super.get3(), super.get4(), super.get5(), this.get6());
    }

    /**
     * @param element6
     */
    public void set6(W element6) {
        this.element6 = element6;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "(" + this.get0() + ", " + this.get1() + ", " + this.get2() + ", " + this.get3() + ", " + this.get4() + ", " + this.get5() + ", " + this.get6() + ")";
    }
}

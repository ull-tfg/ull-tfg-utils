package es.ull.utils.collection;

import java.util.Objects;

/**
 * Tuple6 class is a class that represents a tuple of six elements.
 * @param <L> Type
 * @param <M> Type
 * @param <R> Type
 * @param <S> Type
 * @param <U> Type
 * @param <V> Type
 */
public class Tuple6<L, M, R, S, U, V> extends Tuple5<L, M, R, S, U> {

    /**
     * 
     */
    private V element5;

    /**
     * 
     * @param element0
     * @param element1
     * @param element2
     * @param element3
     * @param element4
     * @param element5 
     */
    public Tuple6(L element0, M element1, R element2, S element3, U element4, V element5) {
        super(element0, element1, element2, element3, element4);
        this.element5 = element5;
    }
    
    public static <L, M, R, S, U, V, W> Tuple6 of(L element0, M element1, R element2, S element3, U element4, V element5) {
        return new Tuple6<>(element0, element1, element2, element3, element4, element5);
    }

    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final Tuple6<?, ?, ?, ?, ?, ?> other = (Tuple6<?, ?, ?, ?, ?, ?>) obj;
        if (!Objects.equals(this.element5, other.element5)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return 
     */
    public V get5() {
        return this.element5;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.get0(), super.get1(), super.get2(), super.get3(), super.get4(), this.get5());
    }

    /**
     * 
     * @param element5 
     */
    public void set5(V element5) {
        this.element5 = element5;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "(" + this.get0() + ", " + this.get1() + ", " + this.get2() + ", " + this.get3() + ", " + this.get4() + ", " + this.get5() + ")";
    }
}

package es.ull.utils.collection;

import java.util.Objects;

/**
 * Tuple8 class is a class that represents a tuple of eight elements.
 * 
 * @param <L>
 * @param <M>
 * @param <R>
 * @param <S>
 * @param <U>
 * @param <V>
 * @param <W>
 * @param <X>
 */
public class Tuple8<L, M, R, S, U, V, W, X> extends Tuple7<L, M, R, S, U, V, W> {

    /**
     * 
     */
    private X element7;

    /**
     * @param element0  
     * @param element1
     * @param element2
     * @param element3
     * @param element4
     * @param element5
     * @param element6
     * @param element7
     */
    public Tuple8(L element0, M element1, R element2, S element3, U element4, V element5, W element6, X element7) {
        super(element0, element1, element2, element3, element4, element5, element6);
        this.element7 = element7;
    }

    public static <L, M, R, S, U, V, W, X> Tuple8 of(L element0, M element1, R element2, S element3, U element4, V element5, W element6, X element7) {
        return new Tuple8<>(element0, element1, element2, element3, element4, element5, element6, element7);
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
        final Tuple8<?, ?, ?, ?, ?, ?, ?, ?> other = (Tuple8<?, ?, ?, ?, ?, ?, ?, ?>) obj;
        if (!Objects.equals(this.element7, other.element7)) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public X get7() {
        return this.element7;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.get0(), super.get1(), super.get2(), super.get3(), super.get4(), super.get5(), super.get6(), this.get7());
    }

    /**
     * @param element7
     */
    public void set7(X element7) {
        this.element7 = element7;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "(" + this.get0() + ", " + this.get1() + ", " + this.get2() + ", " + this.get3() + ", " + this.get4() + ", " + this.get5() + ", " + this.get6() + ", " + this.get7() + ")";
    }
}

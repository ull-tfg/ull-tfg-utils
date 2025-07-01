package es.ull.utils.collection;

import java.util.Objects;

/**
 * Tuple9 class is a class that represents a tuple of nine elements.
 * 
 * @param <L> Type
 * @param <M> Type
 * @param <R> Type
 * @param <S> Type
 * @param <U> Type
 * @param <V> Type
 * @param <W> Type
 * @param <X> Type
 * @param <Y> Type
 */
public class Tuple9<L, M, R, S, U, V, W, X, Y> extends Tuple8<L, M, R, S, U, V, W, X> {

    /**
     *
     */
    private Y element8;

    /**
     * @param element0
     * @param element1
     * @param element2
     * @param element3
     * @param element4
     * @param element5
     * @param element6
     * @param element7
     * @param element8
     */
    public Tuple9(L element0, M element1, R element2, S element3, U element4, V element5, W element6, X element7, Y element8) {
        super(element0, element1, element2, element3, element4, element5, element6, element7);
        this.element8 = element8;
    }

    public static <L, M, R, S, U, V, W, X, Y> Tuple9<L, M, R, S, U, V, W, X, Y> of(L element0, M element1, R element2, S element3, U element4, V element5, W element6, X element7, Y element8) {
        return new Tuple9<>(element0, element1, element2, element3, element4, element5, element6, element7, element8);
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
        final Tuple9<?, ?, ?, ?, ?, ?, ?, ?, ?> other = (Tuple9<?, ?, ?, ?, ?, ?, ?, ?, ?>) obj;
        return Objects.equals(this.element8, other.element8);
    }

    /**
     * @return
     */
    public Y get8() {
        return this.element8;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.get0(),
                super.get1(),
                super.get2(),
                super.get3(),
                super.get4(),
                super.get5(),
                super.get6(),
                super.get7(),
                this.get8());
    }

    /**
     * @param element7
     */
    public void set8(Y element7) {
        this.element8 = element7;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "(" + this.get0() + ", " + this.get1() + ", " + this.get2() + ", " + this.get3() + ", " + this.get4() + ", " + this.get5() + ", " + this.get6() + ", " + this.get7() + ", " + this.get8() + ")";
    }
}

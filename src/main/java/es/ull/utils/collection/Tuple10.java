package es.ull.utils.collection;

import java.util.Objects;

/**
 * Tuple10 class is a class that represents a tuple of ten elements.
 * 
 * @param <L>
 * @param <M>
 * @param <R>
 * @param <S>
 * @param <U>
 * @param <V>
 * @param <W>
 * @param <X>
 * @param <Y>
 * @param <Z>
 */
public class Tuple10<L, M, R, S, U, V, W, X, Y, Z> extends Tuple9<L, M, R, S, U, V, W, X, Y> {

    /**
     *
     */
    private Z element9;

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
     * @param element9
     */
    public Tuple10(L element0, M element1, R element2, S element3, U element4, V element5, W element6, X element7, Y element8, Z element9) {
        super(element0, element1, element2, element3, element4, element5, element6, element7, element8);
        this.element9 = element9;
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
        final Tuple10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> other = (Tuple10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) obj;
        if (!Objects.equals(this.element9, other.element9)) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public Z get9() {
        return this.element9;
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
                super.get8(),
                this.get9());
    }

    /**
     * @param element7
     */
    public void set9(Z element7) {
        this.element9 = element7;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "(" + this.get0() + ", " + this.get1() + ", " + this.get2() + ", " + this.get3() + ", " + this.get4() + ", " + this.get5() + ", " + this.get6() + ", " + this.get7() + ", " + this.get8() + ", " + this.get9() + ")";
    }
}

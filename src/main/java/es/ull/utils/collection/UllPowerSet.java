package es.ull.utils.collection;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class is used to generate the power set of a given set.
 * 
 * @param <E> The type of the elements in the set.
 */
public class UllPowerSet<E> implements Iterator<Set<E>>, Iterable<Set<E>> {

    /**
     *
     */
    private E[] arr = null;
    /**
     *
     */
    private BitSet bset = null;

    /**
     * @param set
     */
    @SuppressWarnings("unchecked")
    public UllPowerSet(Set<E> set) {
        this.arr = (E[]) set.toArray();
        this.bset = new BitSet(this.arr.length + 1);
    }

    /**
     * @return
     */
    @Override
    public boolean hasNext() {
        return !this.bset.get(this.arr.length);
    }

    /**
     * @return
     */
    @Override
    public Set<E> next() {
        Set<E> returnSet = new TreeSet<>();
        for (int i = 0; i < this.arr.length; i++) {
            if (this.bset.get(i)) {
                returnSet.add(this.arr[i]);
            }
        }
        for (int i = 0; i < this.bset.size(); i++) {
            if (!this.bset.get(i)) {
                this.bset.set(i);
                break;
            } else {
                this.bset.clear(i);
            }
        }
        return returnSet;
    }

    /**
     *
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not Supported!");
    }

    /**
     * @return
     */
    @Override
    public Iterator<Set<E>> iterator() {
        return this;
    }
}

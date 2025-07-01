package es.ull.utils.collection;

public final class UllImmutablePair<T, U> {

    private final T first;
    private final U second;

    public UllImmutablePair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> UllImmutablePair<T, U> of(T first, U second) {
        return new UllImmutablePair<>(first, second);
    }

    public static <T, U> UllImmutablePair<T, U> first(T first) {
        return new UllImmutablePair<>(first, null);
    }

    public static <T, U> UllImmutablePair<T, U> second(U second) {
        return new UllImmutablePair<>(null, second);
    }

    public boolean hasFirst() {
        return first != null;
    }

    public boolean hasSecond() {
        return second != null;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public void ifFirst(java.util.function.Consumer<? super T> action) {
        if (this.hasFirst()) {
            action.accept(this.first);
        }
    }

    public void ifSecond(java.util.function.Consumer<? super U> action) {
        if (this.hasSecond()) {
            action.accept(this.second);
        }
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        final UllImmutablePair<?, ?> otherPair = (UllImmutablePair<?, ?>) otherObject;
        return first.equals(otherPair.first) && second.equals(otherPair.second);
    }

    @Override
    public int hashCode() {
        return 31 * first.hashCode() + (second != null ? second.hashCode() : 0);
    }

    public UllImmutablePair<U, T> swap() {
        return new UllImmutablePair<>(second, first);
    }

    public UllImmutablePair<T, U> withFirst(T first) {
        return new UllImmutablePair<>(first, second);
    }

    public UllImmutablePair<T, U> withSecond(U second) {
        return new UllImmutablePair<>(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

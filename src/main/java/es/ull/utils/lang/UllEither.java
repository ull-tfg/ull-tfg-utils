package es.ull.utils.lang;

public abstract class UllEither<L, R> {

    /**
     * Private constructor to prevent direct instantiation.
     */
    private UllEither() {
    }

    /**
     * Create a new Left instance.
     * 
     * @param <L>   Left value type.
     * @param <R>   Right value type.
     * @param value the Left value.
     * @return a new UllEither instance.
     */
    public static <L, R> UllEither<L, R> left(L value) {
        return new Left<>(value);
    }

    /**
     * Create a new Right instance.
     * 
     * @param <L>   Left value type.
     * @param <R>   Right value type.
     * @param value the Right value.
     * @return a new UllEither instance.
     */
    public static <L, R> UllEither<L, R> right(R value) {
        return new Right<>(value);
    }

    /**
     * Create an empty instance.
     * 
     * @param <L> Left value type.
     * @param <R> Right value type.
     * @return a new UllEither instance.
     */
    public static <L, R> UllEither<L, R> empty() {
        return UllEither.right(null);
    }

    /**
     * Check if this instance is a Left.
     * 
     * @return true if this is a Left, false otherwise.
     */
    public abstract boolean isLeft();

    /**
     * Check if this instance is a Right.
     * 
     * @return true if this is a Right, false otherwise.
     */
    public abstract boolean isRight();

    /**
     * Get the Left value.
     * 
     * @return the Left value.
     */
    public abstract L getLeft();

    /**
     * Get the Right value.
     * 
     * @return the Right value.
     */
    public abstract R getRight();

    /**
     * Get the Left value or a default value if this is a Right.
     * 
     * @param other the default value.
     * @return the Left value if this is a Left, otherwise the default value.
     */
    public abstract L getLeftOrElse(L other);

    /**
     * Get the Right value or a default value if this is a Left.
     * 
     * @param other the default value.
     * @return the Right value if this is a Right, otherwise the default value.
     */
    public abstract R getRightOrElse(R other);

    /**
     * Map the Left value to a new value.
     * 
     * @param <T>    the new Left value type.
     * @param mapper the mapping function.
     * @return a new UllEither instance with the mapped value.
     */
    public abstract <T> UllEither<T, R> mapLeft(java.util.function.Function<? super L, ? extends T> mapper);

    /**
     * Map the Right value to a new value.
     * 
     * @param <T>    the new Right value type.
     * @param mapper the mapping function.
     * @return a new UllEither instance with the mapped value.
     */
    public abstract <T> UllEither<L, T> mapRight(java.util.function.Function<? super R, ? extends T> mapper);

    /**
     * FlatMap the Right value to a new UllEither instance.
     * 
     * @param <T>    the new Right value type.
     * @param mapper the mapping function.
     * @return a new UllEither instance with the mapped value.
     */
    public abstract <T> UllEither<L, T> flatMap(java.util.function.Function<? super R, UllEither<L, T>> mapper);

    /**
     * Fold the Left or Right value to a new value.
     * 
     * @param <T>         the new value type.
     * @param leftMapper  Left value mapping function.
     * @param rightMapper Right value mapping function.
     * @return the new value.
     */
    public abstract <T> T fold(
            java.util.function.Function<? super L, ? extends T> leftMapper,
            java.util.function.Function<? super R, ? extends T> rightMapper);

    /**
     * Perform an action on the Left value.
     * 
     * @param action the action to perform.
     */
    public abstract void peekLeft(java.util.function.Consumer<? super L> action);

    /**
     * Perform an action on the Right value.
     * 
     * @param action the action to perform.
     */
    public abstract void peek(java.util.function.Consumer<? super R> action);

    /**
     * Perform an action on the Left value.
     * 
     * @param action the action to perform.
     * @param <T>    the new value type.
     */
    public void ifLeft(java.util.function.Consumer<? super L> action) {
        if (isLeft()) {
            action.accept(getLeft());
        }
    }

    /**
     * Perform an action on the Right value.
     * 
     * @param action the action to perform.
     * @param <T>    the new value type.
     */
    public void ifRight(java.util.function.Consumer<? super R> action) {
        if (isRight()) {
            action.accept(getRight());
        }
    }

    public String toResult(String leftPrefix, String rightPrefix) {
        return isLeft() ? leftPrefix + getLeft() : rightPrefix + getRight();
    }

    public <T> T merge(java.util.function.BiFunction<? super L, ? super R, ? extends T> combiner) {
        return isLeft() ? combiner.apply(getLeft(), null) : combiner.apply(null, getRight());
    }

    public <X extends Throwable> R rightOrElseThrow(java.util.function.Function<? super L, ? extends X> exceptionSupplier) throws X {
        if (isRight()) {
            return getRight();
        }
        throw exceptionSupplier.apply(getLeft());
    }

    public R getRightOrNull() {
        return isRight() ? getRight() : null;
    }

    public L getLeftOrNull() {
        return isLeft() ? getLeft() : null;
    }

    public static <L, R> java.util.AbstractMap.SimpleEntry<java.util.List<L>, java.util.List<R>> toEitherList(java.util.List<UllEither<L, R>> eithers) {
        java.util.List<L> lefts = new java.util.ArrayList<>();
        java.util.List<R> rights = new java.util.ArrayList<>();
        for (UllEither<L, R> either : eithers) {
            if (either.isLeft()) {
                lefts.add(either.getLeft());
            } else {
                rights.add(either.getRight());
            }
        }
        return new java.util.AbstractMap.SimpleEntry<>(lefts, rights);
    }

    public <X extends Throwable> L leftOrElseThrow(java.util.function.Supplier<? extends X> exceptionSupplier) throws X {
        if (isLeft()) {
            return getLeft();
        }
        throw exceptionSupplier.get();
    }

    public <T, U> UllEither<T, U> flatMapBoth(java.util.function.Function<? super L, UllEither<T, U>> leftMapper,
            java.util.function.Function<? super R, UllEither<T, U>> rightMapper) {
        return isLeft() ? leftMapper.apply(getLeft()) : rightMapper.apply(getRight());
    }

    public java.util.AbstractMap.SimpleEntry<L, R> toPair() {
        return isLeft() ? new java.util.AbstractMap.SimpleEntry<>(getLeft(), null) : new java.util.AbstractMap.SimpleEntry<>(null, getRight());
    }

    public void ifPresent(java.util.function.Consumer<? super R> rightAction, java.util.function.Consumer<? super L> leftAction) {
        if (isRight()) {
            rightAction.accept(getRight());
        } else {
            leftAction.accept(getLeft());
        }
    }

    public boolean containsAny(java.util.function.Predicate<? super L> leftPredicate, java.util.function.Predicate<? super R> rightPredicate) {
        return isLeft() ? leftPredicate.test(getLeft()) : rightPredicate.test(getRight());
    }

    public java.util.List<Object> foldToList() {
        return isLeft() ? java.util.Collections.singletonList(getLeft()) : java.util.Collections.singletonList(getRight());
    }

    public static <L, R> java.util.Map<Boolean, java.util.List<Object>> toEitherList(java.util.Collection<UllEither<L, R>> eithers) {
        return eithers.stream()
                .collect(java.util.stream.Collectors.partitioningBy(
                        UllEither::isRight,
                        java.util.stream.Collectors.mapping(e -> e.isRight() ? e.getRight() : e.getLeft(),
                                java.util.stream.Collectors.toList())));
    }

    public boolean containsLeft(java.util.function.Predicate<? super L> predicate) {
        return isLeft() && predicate.test(getLeft());
    }

    public boolean containsRight(java.util.function.Predicate<? super R> predicate) {
        return isRight() && predicate.test(getRight());
    }

    public void match(
            java.util.function.Consumer<? super L> leftAction,
            java.util.function.Consumer<? super R> rightAction) {
        if (isLeft()) {
            leftAction.accept(getLeft());
        } else {
            rightAction.accept(getRight());
        }
    }

    public R rightOrNull() {
        return isRight() ? getRight() : null;
    }

    public L leftOrNull() {
        return isLeft() ? getLeft() : null;
    }

    public void ifRightOrElse(
            java.util.function.Consumer<? super R> rightAction,
            java.util.function.Consumer<? super L> leftAction) {
        if (isRight()) {
            rightAction.accept(getRight());
        } else {
            leftAction.accept(getLeft());
        }
    }

    public void onBoth(
            java.util.function.Consumer<? super L> leftAction,
            java.util.function.Consumer<? super R> rightAction) {
        if (isLeft()) {
            leftAction.accept(getLeft());
        } else {
            rightAction.accept(getRight());
        }
    }

    public <T> java.util.Optional<T> foldToOptional(
            java.util.function.Function<? super L, ? extends T> leftMapper,
            java.util.function.Function<? super R, ? extends T> rightMapper) {
        return isLeft() ?
                java.util.Optional.ofNullable(leftMapper.apply(getLeft())) :
                java.util.Optional.ofNullable(rightMapper.apply(getRight()));
    }

    public UllEither<L, R> onEither(java.util.function.Consumer<? super L> leftAction, java.util.function.Consumer<? super R> rightAction) {
        if (isLeft()) {
            leftAction.accept(getLeft());
        } else {
            rightAction.accept(getRight());
        }
        return this;
    }

    /**
     * Filter the Right value.
     * 
     * @param predicate     the predicate to test the Right value.
     * @param errorSupplier the supplier to create a Left value if the predicate fails.
     * @return a new UllEither instance.
     */
    public UllEither<L, R> filterOrElse(java.util.function.Predicate<? super R> predicate, java.util.function.Supplier<? extends L> errorSupplier) {
        if (isRight() && !predicate.test(getRight())) {
            return UllEither.left(errorSupplier.get());
        }
        return this;
    }

    public java.util.Optional<L> toOptionalLeft() {
        return isLeft() ? java.util.Optional.of(getLeft()) : java.util.Optional.empty();
    }

    /**
     * Convert this instance to an Optional.
     * 
     * @return an Optional instance.
     */
    public java.util.Optional<R> toOptionalRight() {
        return isRight() ? java.util.Optional.of(getRight()) : java.util.Optional.empty();
    }

    public boolean isEmpty() {
        return isLeft() && getLeft() == null || isRight() && getRight() == null;
    }

    public boolean nonEmpty() {
        return !isEmpty();
    }

    public boolean rightContains(R value) {
        return isRight() && getRight().equals(value);
    }

    public boolean leftContains(L value) {
        return isLeft() && getLeft().equals(value);
    }

    public R orElseGet(java.util.function.Supplier<? extends R> supplier) {
        return isRight() ? getRight() : supplier.get();
    }

    public <X extends Throwable> R orElseThrowWithLeft(java.util.function.Function<? super L, ? extends X> exceptionSupplier) throws X {
        if (isRight()) {
            return getRight();
        }
        throw exceptionSupplier.apply(getLeft());
    }

    public java.util.stream.Stream<R> toStream() {
        return isRight() ? java.util.stream.Stream.of(getRight()) : java.util.stream.Stream.empty();
    }

    /**
     * Map the Left and Right values to new values.
     * 
     * @param <T>         The new Left value type.
     * @param <U>         The new Right value type.
     * @param leftMapper  Left value mapping function.
     * @param rightMapper Right value mapping function.
     * @return a new UllEither instance.
     */
    public <T, U> UllEither<T, U> bimap(
            java.util.function.Function<? super L, ? extends T> leftMapper,
            java.util.function.Function<? super R, ? extends U> rightMapper) {
        return isLeft() ? UllEither.left(leftMapper.apply(getLeft())) : UllEither.right(rightMapper.apply(getRight()));
    }

    /**
     * Combine this instance with another instance.
     * 
     * @param <U>      The Right value type of the other instance.
     * @param <T>      The new Right value type.
     * @param other    the other instance.
     * @param combiner the combiner function.
     * @return a new UllEither instance.
     */
    public <U, T> UllEither<L, T> combine(
            UllEither<L, U> other,
            java.util.function.BiFunction<? super R, ? super U, ? extends T> combiner) {
        if (this.isLeft()) {
            return UllEither.left(this.getLeft());
        }
        if (other.isLeft()) {
            return UllEither.left(other.getLeft());
        }
        return UllEither.right(combiner.apply(this.getRight(), other.getRight()));
    }

    /**
     * Get the Right value or throw an exception if this is a Left.
     * 
     * @param exceptionSupplier the exception supplier.
     * @return the Right value.
     */
    public R getOrThrow(java.util.function.Supplier<? extends RuntimeException> exceptionSupplier) {
        if (isRight()) {
            return getRight();
        }
        throw exceptionSupplier.get();
    }

    public UllEither<L, R> filterRight(
            java.util.function.Predicate<? super R> predicate,
            java.util.function.Supplier<? extends L> errorSupplier) {
        if (isRight() && !predicate.test(getRight())) {
            return UllEither.left(errorSupplier.get());
        }
        return this;
    }

    public UllEither<L, R> filterLeft(
            java.util.function.Predicate<? super L> predicate,
            java.util.function.Supplier<? extends R> successSupplier) {
        if (isLeft() && !predicate.test(getLeft())) {
            return UllEither.right(successSupplier.get());
        }
        return this;
    }

    public <T> UllEither<L, T> mergeWith(
            UllEither<L, R> other,
            java.util.function.BiFunction<? super R, ? super R, ? extends T> merger) {
        if (this.isLeft()) {
            return UllEither.left(this.getLeft());
        } else if (other.isLeft()) {
            return UllEither.left(other.getLeft());
        } else {
            return UllEither.right(merger.apply(this.getRight(), other.getRight()));
        }
    }

    public java.util.List<R> rigthToList() {
        return isRight() ? java.util.Collections.singletonList(getRight()) : java.util.Collections.emptyList();
    }

    public java.util.List<L> leftToList() {
        return isLeft() ? java.util.Collections.singletonList(getLeft()) : java.util.Collections.emptyList();
    }

    public boolean containsBoth(java.util.function.Predicate<? super L> leftPredicate, java.util.function.Predicate<? super R> rightPredicate) {
        return isLeft() ? leftPredicate.test(getLeft()) : rightPredicate.test(getRight());
    }

    public static <L, R> java.util.Map<Boolean, java.util.List<UllEither<L, R>>> partition(java.util.List<UllEither<L, R>> eithers) {
        return eithers.stream()
                .collect(java.util.stream.Collectors.partitioningBy(UllEither::isRight));
    }

    public UllEither<L, R> orElseSwitch(java.util.function.Function<? super L, ? extends R> switchFunction) {
        return isLeft() ? UllEither.right(switchFunction.apply(getLeft())) : this;
    }

    public <T> T transform(java.util.function.Function<UllEither<L, R>, T> transformer) {
        return transformer.apply(this);
    }

    public void ifBoth(
            java.util.function.Consumer<? super L> leftAction,
            java.util.function.Consumer<? super R> rightAction) {
        if (isLeft()) {
            leftAction.accept(getLeft());
        } else {
            rightAction.accept(getRight());
        }
    }

    public UllEither<L, R> flatRecover(
            java.util.function.Function<? super L, UllEither<L, R>> recoveryFunction) {
        return isLeft() ? recoveryFunction.apply(getLeft()) : this;
    }

    public UllEither<L, R> onLeft(java.util.function.Consumer<? super L> action) {
        if (isLeft()) {
            action.accept(getLeft());
        }
        return this;
    }

    public java.util.concurrent.CompletableFuture<R> toCompletableFuture(java.util.function.Supplier<? extends Throwable> leftExceptionSupplier) {
        if (isRight()) {
            return java.util.concurrent.CompletableFuture.completedFuture(getRight());
        } else {
            java.util.concurrent.CompletableFuture<R> future = new java.util.concurrent.CompletableFuture<>();
            future.completeExceptionally(leftExceptionSupplier.get());
            return future;
        }
    }

    public <U, T> UllEither<L, T> combineWith(
            UllEither<L, U> other,
            java.util.function.BiFunction<? super R, ? super U, ? extends T> combiner) {
        if (this.isLeft()) {
            return UllEither.left(this.getLeft());
        }
        if (other.isLeft()) {
            return UllEither.left(other.getLeft());
        }
        return UllEither.right(combiner.apply(this.getRight(), other.getRight()));
    }

    public <T, U> UllEither<L, U> apply(UllEither<L, java.util.function.Function<? super R, ? extends U>> eitherFunction) {
        if (this.isLeft())
            return UllEither.left(getLeft());
        if (eitherFunction.isLeft())
            return UllEither.left(eitherFunction.getLeft());
        return UllEither.right(eitherFunction.getRight().apply(getRight()));
    }

    public R rightOrElse(R other) {
        return isRight() ? getRight() : other;
    }

    public L leftOrElse(L other) {
        return isLeft() ? getLeft() : other;
    }

    public boolean isLeftAnd(java.util.function.Predicate<? super L> predicate) {
        return isLeft() && predicate.test(getLeft());
    }

    public boolean isRightAnd(java.util.function.Predicate<? super R> predicate) {
        return isRight() && predicate.test(getRight());
    }

    public UllEither<L, R> peekBoth(java.util.function.Consumer<? super L> leftAction, java.util.function.Consumer<? super R> rightAction) {
        if (isLeft()) {
            leftAction.accept(getLeft());
        } else {
            rightAction.accept(getRight());
        }
        return this;
    }

    public <T> T reduce(java.util.function.BiFunction<? super L, ? super R, ? extends T> reducer) {
        return isLeft() ? reducer.apply(getLeft(), null) : reducer.apply(null, getRight());
    }

    public java.util.stream.Stream<Object> toStreamBoth() {
        return isLeft() ? java.util.stream.Stream.of(getLeft()) : java.util.stream.Stream.of(getRight());
    }

    public <T> T mapOrElse(java.util.function.Function<? super R, ? extends T> mapper, T defaultValue) {
        return isRight() ? mapper.apply(getRight()) : defaultValue;
    }

    public UllEither<L, R> onRight(java.util.function.Consumer<? super R> action) {
        if (isRight()) {
            action.accept(getRight());
        }
        return this;
    }

    public java.util.Map<String, Object> toMap(String leftKey, String rightKey) {
        if (isLeft()) {
            return java.util.Collections.singletonMap(leftKey, getLeft());
        } else {
            return java.util.Collections.singletonMap(rightKey, getRight());
        }
    }

    public <T> T foldBoth(
            java.util.function.Function<? super L, ? extends T> leftMapper,
            java.util.function.Function<? super R, ? extends T> rightMapper) {
        return isLeft() ? leftMapper.apply(getLeft()) : rightMapper.apply(getRight());
    }

    public static <L, R, T> T foldEither(
            java.util.List<UllEither<L, R>> eithers,
            java.util.function.Function<? super L, ? extends T> leftFolder,
            java.util.function.Function<? super R, ? extends T> rightFolder,
            java.util.function.BinaryOperator<T> combiner) {
        return eithers.stream()
                .map(e -> e.fold(leftFolder, rightFolder))
                .reduce(combiner)
                .orElseThrow(() -> new IllegalStateException("Cannot fold empty list of Either"));
    }

    public <T, U> UllEither<T, U> mapBoth(
            java.util.function.Function<? super L, ? extends T> leftMapper,
            java.util.function.Function<? super R, ? extends U> rightMapper) {
        return isLeft() ?
                UllEither.left(leftMapper.apply(getLeft())) :
                UllEither.right(rightMapper.apply(getRight()));
    }

    // New Methods
    public UllEither<L, R> tap(java.util.function.Consumer<? super R> action) {
        if (isRight()) {
            action.accept(getRight());
        }
        return this;
    }

    public UllEither<L, R> tapLeft(java.util.function.Consumer<? super L> action) {
        if (isLeft()) {
            action.accept(getLeft());
        }
        return this;
    }

    public UllEither<R, L> swap() {
        return isLeft() ? UllEither.right(getLeft()) : UllEither.left(getRight());
    }

    public <T> T foldLeft(T identity, java.util.function.BiFunction<T, ? super L, T> accumulator) {
        return isLeft() ? accumulator.apply(identity, getLeft()) : identity;
    }

    public UllEither<L, R> recover(java.util.function.Function<? super L, ? extends R> recoveryFunction) {
        return isLeft() ? UllEither.right(recoveryFunction.apply(getLeft())) : this;
    }

    public UllEither<L, R> recoverWith(java.util.function.Function<? super L, UllEither<L, R>> recoveryFunction) {
        return isLeft() ? recoveryFunction.apply(getLeft()) : this;
    }

    public boolean exists(java.util.function.Predicate<? super R> predicate) {
        return isRight() && predicate.test(getRight());
    }

    public boolean forAll(java.util.function.Predicate<? super R> predicate) {
        return isLeft() || predicate.test(getRight());
    }

    public <U, T> UllEither<L, T> zip(
            UllEither<L, U> other,
            java.util.function.BiFunction<? super R, ? super U, ? extends T> zipper) {
        if (this.isRight() && other.isRight()) {
            return UllEither.right(zipper.apply(this.getRight(), other.getRight()));
        } else if (this.isLeft()) {
            return UllEither.left(this.getLeft());
        } else {
            return UllEither.left(other.getLeft());
        }
    }

    /**
     * It provides a string representation of the either.
     * 
     * @return Representation of the either.
     */
    @Override
    public String toString() {
        return isLeft() ? "Left[" + getLeft() + "]" : "Right[" + getRight() + "]";
    }

    /**
     * Left subclass.
     */
    public static final class Left<L, R> extends UllEither<L, R> {

        /**
         * Left value.
         */
        private final L value;

        /**
         * Private constructor.
         * 
         * @param value the Left value.
         */
        private Left(L value) {
            this.value = value;
        }

        /**
         * Check if this instance is a Left.
         */
        @Override
        public boolean isLeft() {
            return true;
        }

        /**
         * Check if this instance is a Right.
         */
        @Override
        public boolean isRight() {
            return false;
        }

        /**
         * Get the Left value.
         */
        @Override
        public L getLeft() {
            return this.value;
        }

        /**
         * Get the Right value.
         */
        @Override
        public R getRight() {
            throw new IllegalStateException("Cannot get Right value from a Left instance");
        }

        /**
         * Get the Left value or a default value if this is a Right.
         */
        @Override
        public L getLeftOrElse(L other) {
            return this.value;
        }

        /**
         * Get the Right value or a default value if this is a Left.
         */
        @Override
        public R getRightOrElse(R other) {
            return other;
        }

        /**
         * Map the Left value to a new value.
         * 
         * @param <T>    the new Left value type.
         * @param mapper the mapping function.
         * @return a new UllEither instance with the mapped value.
         */
        @Override
        public <T> UllEither<T, R> mapLeft(java.util.function.Function<? super L, ? extends T> mapper) {
            return UllEither.left(mapper.apply(this.value));
        }

        /**
         * Map the Right value to a new value.
         * 
         * @param <T>    the new Right value type.
         * @param mapper the mapping function.
         * @return a new UllEither instance with the mapped value.
         */
        @Override
        public <T> UllEither<L, T> mapRight(java.util.function.Function<? super R, ? extends T> mapper) {
            return UllEither.left(this.value);
        }

        /**
         * FlatMap the Right value to a new UllEither instance.
         * 
         * @param <T>    the new Right value type.
         * @param mapper the mapping function.
         * @return a new UllEither instance with the mapped value.
         */
        @Override
        public <T> UllEither<L, T> flatMap(java.util.function.Function<? super R, UllEither<L, T>> mapper) {
            return UllEither.left(this.value);
        }

        /**
         * Fold the Left or Right value to a new value.
         * 
         * @param <T>         the new value type.
         * @param leftMapper  Left value mapping function.
         * @param rightMapper Right value mapping function.
         * @return the new value.
         */
        @Override
        public <T> T fold(
                java.util.function.Function<? super L, ? extends T> leftMapper,
                java.util.function.Function<? super R, ? extends T> rightMapper) {
            return leftMapper.apply(this.value);
        }

        /**
         * Perform an action on the Left value.
         * 
         * @param action the action to perform.
         * @param <T>    the new value type.
         */
        @Override
        public void peekLeft(java.util.function.Consumer<? super L> action) {
            action.accept(this.value);
        }

        /**
         * Perform an action on the Right value.
         * 
         * @param action the action to perform.
         * @param <T>    the new value type.
         */
        @Override
        public void peek(java.util.function.Consumer<? super R> action) {
            // Do nothing since this is Left.
        }
    }

    /**
     * Right subclass.
     */
    public static final class Right<L, R> extends UllEither<L, R> {

        /**
         * Right value.
         */
        private final R value;

        /**
         * Private constructor.
         * 
         * @param value the Right value.
         */
        private Right(R value) {
            this.value = value;
        }

        /**
         * Check if this instance is a Left.
         * 
         * @return false since this is a Right instance.
         */
        @Override
        public boolean isLeft() {
            return false;
        }

        /**
         * Check if this instance is a Right.
         * 
         * @return true since this is a Right instance.
         */
        @Override
        public boolean isRight() {
            return true;
        }

        /**
         * Get the Left value.
         * 
         * @return the Left value.
         */
        @Override
        public L getLeft() {
            throw new IllegalStateException("Cannot get Left value from a Right instance");
        }

        /**
         * Get the Right value.
         * 
         * @return the Right value.
         */
        @Override
        public R getRight() {
            return this.value;
        }

        /**
         * Get the Left value or a default value if this is a Right.
         * 
         * @param other the default value.
         * @return the Left value if this is a Left, otherwise the default value.
         */
        @Override
        public L getLeftOrElse(L other) {
            return other;
        }

        /**
         * Get the Right value or a default value if this is a Left.
         * 
         * @param other the default value.
         * @return the Right value if this is a Right, otherwise the default value.
         */
        @Override
        public R getRightOrElse(R other) {
            return this.value;
        }

        /**
         * Map the Left value to a new value.
         * 
         * @param <T>    the new Left value type.
         * @param mapper the mapping function.
         * @return a new UllEither instance with the mapped value.
         */
        @Override
        public <T> UllEither<T, R> mapLeft(java.util.function.Function<? super L, ? extends T> mapper) {
            return UllEither.right(this.value);
        }

        /**
         * Map the Right value to a new value.
         * 
         * @param <T>    the new Right value type.
         * @param mapper the mapping function.
         * @return a new UllEither instance with the mapped value.
         */
        @Override
        public <T> UllEither<L, T> mapRight(java.util.function.Function<? super R, ? extends T> mapper) {
            return UllEither.right(mapper.apply(this.value));
        }

        /**
         * FlatMap the Right value to a new UllEither instance.
         * 
         * @param <T>    the new Right value type.
         * @param mapper the mapping function.
         * @return a new UllEither instance with the mapped value.
         */
        @Override
        public <T> UllEither<L, T> flatMap(java.util.function.Function<? super R, UllEither<L, T>> mapper) {
            return mapper.apply(this.value);
        }

        /**
         * @param <T>         the new value type.
         * @param leftMapper  Left value mapping function.
         * @param rightMapper Right value mapping function.
         * @return the new value.
         */
        @Override
        public <T> T fold(
                java.util.function.Function<? super L, ? extends T> leftMapper,
                java.util.function.Function<? super R, ? extends T> rightMapper) {
            return rightMapper.apply(this.value);
        }

        /**
         * Perform an action on the Left value.
         * 
         * @param action the action to perform.
         * @param <T>    the new value type.
         * @return the new value.
         */
        @Override
        public void peekLeft(java.util.function.Consumer<? super L> action) {
            // Do nothing since this is Right.
        }

        /**
         * Perform an action on the Right value.
         * 
         * @param action the action to perform.
         * @param <T>    the new value type.
         * @return the new value.
         */
        @Override
        public void peek(java.util.function.Consumer<? super R> action) {
            action.accept(this.value);
        }
    }
}
package com.miniverse.hbm.util;

import java.util.function.Function;

/**
 * Represents a value that is either of generic type L or R.
 *
 * <p>This class is a simple implementation of the "Either" type commonly used in functional programming.
 * It allows a value to be one of two types: left or right. Typically, left is used to represent an error
 * or alternative result, while right represents a successful result.</p>
 *
 * <p>Usage examples:
 * <ul>
 *   <li>Either.left(value) creates an instance holding a left value.</li>
 *   <li>Either.right(value) creates an instance holding a right value.</li>
 * </ul>
 * Methods such as {@code isLeft()}, {@code left()}, {@code run()}, etc. provide a flexible way to work with the contained value.
 * </p>
 *
 * @param <L> the type of the left value
 * @param <R> the type of the right value
 */
public final class Either<L, R> {

    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(value, true);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(value, false);
    }

    private final Object value;
    private final boolean isLeft;

    private Either(Object value, boolean isLeft) {
        this.value = value;
        this.isLeft = isLeft;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isRight() {
        return !isLeft;
    }

    public L left() {
        if (isLeft)
            return (L) value;
        else
            throw new IllegalStateException("Tried accessing value as the L type, but was R type");
    }

    public R right() {
        if (!isLeft)
            return (R) value;
        else
            throw new IllegalStateException("Tried accessing value as the R type, but was L type");
    }

    public L leftOrNull() {
        return isLeft ? (L) value : null;
    }

    public R rightOrNull() {
        return !isLeft ? (R) value : null;
    }

    public <V> V cast() {
        return (V) value;
    }

    public <T> T run(Function<L, T> leftFunc, Function<R, T> rightFunc) {
        return isLeft ? leftFunc.apply((L) value) : rightFunc.apply((R) value);
    }

    public <T> T runLeftOrNull(Function<L, T> func) {
        return isLeft ? func.apply((L) value) : null;
    }

    public <T> T runRightOrNull(Function<R, T> func) {
        return !isLeft ? func.apply((R) value) : null;
    }

    public <V, T> T runCasting(Function<V, T> func) {
        return func.apply((V) value);
    }
}

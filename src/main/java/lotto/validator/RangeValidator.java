package lotto.validator;

public interface RangeValidator<T extends Comparable<T>> {

    default boolean isInRange(T target, T startInclusive, T endInclusive) {
        return target.compareTo(startInclusive) >= 0 && target.compareTo(endInclusive) <= 0;
    }
}

package lotto.validator;

import java.util.List;

public interface DuplicateValidator<T> {

    default boolean isDuplicate(final List<T> target) {
        for (int i = 0; i < target.size(); i++) {
            T cur = target.get(i);
            if (isDup(target, cur, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isDup(final List<T> target, final T cur,
        final int curIdx) {
        for (int i = 0; i < target.size(); i++) {
            if (curIdx == i) {
                continue;
            }
            if (target.get(i).equals(cur)) {
                return true;
            }
        }
        return false;
    }
}

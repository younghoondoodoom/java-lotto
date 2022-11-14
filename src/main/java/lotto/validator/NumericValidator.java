package lotto.validator;

public interface NumericValidator {

    default boolean isNumeric(String target) {
        return target.chars().allMatch(Character::isDigit);
    }
}

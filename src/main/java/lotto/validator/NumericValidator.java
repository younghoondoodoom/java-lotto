package lotto.validator;

public interface NumericValidator {

    default boolean isNumeric(String target) {
        return target != null && !target.equals("") && target.chars().allMatch(Character::isDigit);
    }
}

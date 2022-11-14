package lotto.validator.impl;

import java.util.List;
import lotto.validator.integrated.WinningNumberInputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinningNumberValidatorImplTest {

    private final WinningNumberInputValidator validator = new WinningNumberInputValidatorImpl();

    @Test
    public void isNumericFailure() {
        //given
        List<String> wrongTarget = List.of("a", "b");

        //when
        boolean result = validator.validate(wrongTarget);

        //then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void isInRangeFailure() {
        //given
        List<String> wrongTarget1 = List.of("0");
        List<String> wrongTarget2 = List.of("46");

        //when
        boolean result1 = validator.validate(wrongTarget1);
        boolean result2 = validator.validate(wrongTarget2);

        //then
        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isFalse();
    }

    @Test
    public void isValidSizeFailure() {
        //given
        List<String> wrongTarget = List.of("1");

        //when
        boolean result = validator.validate(wrongTarget);

        //then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void isDuplicateFailure() {
        //given
        List<String> wrongTarget1 = List.of("1", "1", "1", "1", "1", "1");
        List<String> wrongTarget2 = List.of("1", "2", "3", "4", "5", "5");

        //when
        boolean result1 = validator.validate(wrongTarget1);
        boolean result2 = validator.validate(wrongTarget2);

        //then
        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isFalse();
    }

    @Test
    public void validateSuccess() {
        //given
        List<String> rightTarget1 = List.of("1", "2", "3", "4", "5", "6");
        List<String> rightTarget2 = List.of("45", "44", "43", "42", "41", "40");

        //when
        boolean result1 = validator.validate(rightTarget1);
        boolean result2 = validator.validate(rightTarget2);

        //then
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
    }
}
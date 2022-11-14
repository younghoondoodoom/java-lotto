package lotto.validator.impl;

import lotto.validator.integrated.PurchaseAmountInputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseAmountInputValidatorImplTest {

    private final PurchaseAmountInputValidator validator = new PurchaseAmountInputValidatorImpl();

    @Test
    public void isNumericFailure() {
        //given
        String wrongTarget = "wrong";

        //when
        boolean result = validator.validate(wrongTarget);

        //then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void isInRangeFailure() {
        //given
        String wrongTarget1 = "0";
        String wrongTarget2 = "9223372036854775808";

        //when
        boolean result1 = validator.validate(wrongTarget1);
        boolean result2 = validator.validate(wrongTarget2);

        //then
        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isFalse();
    }

    @Test
    public void isDividedByThousandFailure() {
        //given
        String wrongTarget1 = "1001";
        String wrongTarget2 = "999";

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
        String rightTarget1 = "1000";
        String rightTarget2 = "9223372036854775000";

        //when
        boolean result1 = validator.validate(rightTarget1);
        boolean result2 = validator.validate(rightTarget2);

        //then
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
    }
}
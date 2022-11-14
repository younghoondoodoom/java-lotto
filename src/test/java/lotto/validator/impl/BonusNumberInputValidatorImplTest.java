package lotto.validator.impl;

import lotto.type.LottoInformation;
import lotto.validator.integrated.BonusNumberInputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BonusNumberInputValidatorImplTest {

    private final BonusNumberInputValidator validator = new BonusNumberInputValidatorImpl();

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
        String wrongTarget1 = String.valueOf(LottoInformation.START.value() - 1);
        String wrongTarget2 = String.valueOf(LottoInformation.END.value() + 1);

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
        String rightTarget1 = String.valueOf(LottoInformation.START.value());
        String rightTarget2 = String.valueOf(LottoInformation.END.value());

        //when
        boolean result1 = validator.validate(rightTarget1);
        boolean result2 = validator.validate(rightTarget2);

        //then
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
    }
}
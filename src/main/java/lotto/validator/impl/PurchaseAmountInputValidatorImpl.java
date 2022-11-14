package lotto.validator.impl;

import java.util.Objects;
import lotto.type.ErrorCode;
import lotto.type.LottoInformation;
import lotto.util.log.LottoLogger;
import lotto.validator.integrated.PurchaseAmountInputValidator;

public class PurchaseAmountInputValidatorImpl implements PurchaseAmountInputValidator {

    @Override
    public boolean validate(String target) {
        if (!LottoLogger.validationWithErrorLog(isNumeric(target), ErrorCode.NON_NUMERIC_VALUE)) {
            return false;
        }
        Long targetNumber = parseTargetToLong(target);
        return LottoLogger.validationWithErrorLog(targetNumber != null, ErrorCode.TOO_BIG_OR_TOO_SMALL_VALUE)
            && LottoLogger.validationWithErrorLog(isInRange(Objects.requireNonNull(targetNumber), 1L, Long.MAX_VALUE),
            ErrorCode.TOO_BIG_OR_TOO_SMALL_VALUE)
            && LottoLogger.validationWithErrorLog(isDividedBySmallestUnit(targetNumber),
            ErrorCode.COULD_NOT_DIVIDE_BY_SMALLEST_UNIT);
    }

    @Override
    public boolean isDividedBySmallestUnit(Long target) {
        return target % LottoInformation.SMALLEST_UNIT.value() == 0;
    }

    private static Long parseTargetToLong(String target) {
        long targetNumber;
        try {
            targetNumber = Long.parseLong(target);
        } catch (NumberFormatException e) {
            return null;
        }
        return targetNumber;
    }
}

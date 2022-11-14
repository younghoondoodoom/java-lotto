package lotto.validator.impl;

import lotto.type.ErrorCode;
import lotto.type.LottoInformation;
import lotto.util.log.LottoLogger;
import lotto.validator.integrated.BonusNumberInputValidator;

public class BonusNumberInputValidatorImpl implements BonusNumberInputValidator {

    @Override
    public boolean validate(String target) {
        if (!LottoLogger.validationWithErrorLog(isNumeric(target), ErrorCode.NON_NUMERIC_VALUE)) {
            return false;
        }
        if (!LottoLogger.validationWithErrorLog(
            isInRange(Integer.parseInt(target), LottoInformation.START.value(), LottoInformation.END.value()),
            ErrorCode.TOO_BIG_OR_TOO_SMALL_VALUE)) {
            return false;
        }
        return true;
    }
}

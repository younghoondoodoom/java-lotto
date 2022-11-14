package lotto.validator.impl;

import java.util.List;
import java.util.stream.Collectors;
import lotto.type.ErrorCode;
import lotto.type.LottoInformation;
import lotto.util.log.LottoLogger;
import lotto.validator.integrated.WinningNumberInputValidator;

public class WinningNumberInputValidatorImpl implements WinningNumberInputValidator {

    @Override
    public boolean validate(List<String> targets) {
        if (validateNumeric(targets)) {
            return false;
        }
        List<Integer> targetNumbers = parseTargetsToInteger(targets);
        if (validateRange(targetNumbers)) {
            return false;
        }
        return LottoLogger.validationWithErrorLog(isValidSize(targetNumbers, LottoInformation.SIZE.value()),
            ErrorCode.LOTTO_SIZE_ERROR)
            && LottoLogger.validationWithErrorLog(isDuplicate(targetNumbers), ErrorCode.DUPLICATE_ERROR);
    }

    private boolean validateRange(List<Integer> targetNumbers) {
        for (Integer targetNumber : targetNumbers) {
            if (!LottoLogger.validationWithErrorLog(
                isInRange(targetNumber, LottoInformation.START.value(), LottoInformation.END.value()),
                ErrorCode.TOO_BIG_OR_TOO_SMALL_VALUE)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateNumeric(List<String> targets) {
        for (String target : targets) {
            if (!LottoLogger.validationWithErrorLog(isNumeric(target), ErrorCode.NON_NUMERIC_VALUE)) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> parseTargetsToInteger(List<String> targets) {
        return targets.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}

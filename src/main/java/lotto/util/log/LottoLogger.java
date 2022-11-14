package lotto.util.log;

import lotto.type.ErrorCode;

public class LottoLogger {
    public static void error(ErrorCode errorCode) {
        System.out.println("[ERROR] " + errorCode.getMessage());
    }
}

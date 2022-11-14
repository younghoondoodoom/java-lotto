package lotto.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lotto.type.LottoStandard;

public class LottoStatistic {

    private final Map<LottoStandard, Integer> resultMap;
    private BigDecimal yield;

    public LottoStatistic() {
        resultMap = new HashMap<>();
        for (LottoStandard lottoStandard : LottoStandard.values()) {
            resultMap.put(lottoStandard, 0);
        }
    }

    public Map<LottoStandard, Integer> getResultMap() {
        return resultMap;
    }

    public BigDecimal getYield() {
        return yield;
    }
}

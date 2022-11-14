package lotto.service.impl;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lotto.domain.Lotto;
import lotto.dto.LottoStatistic;
import lotto.service.LottoService;
import lotto.type.ErrorCode;
import lotto.type.LottoInformation;
import lotto.type.LottoStandard;
import lotto.util.log.LottoLogger;

public class LottoServiceImpl implements LottoService {

    @Override
    public List<Lotto> createLottos(int size) {
        if (size <= 0) {
            LottoLogger.error(ErrorCode.LOTTO_SIZE_ERROR);
            throw new IllegalArgumentException();
        }
        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(createLotto());
        }
        return result;
    }

    @Override
    public LottoStatistic makeStatistic(List<Lotto> lottos, long purchaseAmount, List<Integer> winningNumbers,
        int bonusNumber) {
        LottoStatistic statistic = new LottoStatistic();
        long totalPrize = 0L;
        for (Lotto lotto : lottos) {
            LottoStandard lottoStandard = LottoStandard.judge(lotto, winningNumbers, bonusNumber);
            statistic.addLottoStandard(lottoStandard);
            totalPrize += lottoStandard.getPrize();
        }
        statistic.calculateYield(purchaseAmount, totalPrize);
        return statistic;
    }


    private Lotto createLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LottoInformation.START.value(),
            LottoInformation.END.value(), LottoInformation.SIZE.value());
        numbers.sort(Comparator.naturalOrder());
        return new Lotto(numbers);
    }
}

package lotto.service.impl;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lotto.domain.Lotto;
import lotto.service.LottoService;
import lotto.type.LottoInformation;
import lotto.util.log.LottoLogger;

public class LottoServiceImpl implements LottoService {

    @Override
    public List<Lotto> createLottos(int size) {
        if (size <= 0) {
            LottoLogger.error("Lotto는 최소 1개 이상만 발행할 수 있습니다.");
            throw new IllegalArgumentException();
        }
        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LottoInformation.START.value(),
                LottoInformation.END.value(), LottoInformation.SIZE.value());
            numbers.sort(Comparator.naturalOrder());
            result.add(new Lotto(numbers));
        }
        return result;
    }
}

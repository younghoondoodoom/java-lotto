package lotto.service.impl;

import java.util.List;
import lotto.domain.Lotto;
import lotto.service.LottoService;
import lotto.type.LottoInformation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    private final LottoService lottoService = new LottoServiceImpl();

    @Test
    public void createLottos() {
        //given
        int size = 5;

        //when
        List<Lotto> lottos = lottoService.createLottos(size);

        //then
        Assertions.assertThat(lottos.size()).isEqualTo(size);
        for (Lotto lotto : lottos) {
            lottoInformAssert(lotto);
        }
    }

    private void lottoInformAssert(Lotto lotto) {
        Assertions.assertThat(lotto.getNumbers().size()).isEqualTo(LottoInformation.SIZE.value());
        for (Integer number : lotto.getNumbers()) {
            if (number < LottoInformation.START.value() || number > LottoInformation.END.value()) {
                org.junit.jupiter.api.Assertions.fail();
            }
        }
        for (int i = 0; i < LottoInformation.SIZE.value() - 1; i++) {
            if (lotto.getNumbers().get(i) > lotto.getNumbers().get(i + 1)) {
                org.junit.jupiter.api.Assertions.fail();
            }
        }
    }
}
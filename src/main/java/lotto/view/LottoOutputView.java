package lotto.view;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.dto.LottoStatistic;
import lotto.type.LottoStandard;

public class LottoOutputView {

    public static void outputLottos(final List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public static void outputLottoStatistic(LottoStatistic statistic) {
        printLottoStatisticIntro();
        printLottoStatistic(statistic);
        printLottoStatisticYield(statistic);
    }


    private static void printLottoStatisticIntro() {
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    private static void printLottoStatistic(LottoStatistic statistic) {
        List<LottoStandard> lottoStandards = Arrays.stream(LottoStandard.values())
            .sorted((o1, o2) -> (int) (o1.getPrize() - o2.getPrize()))
            .collect(Collectors.toList());
        printResultMap(statistic, lottoStandards);
    }

    private static void printResultMap(LottoStatistic statistic, List<LottoStandard> lottoStandards) {
        DecimalFormat amountFormatter = new DecimalFormat("###,###");
        for (LottoStandard lottoStandard : lottoStandards) {
            if (lottoStandard.equals(LottoStandard.NOTHING)) {
                continue;
            }
            Integer result = statistic.getResultMap().get(lottoStandard);
            System.out.print(lottoStandard.getMatchCount() + "개 일치");
            if (lottoStandard.equals(LottoStandard.SECOND)) {
                System.out.print(", 보너스 볼 일치");
            }
            System.out.print(" (" + amountFormatter.format(lottoStandard.getPrize()) + "원) - ");
            System.out.print(result + "개");
            System.out.println();
        }
    }

    private static void printLottoStatisticYield(LottoStatistic statistic) {
        System.out.print("총 수익률은 ");
        System.out.print(statistic.getYield() + "%입니다.");
    }
}

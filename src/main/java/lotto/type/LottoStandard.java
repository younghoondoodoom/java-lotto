package lotto.type;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;

public enum LottoStandard {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    NOTHING(0, false, 0L);

    private final int matchCount;
    private final boolean bonus;
    private final long prize;

    LottoStandard(int matchCount, boolean bonus, long prize) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }

    public static LottoStandard judge(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = judgeMatchCount(lotto, winningNumbers);
        boolean bonus = judgeBonus(lotto, bonusNumber);
        return findByMathCountAndBonus(matchCount, bonus);
    }

    public static LottoStandard findByMathCountAndBonus(int matchCount, boolean bonus) {
        if (matchCount == 5) {
            return Arrays.stream(LottoStandard.values())
                .filter(lottoStandard -> lottoStandard.matchCount == matchCount)
                .filter(lottoStandard -> lottoStandard.bonus == bonus)
                .findAny()
                .orElse(NOTHING);
        }
        return Arrays.stream(LottoStandard.values())
            .filter(lottoStandard -> lottoStandard.matchCount == matchCount)
            .findAny()
            .orElse(NOTHING);
    }

    private static int judgeMatchCount(Lotto lotto, List<Integer> winningNumbers) {
        return (int) winningNumbers.stream().filter(winningNumber -> lotto.getNumbers().contains(winningNumber))
            .count();
    }

    private static boolean judgeBonus(Lotto lotto, int bonusNumber) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}

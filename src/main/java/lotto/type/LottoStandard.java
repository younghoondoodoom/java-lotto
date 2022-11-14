package lotto.type;

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
}

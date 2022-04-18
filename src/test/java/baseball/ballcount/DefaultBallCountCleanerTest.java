package baseball.ballcount;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DefaultBallCountCleanerTest {

    BallCountCleaner countCleaner;

    @BeforeEach
    public void init() {
        this.countCleaner = new DefaultBallCountCleaner();
        this.countCleaner.initCleaner();
    }

    @ParameterizedTest
    @DisplayName("기본 Cleaner는 STRIKE 와 BALL 만 남겨둔다.")
    @CsvSource(value = {"4 , 1, 7", "1, 2, 0", "0, 0, 7"})
    public void onlyCleanNothing(int strike, int ball, int nothing) {
        List<BallCount> ballCounts = new ArrayList<>();
        this.addCleanerBallCount(BallCount.STRIKE, strike);
        this.addCleanerBallCount(BallCount.BALL, ball);
        this.addCleanerBallCount(BallCount.NOTHING, nothing);

        int expectedSize = strike + ball;

        this.countCleaner.addBallCounts(ballCounts);

        assertThat(this.countCleaner.cleanedBallCounts().size())
                .isEqualTo(expectedSize);
    }

    @Test
    @DisplayName("기본 클리너는 NOTHING 만 삭제 할 수 있.")
    public void isAddedBallCountTest() {
        assertThat(this.countCleaner.isAddedBallCount(BallCount.STRIKE))
                .isTrue();
        assertThat(this.countCleaner.isAddedBallCount(BallCount.BALL))
                .isTrue();
        assertThat(this.countCleaner.isAddedBallCount(BallCount.NOTHING))
                .isFalse();
    }

    private void addCleanerBallCount(BallCount ballCount, int size) {
        this.countCleaner.addBallCounts(this.generate(ballCount, size));
    }

    private List<BallCount> generate(BallCount target, int size) {
        List<BallCount> counts = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            counts.add(target);
        }
        return counts;
    }
}
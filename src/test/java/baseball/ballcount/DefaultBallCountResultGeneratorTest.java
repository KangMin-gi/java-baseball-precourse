package baseball.ballcount;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DefaultBallCountResultGeneratorTest {

    BallCountResultGenerator generator;


    @BeforeEach
    public void init() {
        this.generator = new DefaultBallCountResultGenerator();
    }

    @ParameterizedTest
    @DisplayName("BallCountList로 String 와 Ball의 수를 반환한다.")
    @CsvSource(value = {"4, 1, 5", "1, 3 ,4", "1, 0, 5"})
    public void generateTest(int strike, int ball, int nothing) {
        List<BallCount> list = new ArrayList<>();
        list.addAll(this.generate(BallCount.STRIKE, strike));
        list.addAll(this.generate(BallCount.BALL, ball));
        list.addAll(this.generate(BallCount.NOTHING, nothing));

        BallCountResult generate = this.generator.generate(new TestCopyCleaner(list));
        assertThat(generate.strikeCount())
                .isEqualTo(strike);
        assertThat(generate.ballCount())
                .isEqualTo(ball);

    }


    private List<BallCount> generate(BallCount target, int size) {
        List<BallCount> counts = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            counts.add(target);
        }
        return counts;
    }

}
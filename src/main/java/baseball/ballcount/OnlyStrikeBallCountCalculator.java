package baseball.ballcount;

import baseball.numbers.BaseballDigitNumber;
import baseball.numbers.BaseballNumbers;
import baseball.util.NullUtils;
import java.util.List;

public class OnlyStrikeBallCountCalculator implements BallCountCalculator<BaseballNumbers> {

    private final BallCountCleaner ballCountCleaner;
    private final BallCountResultGenerator ballCountResultGenerator;

    public OnlyStrikeBallCountCalculator() {
        this(new DefaultBallCountCleaner(), new DefaultBallCountResultGenerator());
    }

    public OnlyStrikeBallCountCalculator(
            BallCountCleaner ballCountCleaner,
            BallCountResultGenerator ballCountResultGenerator) {
        NullUtils.validNullArgument(ballCountCleaner, "BallCountCleaner Can not be null");
        NullUtils.validNullArgument(ballCountResultGenerator, "ballCountResultGenerator Can not be null");
        this.ballCountCleaner = ballCountCleaner;
        this.ballCountResultGenerator = ballCountResultGenerator;
    }

    @Override
    public BallCountResult calculate(BaseballNumbers numbers1, BaseballNumbers numbers2) {
        this.ballCountCleaner.initCleaner();

        List<BaseballDigitNumber> baseballDigitNumbers = numbers2.baseballDigitNumbers();
        for (BaseballDigitNumber baseballDigitNumber : baseballDigitNumbers) {
            List<BallCount> inspectBallCounts = numbers1.inspectBallCounts(baseballDigitNumber);
            this.ballCountCleaner.addBallCounts(inspectBallCounts);
        }
        return this.ballCountResultGenerator.generate(ballCountCleaner);
    }

}

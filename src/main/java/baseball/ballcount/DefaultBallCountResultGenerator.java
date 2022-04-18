package baseball.ballcount;

import java.util.List;

public class DefaultBallCountResultGenerator implements BallCountResultGenerator {

    @Override
    public BallCountResult generate(BallCountCleaner countCleaner) {
        List<BallCount> cleanedBallCounts = countCleaner.cleanedBallCounts();
        int strike = this.counting(cleanedBallCounts, BallCount.STRIKE);
        int ball = this.counting(cleanedBallCounts, BallCount.BALL);
        return new BallCountResult(strike, ball, this.isEnd(strike, countCleaner.originSize()));
    }

    private int counting(List<BallCount> list, BallCount target) {
        int count = 0;
        for (BallCount ballCount : list) {
            count += ballCount.countIfEqual(target);
        }
        return count;
    }

    private boolean isEnd(int strike, int originSize) {
        return strike == originSize;
    }
}

package baseball.ballcount;

import java.util.List;

public class TestCopyCleaner implements BallCountCleaner {

    private List<BallCount> counts;

    public TestCopyCleaner(List<BallCount> counts) {
        this.counts = counts;
    }

    @Override
    public void addBallCounts(List<BallCount> ballCounts) {

    }

    @Override
    public void initCleaner() {

    }

    @Override
    public int originSize() {
        return 0;
    }

    @Override
    public List<BallCount> cleanedBallCounts() {
        return this.counts;
    }

    @Override
    public boolean isAddedBallCount(BallCount ballCount) {
        return false;
    }
}

package baseball.ballcount;

import baseball.util.NullUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultBallCountCleaner implements BallCountCleaner {

    private List<BallCount> ballCounts = new ArrayList<>();
    private int originSize = 0;

    @Override
    public void addBallCounts(List<BallCount> ballCounts) {
        NullUtils.validNullArgument(ballCounts, "Cleaner BallCounts는 Null 일수 없습니다.");
        for (BallCount ballCount : ballCounts) {
            this.addBallCount(ballCount);
        }
        this.initOriginSize(ballCounts);
    }

    private void addBallCount(BallCount ballCount) {
        if (!this.isAddedBallCount(ballCount)) {
            return;
        }
        this.ballCounts.add(ballCount);
    }

    private void initOriginSize(List<BallCount> ballCounts) {
        this.originSize = ballCounts.size();
    }

    @Override
    public boolean isAddedBallCount(BallCount ballCount) {
        if (ballCount == BallCount.NOTHING) {
            return false;
        }
        return true;
    }

    @Override
    public void initCleaner() {
        this.ballCounts = new ArrayList<>();
    }

    @Override
    public List<BallCount> cleanedBallCounts() {
        return Collections.unmodifiableList(this.ballCounts);
    }

    @Override
    public int originSize() {
        return this.originSize;
    }
}

package baseball.ballcount;

import java.util.List;

public interface BallCountCleaner {

    void addBallCounts(List<BallCount> ballCounts);

    void initCleaner();

    List<BallCount> cleanedBallCounts();

    int originSize();

    boolean isAddedBallCount(BallCount ballCount);
}

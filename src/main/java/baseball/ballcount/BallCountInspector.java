package baseball.ballcount;

import java.util.List;

public interface BallCountInspector<T> {

    List<BallCount> inspectBallCounts(T element);

}

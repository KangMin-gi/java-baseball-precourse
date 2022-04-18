package baseball.ballcount;

public enum BallCount {
    STRIKE, BALL, NOTHING;

    public int countIfEqual(BallCount ballCount) {
        if (this == ballCount) {
            return 1;
        }
        return 0;
    }
}

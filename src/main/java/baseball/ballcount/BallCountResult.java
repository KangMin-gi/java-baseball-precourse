package baseball.ballcount;

public class BallCountResult {

    private Integer strikeCount;
    private Integer ballCount;
    private Boolean end;

    public BallCountResult(Integer strikeCount, Integer ballCount, boolean end) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
        this.end = end;
    }

    public Integer strikeCount() {
        return this.strikeCount;
    }

    public Integer ballCount() {
        return this.ballCount;
    }

    public Boolean isEnd() {
        return this.end;
    }
}

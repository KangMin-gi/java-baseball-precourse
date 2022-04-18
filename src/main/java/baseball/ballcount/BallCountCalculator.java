package baseball.ballcount;

public interface BallCountCalculator<T> {

    BallCountResult calculate(T numbers1, T numbers2);

}

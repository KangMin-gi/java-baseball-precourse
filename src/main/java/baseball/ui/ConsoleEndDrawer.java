package baseball.ui;

import baseball.ballcount.BallCountResult;

public class ConsoleEndDrawer implements ResultDrawer {

    @Override
    public void draw(BallCountResult ballCountResult) {
        int strike = ballCountResult.strikeCount();
        System.out.println(strike + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}

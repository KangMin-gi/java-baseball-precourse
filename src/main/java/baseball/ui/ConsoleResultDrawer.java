package baseball.ui;

import baseball.ballcount.BallCountResult;

public class ConsoleResultDrawer extends ConsoleStartDrawer implements ResultDrawer {

    @Override
    public void draw(BallCountResult ballCountResult) {
        String message = ballCountResult.ballCount() + "볼 "
                + ballCountResult.strikeCount() + "스트라이크 ";
        super.draw(message);
    }
}

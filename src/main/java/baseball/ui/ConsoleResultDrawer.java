package baseball.ui;

import baseball.ballcount.BallCountResult;

public class ConsoleResultDrawer extends ConsoleStartDrawer implements ResultDrawer {

    @Override
    public void draw(BallCountResult ballCountResult) {
        int ballCount = ballCountResult.ballCount();
        int strikeCount = ballCountResult.strikeCount();
        String message = "";
        message += this.ballGenerator(ballCount);
        message += this.strikeGenerator(strikeCount);
        if (ballCount == 0 && strikeCount == 0) {
            message = "낫싱";
        }
        super.draw(message);
    }

    private String ballGenerator(int ballCount) {
        if (ballCount == 0) {
            return "";
        }
        return ballCount + "볼 ";
    }

    private String strikeGenerator(int strikeCount) {
        if (strikeCount == 0) {
            return "";
        }
        return strikeCount + "스트라이크 ";
    }
}

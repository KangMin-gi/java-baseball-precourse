package baseball;

import baseball.ballcount.BallCountResult;
import baseball.game.GameManager;
import baseball.game.GameRestartStatus;
import baseball.ui.ConsoleRestartChoiceDraw;
import baseball.ui.ConsoleStartDrawer;
import baseball.ui.ConsoleUiFactory;
import baseball.ui.Drawer;
import baseball.ui.ResultDrawer;

public class Application {
    public static void main(String[] args) {
        GameManager manager = new GameManager();
        manager.start();

        Drawer start = ConsoleUiFactory.requestInput();
        ResultDrawer resultDrawer = ConsoleUiFactory.result();
        ResultDrawer endDrawer = ConsoleUiFactory.end();
        Drawer restartDrawer = ConsoleUiFactory.restartDrawer();

        while (true) {
            start.draw(ConsoleStartDrawer.NUMBER_INPUT_MESSAGE);
            BallCountResult ballCountResult = manager.requestAttackerNumber();
            resultDrawer.draw(ballCountResult);

            if (!manager.isEnd()) {
                continue;
            }

            endDrawer.draw(ballCountResult);
            restartDrawer.draw(ConsoleRestartChoiceDraw.RESTART_MESSAGE);
            GameRestartStatus gameRestartStatus = manager.requestAttackerRestartChoice();
            if (gameRestartStatus == GameRestartStatus.END) {
                break;
            }
            manager.restart();
        }


    }
}

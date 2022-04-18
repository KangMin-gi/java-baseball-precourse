package baseball.ui;

import baseball.ballcount.BallCountResult;

public interface ResultDrawer extends Drawer {

    void draw(BallCountResult ballCountResult);
}

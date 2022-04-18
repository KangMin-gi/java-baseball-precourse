package baseball.ui;

public class ConsoleUiFactory {

    public static Drawer requestInput() {
        return new ConsoleStartDrawer();
    }

    public static ResultDrawer result() {
        return new ConsoleResultDrawer();
    }

    public static ResultDrawer end() {
        return new ConsoleEndDrawer();
    }

    public static Drawer restartDrawer() {
        return new ConsoleRestartChoiceDraw();
    }

}

package baseball.ui;

public interface Drawer {

    default void draw(String message) {
        System.out.println(message);
    }
}

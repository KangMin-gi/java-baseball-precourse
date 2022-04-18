package baseball.game;

import baseball.input.NumberReader;

public class User implements NumberWriter {

    private NumberReader reader;

    public User(NumberReader reader) {
        this.reader = reader;
    }

    public Integer writeNumber() {
        return this.reader.readNumber();
    }

    public void changeWriter(NumberReader reader) {
        this.reader = reader;
    }

}

package baseball.input;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleNumberReader implements NumberReader {

    private StringToInteger stringToInteger = new StringToInteger();

    @Override
    public Integer readNumber() {
        return this.stringToInteger.convert(Console.readLine());
    }
}

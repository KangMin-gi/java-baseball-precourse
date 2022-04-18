package baseball.input;

import baseball.property.BaseballGameProperty;

public class ValidConsoleNumberReader extends ReaderRangeValidator implements NumberReader {

    private final NumberReader reader;

    public ValidConsoleNumberReader(
            BaseballGameProperty property,
            NumberReader reader) {
        super(property);
        this.reader = reader;
    }

    @Override
    public Integer readNumber() {
        Integer convert = this.reader.readNumber();
        super.valid(convert);
        return convert;
    }
}

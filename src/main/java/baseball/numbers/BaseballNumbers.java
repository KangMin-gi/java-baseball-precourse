package baseball.numbers;

import baseball.util.NullUtils;
import java.util.List;

public class BaseballNumbers {

    private final BaseballDigitNumbersConverter baseballDigitNumbersConverter;
    private final Integer originBaseballNumbers;

    public BaseballNumbers(Integer baseballNumbers) {
        this(baseballNumbers, new DefaultBaseballDigitNumbersConverter());
    }

    public BaseballNumbers(
            Integer baseballNumbers,
            BaseballDigitNumbersConverter baseballDigitNumbersConverter) {
        NullUtils.validNullArgument(baseballNumbers, "BaseBall Numbers Cannot Be Null");
        NullUtils.validNullArgument(baseballDigitNumbersConverter, "BaseBall Numbers Cannot Be Null");

        this.originBaseballNumbers = baseballNumbers;
        this.baseballDigitNumbersConverter = baseballDigitNumbersConverter;
    }

    public Integer numberOfDigit() {
        return String.valueOf(this.originBaseballNumbers)
                .length();
    }

    public List<BaseballDigitNumber> baseballDigitNumbers() {
        return this.baseballDigitNumbersConverter.convert(originBaseballNumbers);
    }

    public int intValue() {
        return this.originBaseballNumbers;
    }
}

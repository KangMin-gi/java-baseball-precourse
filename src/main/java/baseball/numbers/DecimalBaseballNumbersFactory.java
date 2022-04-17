package baseball.numbers;

import baseball.numbers.valid.BaseballNumbersValidator;
import baseball.numbers.valid.DefaultBaseballNumberValidator;
import baseball.property.BaseballGameProperty;
import baseball.util.NullUtils;

public class DecimalBaseballNumbersFactory implements BaseballNumbersFactory {

    private BaseballDigitNumbersConverter converter;
    private BaseballNumbersValidator validator;

    public DecimalBaseballNumbersFactory(BaseballGameProperty property) {
        this(new DefaultBaseballDigitNumbersConverter(), new DefaultBaseballNumberValidator(property));
    }

    public DecimalBaseballNumbersFactory(
            BaseballGameProperty property,
            BaseballDigitNumbersConverter converter) {
        this(converter, new DefaultBaseballNumberValidator(property));
    }

    public DecimalBaseballNumbersFactory(BaseballNumbersValidator validator) {
        this(new DefaultBaseballDigitNumbersConverter(), validator);
    }

    public DecimalBaseballNumbersFactory(
            BaseballDigitNumbersConverter converter,
            BaseballNumbersValidator validator) {
        NullUtils.validNullArgument(converter, "BaseBallConverter Can Not Be Null");
        NullUtils.validNullArgument(validator, "validator Can Not Be Null");

        this.converter = converter;
        this.validator = validator;
    }

    @Override
    public BaseballNumbers generate(Integer number) {
        BaseballNumbers baseballNumbers = new BaseballNumbers(number, this.converter);
        this.validator.valid(baseballNumbers);
        return baseballNumbers;
    }
}


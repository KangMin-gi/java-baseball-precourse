package baseball.property;

import baseball.util.NullUtils;

public class BaseballGameProperty {

    private Integer numberOfDigit;
    private Integer allowDigitNumberMin;
    private Integer allowDigitNumberMax;

    public BaseballGameProperty(
            Integer numberOfDigit,
            Integer allowDigitNumberMin,
            Integer allowDigitNumberMax) {
        NullUtils.validNullArgument(numberOfDigit, "BaseballGameProperty.numberOfDigit Can Not Be Null");
        NullUtils.validNullArgument(allowDigitNumberMin, "BaseballGameProperty.allowDigitNumberMin Can Not Be Null");
        NullUtils.validNullArgument(allowDigitNumberMax, "BaseballGameProperty.allowDigitNumberMax Can Not Be Null");
        this.numberOfDigit = numberOfDigit;
        this.allowDigitNumberMin = allowDigitNumberMin;
        this.allowDigitNumberMax = allowDigitNumberMax;
    }

    public Integer getNumberOfDigit() {
        return numberOfDigit;
    }

    public Integer getAllowDigitNumberMin() {
        return allowDigitNumberMin;
    }

    public Integer getAllowDigitNumberMax() {
        return allowDigitNumberMax;
    }

    public Integer minNumberRange() {
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        for (int i = 1; i < numberOfDigit; ++i) {
            builder.append(0);
        }
        String num = builder.toString();
        return Integer.parseInt(num);
    }

    public Integer maxNumberRange() {
        return this.minNumberRange() * 10 - 1;
    }

}

package baseball.numbers.valid;

import baseball.numbers.BaseballDigitNumber;
import baseball.numbers.BaseballNumbers;
import baseball.property.BaseballGameProperty;
import baseball.util.NullUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultBaseballNumberValidator implements BaseballNumbersValidator {

    private final BaseballGameProperty baseballGameProperty;

    public DefaultBaseballNumberValidator(BaseballGameProperty baseballGameProperty) {
        NullUtils.validNullArgument(baseballGameProperty, "BaseballGameProperty Can Not Be Null");
        this.baseballGameProperty = baseballGameProperty;
    }

    @Override
    public void valid(BaseballNumbers baseballNumbers) {
        this.validNumberOfDigit(baseballNumbers);
        this.validUniqueDigitNumber(baseballNumbers);
        this.validDigitNumber(baseballNumbers);
    }

    protected void validUniqueDigitNumber(BaseballNumbers baseballNumbers) {
        List<BaseballDigitNumber> baseballDigitNumbers = baseballNumbers.baseballDigitNumbers();
        if (!this.isUniqueNumberList(baseballDigitNumbers)) {
            throw new IllegalArgumentException("서로 다른 수로 이루어지지 않은 숫자입니다.");
        }
    }

    protected void validNumberOfDigit(BaseballNumbers baseballNumbers) {
        Integer allowNumberOfDigit = this.baseballGameProperty.getNumberOfDigit();
        Integer numberOfDigit = baseballNumbers.numberOfDigit();
        if (!allowNumberOfDigit.equals(numberOfDigit)) {
            throw new IllegalArgumentException("잘못된 길이의 숫자 입니다. expected: " + allowNumberOfDigit
                    + " now : " + baseballNumbers.numberOfDigit());
        }
    }

    protected void validDigitNumber(BaseballNumbers baseballNumbers) {
        int minNumber = this.baseballGameProperty.getAllowDigitNumberMin();
        int maxNumber = this.baseballGameProperty.getAllowDigitNumberMax();
        List<BaseballDigitNumber> baseballDigitNumbers = baseballNumbers.baseballDigitNumbers();
        for (BaseballDigitNumber baseballDigitNumber : baseballDigitNumbers) {
            int number = baseballDigitNumber.number();
            if (number < minNumber || number > maxNumber) {
                throw new IllegalArgumentException("걱 자릿수는 " + minNumber + "~" + maxNumber + " 사이만 허용됩니다.");
            }
        }
    }

    private boolean isUniqueNumberList(List<BaseballDigitNumber> list) {
        Set<Integer> set = new HashSet<>();
        boolean allAdded = true;
        for (BaseballDigitNumber baseballDigitNumber : list) {
            allAdded &= set.add(baseballDigitNumber.number());
        }
        return allAdded;
    }
}

package baseball.input;

import baseball.property.BaseballGameProperty;

public abstract class ReaderRangeValidator implements InputValidator {

    private BaseballGameProperty property;

    public ReaderRangeValidator(BaseballGameProperty property) {
        this.property = property;
    }

    protected int minInclusive() {
        return this.property.minNumberRange();
    }

    protected int maxInclusive() {
        return this.property.maxNumberRange();
    }

    public void valid(Integer validNumber) {
        if (!rangeIn(validNumber)) {
            throw new IllegalArgumentException("잘못된 범위의 숫자입니다. 입력된 숫자 = " + validNumber);
        }
    }

    protected boolean rangeIn(Integer validNumber) {
        return this.minInclusive() <= validNumber
                &&  this.maxInclusive() >= validNumber;
    }

}

package baseball.numbers;

import baseball.ballcount.BallCount;
import baseball.ballcount.BallCountComparable;
import baseball.util.NullUtils;
import java.util.Objects;

public class BaseballDigitNumber implements BallCountComparable<BaseballDigitNumber> {

    private final Integer number;
    private final Integer rightIndex;

    public BaseballDigitNumber(Integer number, Integer rightIndex) {
        NullUtils.validNullArgument(number, "DigitNumber Can Not Be Null");
        NullUtils.validNullArgument(rightIndex, "RightIndex Can Not Be Null");

        this.number = number;
        this.rightIndex = rightIndex;

        this.validDigitNumber();
    }

    public BaseballDigitNumber(Character character, Integer rightIndex) {
        this(Character.getNumericValue(character), rightIndex);
    }

    public Integer number() {
        return this.number;
    }

    @Override
    public BallCount compare(BaseballDigitNumber digitNumber) {
        if (digitNumber.number.equals(this.number)
                && digitNumber.rightIndex.equals(this.rightIndex)) {
            return BallCount.STRIKE;
        }

        if (digitNumber.number.equals(this.number)) {
            return BallCount.BALL;
        }
        return BallCount.NOTHING;
    }

    protected void validDigitNumber() {
        this.validNumber();
        this.validRightIndex();
    }

    private void validRightIndex() {
        if (this.rightIndex < 0) {
            throw new IllegalArgumentException(
                    "rightIndex 는 0 이하일 수 없습니다. 'rightIndex(자릿수) : " + this.rightIndex + "'");
        }
    }

    private void validNumber() {
        if (this.number < 0 || this.number >= 10) {
            throw new IllegalArgumentException("잘못된 자릿수 입니다. number : " + number);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseballDigitNumber that = (BaseballDigitNumber) o;
        return Objects.equals(number, that.number) && Objects.equals(rightIndex, that.rightIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, rightIndex);
    }
}

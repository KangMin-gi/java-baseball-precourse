package baseball.numbers.valid;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.numbers.BaseballNumbers;
import baseball.property.BaseballGameProperty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DefaultBaseballNumberValidatorTest {

    private BaseballGameProperty property;
    private DefaultBaseballNumberValidator validator;

    @ParameterizedTest
    @DisplayName("자릿수의 범위가 설정된 범위를 벗어나면, 오류를 발생한다.")
    @CsvSource(value = {"0xA09, 0, 8", "0701, 0, 6", "106, 1, 5"})
    public void oneToNineTest(int number, int allowMinDigitNumber, int allowMaxDigitNumber) {
        this.initValidator(this.length(number), allowMinDigitNumber, allowMaxDigitNumber);
        BaseballNumbers baseballNumbers = new BaseballNumbers(number);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> validator.validDigitNumber(baseballNumbers))
                .withMessageContainingAll("걱 자릿수는 " + allowMinDigitNumber + "~" + allowMaxDigitNumber + " 사이만 허용됩니다.");
    }

    @ParameterizedTest
    @DisplayName("숫자의 길이가 설정된 길이와 다르면, 오류가 발생한다.")
    @CsvSource(value = {"123, 4", "123456789, 10", "1, 2"})
    public void wrongNumberOfDigit(int number, int numberOfDigitProperty) {
        this.initValidator(numberOfDigitProperty, 0, 9);
        BaseballNumbers baseballNumbers = new BaseballNumbers(number);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> validator.validNumberOfDigit(baseballNumbers))
                .withMessageContaining("잘못된 길이의 숫자 입니다");
    }

    @ParameterizedTest
    @DisplayName("중복된 자릿수가 존재할 수 없다.")
    @ValueSource(ints = {111, 112, 515, 626, 555, 1313131, 2424242, 66})
    public void nnoUniqueNumberThrowError(int noUniqueNumber) {
        this.initValidator(this.length(noUniqueNumber), 0, 9);
        BaseballNumbers baseballNumbers = new BaseballNumbers(noUniqueNumber);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> validator.validUniqueDigitNumber(baseballNumbers))
                .withMessageContaining("서로 다른 수로 이루어지지 않은 숫자입니다.");
    }

    @ParameterizedTest
    @DisplayName("기본 validator는 중복여부, 자릿수, 자릿수의 범위 를 검사해서 통과를 못하면 오류를 발생시킨다.")
    @CsvSource(value = {"123, 4, 1, 9", "109381, 6, 1, 8", "-12345, 5, 1, 9", "11111, 5, 1, 9"})
    public void allFailTest(int number, int length, int min, int max) {
        this.initValidator(length, min, max);
        BaseballNumbers baseballNumbers = new BaseballNumbers(number);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.validator.valid(baseballNumbers));
    }

    @ParameterizedTest
    @DisplayName("기본 validator는 중복여부, 자릿수, 자릿수의 범위 를 검사해서 통과하면 오류를 발생시키지 않는다.")
    @ValueSource(ints = {123, 456, 789, 12345, 98765, 3019})
    public void okTest(int number) {
        this.initValidator(this.length(number), 0, 9);
        BaseballNumbers baseballNumbers = new BaseballNumbers(number);
        assertThatNoException()
                .isThrownBy(() -> this.validator.valid(baseballNumbers));
    }

    private int length(Integer number) {
        return String.valueOf(number).length();
    }

    private void initValidator(int length, int minDigit, int maxDigit) {
        this.property = new BaseballGameProperty(length, minDigit, maxDigit);
        this.validator = new DefaultBaseballNumberValidator(property);
    }

}
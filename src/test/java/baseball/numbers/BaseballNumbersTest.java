package baseball.numbers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BaseballNumbersTest {

    BaseballDigitNumbersConverter converter = new DefaultBaseballDigitNumbersConverter();

    @ParameterizedTest
    @DisplayName("입력된 숫자의 자릿수를 리턴한다.")
    @CsvSource(value = {"312, 3", "1234567891, 10"})
    public void numberOfDigitTest(int number, int expectedSize) {
        BaseballNumbers baseballNumbers = new BaseballNumbers(number, this.converter);
        assertThat(baseballNumbers.numberOfDigit())
                .isEqualTo(expectedSize);
    }

    @ParameterizedTest
    @DisplayName("intValue() 메소드는 입력된 숫자를 그대로 리턴한다.")
    @ValueSource(ints = {123, 444, 321, 3728193, 543829, 3427189})
    public void intValueTest(int number) {
        BaseballNumbers baseballNumbers = new BaseballNumbers(number, this.converter);
        assertThat(baseballNumbers.intValue())
                .isEqualTo(number);
    }

    @Test
    public void listEqualsTest() {
        int number = 1031;
        List<BaseballDigitNumber> list = Arrays.asList(
                new BaseballDigitNumber(1, 3),
                new BaseballDigitNumber(0, 2),
                new BaseballDigitNumber(3, 1),
                new BaseballDigitNumber(1, 0));

        BaseballNumbers numbers = new BaseballNumbers(number, this.converter);
        List<BaseballDigitNumber> baseballDigitNumbers = numbers.baseballDigitNumbers();
        assertThat(baseballDigitNumbers)
                .containsExactlyElementsOf(list);
    }

}
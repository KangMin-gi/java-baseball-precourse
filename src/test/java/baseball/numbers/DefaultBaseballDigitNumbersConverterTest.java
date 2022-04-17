package baseball.numbers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultBaseballDigitNumbersConverterTest {

    DefaultBaseballDigitNumbersConverter converter;

    @BeforeEach
    void init() {
        this.converter = new DefaultBaseballDigitNumbersConverter();
    }

    @Test
    @DisplayName("숫자를, baseballNumbers 리스트로 반환해서 리턴한다.")
    public void convertOkTest() {
        int number = 5025;
        List<BaseballDigitNumber> list = Arrays.asList(
                new BaseballDigitNumber(5, 3),
                new BaseballDigitNumber(0, 2),
                new BaseballDigitNumber(2, 1),
                new BaseballDigitNumber(5, 0));

        List<BaseballDigitNumber> convert = this.converter.convert(number);
        assertThat(convert)
                .containsExactlyElementsOf(list);
    }

    @Test
    @DisplayName("converter로 반환 받은 객체는 불변객체이다.")
    public void convertReturnUnmodifiableList() {
        int number = 5025;
        List<BaseballDigitNumber> convert = this.converter.convert(number);
        assertThatThrownBy(() -> convert.add(new BaseballDigitNumber(1, 1)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("converter Cache 기능 테스트")
    public void convertCacheTest() {
        int number = 5025;
        List<BaseballDigitNumber> convert = this.converter.convert(number);
        List<BaseballDigitNumber> convert1 = this.converter.convert(number);
        List<BaseballDigitNumber> convert2 = this.converter.convert(number);

        assertThat(convert == convert1)
                .isTrue();
        assertThat(convert1 == convert2)
                .isTrue();

        int newNumber = 50505;
        List<BaseballDigitNumber> newConvert = this.converter.convert(newNumber);
        List<BaseballDigitNumber> newConvert1 = this.converter.convert(newNumber);

        assertThat(convert == newConvert)
                .isFalse();

        assertThat(newConvert == newConvert1)
                .isTrue();

    }
}
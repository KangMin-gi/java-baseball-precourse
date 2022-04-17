package baseball.numbers;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BaseballDigitNumberTest {


    @ParameterizedTest
    @DisplayName("숫자가 아닌 자리수는 오류를 발생시킨다.")
    @ValueSource(chars = {'b', 'a', '-', 'x', ' '})
    public void nonIntegerThrowError(char ch) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BaseballDigitNumber(ch, 0))
                .withMessageContaining("잘못된 자릿수 입니다.");

    }

    @ParameterizedTest
    @DisplayName("음수인 rightIndex는 오류를 발생시킨다.")
    @ValueSource(ints = {-1, -3, -5})
    public void negativeRightIndexThrowError(int negativeNumber) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BaseballDigitNumber(1, negativeNumber))
                .withMessageContaining("rightIndex 는 0 이하일 수 없습니다.");
    }

}
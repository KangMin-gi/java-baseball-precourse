package baseball.ballcount;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.numbers.BaseballNumbers;
import baseball.numbers.BaseballNumbersFactory;
import baseball.numbers.DecimalBaseballNumbersFactory;
import baseball.property.BaseballGameProperty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OnlyStrikeBallCountCalculatorTest {

    BallCountCalculator<BaseballNumbers> ballCountCalculator;
    BaseballNumbersFactory factory;

    @ParameterizedTest
    @DisplayName("Calculator는 두 수를 비교하여, STRIKE, BALL의 갯수를 알려준다")
    @CsvSource(value = {"123, 123, 3, 0", "123, 312, 0, 3", "1234, 5678, 0, 0", "213, 236, 1, 1"})
    public void calculateTest(int number1, int number2, int strikeExpected, int ballExpected) {
        this.init(String.valueOf(number1).length());

        BaseballNumbers num1 = this.factory.generate(number1);
        BaseballNumbers num2 = this.factory.generate(number2);
        BallCountResult calculate = this.ballCountCalculator.calculate(num1, num2);
        assertThat(calculate.strikeCount())
                .isEqualTo(strikeExpected);
        assertThat(calculate.ballCount())
                .isEqualTo(ballExpected);
    }


    public void init(int length) {
        this.ballCountCalculator = new OnlyStrikeBallCountCalculator();
        this.factory = new DecimalBaseballNumbersFactory(new BaseballGameProperty(length, 0, 9));
    }


}
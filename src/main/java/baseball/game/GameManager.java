package baseball.game;

import baseball.ballcount.BallCountCalculator;
import baseball.ballcount.BallCountResult;
import baseball.ballcount.OnlyStrikeBallCountCalculator;
import baseball.input.ConsoleNumberReader;
import baseball.input.NumberReader;
import baseball.input.ValidConsoleNumberReader;
import baseball.input.ValidRandomNumberReader;
import baseball.numbers.BaseballNumbers;
import baseball.numbers.BaseballNumbersFactory;
import baseball.numbers.DecimalBaseballNumbersFactory;
import baseball.property.BaseballGameProperty;
import baseball.util.NullUtils;

public class GameManager {

    private BaseballGameProperty property;
    private BallCountCalculator<BaseballNumbers> calculator;
    private BaseballNumbersFactory factory;

    private User attacker;
    private User defender;
    private BaseballNumbers defenderNumbers;
    private boolean end;

    private NumberReader defaultConsoleNumberReader = new ConsoleNumberReader();

    public GameManager() {
        this(new BaseballGameProperty(3, 1, 9));
    }

    public GameManager(BaseballGameProperty property) {
        NullUtils.validNullArgument(property, "GameProperty Can not be null");
        this.property = property;
        this.init();
    }

    private void init() {
        this.attacker = new User(new ValidConsoleNumberReader(this.property, new ConsoleNumberReader()));
        this.defender = new User(new ValidRandomNumberReader(this.property));
        this.factory = new DecimalBaseballNumbersFactory(this.property);
        this.calculator = new OnlyStrikeBallCountCalculator();

        this.end = false;
    }

    public void restart() {
        this.init();
        this.start();
    }

    public void start() {
        this.initDefenderNumber();
    }

    public BallCountResult requestAttackerNumber() {
        Integer attackerWrite = this.attacker.writeNumber();
        BaseballNumbers attackerNumbers = this.generateNumbers(attackerWrite);
        BallCountResult result = this.calculator.calculate(attackerNumbers, this.defenderNumbers);
        this.end = result.isEnd();
        return result;
    }

    public boolean isEnd() {
        return this.end;
    }

    public GameRestartStatus requestAttackerRestartChoice() {
        Integer inputNumber = this.defaultConsoleNumberReader.readNumber();
        if (GameRestartStatus.START.isEqualStatusNumber(inputNumber)) {
            return GameRestartStatus.START;
        }
        if (GameRestartStatus.END.isEqualStatusNumber(inputNumber)) {
            return GameRestartStatus.END;
        }
        throw new IllegalArgumentException("재 시작 값을 잘못 선택 했습니다. 게임을 종료합니다.");
    }

    private void initDefenderNumber() {
        this.defenderNumbers = this.generateNumbers(defender.writeNumber());
    }

    private BaseballNumbers generateNumbers(int numbers) {
        return this.factory.generate(numbers);
    }


}

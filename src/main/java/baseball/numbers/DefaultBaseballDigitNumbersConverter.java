package baseball.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultBaseballDigitNumbersConverter implements BaseballDigitNumbersConverter {

    private List<BaseballDigitNumber> cached;
    private Integer lastNumber;

    @Override
    public List<BaseballDigitNumber> convert(int number) {
        if (this.isCachedNumber(number)) {
            return cached;
        }
        this.initLastNumber(number);
        this.initCached(number);
        return cached;
    }

    protected void initLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    protected void initCached(int number) {
        List<BaseballDigitNumber> response = new ArrayList<>();
        String numberStr = String.valueOf(number);
        for (int digitIndex = 0; digitIndex < numberStr.length(); ++digitIndex) {
            Character digitChar = numberStr.charAt(digitIndex);
            int rightIndex = numberStr.length() - 1 - digitIndex;
            BaseballDigitNumber baseballDigitNumber = new BaseballDigitNumber(digitChar, rightIndex);
            response.add(baseballDigitNumber);
        }
        this.cached = Collections.unmodifiableList(response);
    }

    private boolean isCachedNumber(int number) {
        if (this.lastNumber == null || number != this.lastNumber) {
            return false;
        }
        if (this.cached == null || this.cached.isEmpty()) {
            return false;
        }
        return true;
    }
}

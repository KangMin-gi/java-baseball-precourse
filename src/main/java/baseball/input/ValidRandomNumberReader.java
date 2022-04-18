package baseball.input;

import baseball.property.BaseballGameProperty;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidRandomNumberReader extends ReaderRangeValidator implements NumberReader {


    public ValidRandomNumberReader(BaseballGameProperty property) {
        super(property);
    }

    @Override
    public Integer readNumber() {
        int minInclusive = super.minInclusive();
        int maxInclusive = super.maxInclusive();
        int pick = Randoms.pickNumberInRange(minInclusive, maxInclusive);
        while (!isAllowNumber(pick)) {
            pick = Randoms.pickNumberInRange(minInclusive, maxInclusive);
        }
        int randomNumber = pick;

        super.valid(randomNumber);

        return randomNumber;
    }

    private boolean isAllowNumber(int number) {
        List<Integer> list = this.generate(number);
        if (list.contains(0)) {
            return false;
        }
        if (isDistinct(list)) {
            return false;
        }
        return true;
    }

    private boolean isDistinct(List<Integer> list) {
        Set<Integer> distinct = new HashSet<>(list);
        return list.size() != distinct.size();
    }

    private List<Integer> generate(Integer number) {
        String s = String.valueOf(number);
        List<Integer> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            list.add(Character.getNumericValue(chars[i]));
        }
        return list;
    }

}

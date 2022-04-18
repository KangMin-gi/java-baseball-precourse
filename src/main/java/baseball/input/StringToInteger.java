package baseball.input;

public class StringToInteger {

    public Integer convert(final String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("'" + s + "' 는 숫자로 변환할 수 없습니다.");
        }
    }
}

package numbers;

import java.lang.reflect.InvocationTargetException;

public class WholeNumber extends Number {
    private static final long ZERO = 0;

    public WholeNumber(long value) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        super(value);
        if (!isWholeNumber(VALUE)) {
            throw new NotAWholeNumber();
        }
    }

    public WholeNumber(String value) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        super(value);
        if (!isWholeNumber(VALUE)) {
            throw new NotAWholeNumber();
        }
    }
    @Override
    public WholeNumber next() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        return new WholeNumber(VALUE + 1);
    }

    public boolean isZero() { return VALUE == ZERO;}

    public static boolean isWholeNumber(String stringValue) {
        if (stringValue == null) {
            return false;
        }
        int length = stringValue.length();
        if (length == 0) {
            return false;
        }
        if (stringValue.charAt(0) == '-') {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char c = stringValue.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean isWholeNumber(UserInput userInput) {
        return isWholeNumber(userInput.VALUE());
    }

    public static boolean isWholeNumber (long longValue) {
        return longValue >= 0;
    }
}

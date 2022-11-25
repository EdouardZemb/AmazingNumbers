package numbers;

import java.lang.reflect.InvocationTargetException;

public class NaturalNumber extends Number {

    public NaturalNumber(long value) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotANaturalNumber {
        super(value);
        if (!isNaturalNumber(VALUE)) {
            throw new NotANaturalNumber();
        }
    }

    public NaturalNumber(String stringValue) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotANaturalNumber {
        super(stringValue);
        if (!isNaturalNumber(VALUE)) {
            throw new NotANaturalNumber();
        }
    }

    @Override
    public NaturalNumber next() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotANaturalNumber {
        return new NaturalNumber(VALUE + 1);
    }

    public static boolean isNaturalNumber(String stringValue) {
        return WholeNumber.isWholeNumber(stringValue) &&
                stringValue != "0";
    }

    public static boolean isNaturalNumber(long longValue) {
        return longValue > 0;
    }

}

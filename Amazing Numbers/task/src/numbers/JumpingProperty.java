package numbers;

import java.lang.reflect.InvocationTargetException;

public class JumpingProperty extends Property {

    JumpingProperty() {
        super(PropertyType.JUMPING);
    }

    @Override
    public boolean isAPropertyOf(Number number) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (int i = 1; i < number.NUMBER_OF_DIGITS; i++) {
            int currentDigit = number.getDigitAt(i);
            int nextDigit = number.getDigitAt(i + 1);
            if (nextDigit != currentDigit + 1 && nextDigit != currentDigit - 1) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof JumpingProperty;
    }
}

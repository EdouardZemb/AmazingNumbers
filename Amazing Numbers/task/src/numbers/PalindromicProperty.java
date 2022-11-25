package numbers;

import java.lang.reflect.InvocationTargetException;

public class PalindromicProperty extends Property {
    PalindromicProperty() {
        super(PropertyType.PALINDROMIC);
    }

    @Override
    public boolean isAPropertyOf(Number number) {
        long numberValue = number.VALUE, temp = numberValue, remainder, sum = 0;
        while (numberValue > 0) {
            remainder = numberValue % 10;
            sum = (sum * 10) + remainder;
            numberValue = numberValue / 10;
        }
        if (temp == sum) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PalindromicProperty;
    }
}

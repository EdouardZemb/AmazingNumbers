package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SpyProperty extends Property {

    SpyProperty() {
        super(PropertyType.SPY, List.of(PropertyType.DUCK));
    }

    @Override
    public boolean isAPropertyOf(Number number) {
        if (new DuckProperty().isAPropertyOf(number)) {
            return false;
        }
        long lastDigit, product=1, sum=0, numberValue = number.VALUE;
        while (numberValue > 0)
        {
            lastDigit = numberValue % 10;
            sum += lastDigit;
            product = product * lastDigit;
            numberValue = numberValue / 10;
        }
        return sum == product;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SpyProperty;
    }
}

package numbers;

import java.lang.reflect.InvocationTargetException;

public class BuzzProperty extends Property {
    BuzzProperty() {
        super(PropertyType.BUZZ);
    }

    @Override
    public boolean isAPropertyOf(Number number) {
        return number.VALUE % 7 == 0 || number.VALUE % 10 == 7;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof BuzzProperty;
    }
}

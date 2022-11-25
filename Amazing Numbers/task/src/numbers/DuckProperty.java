package numbers;

import java.util.List;

public class DuckProperty extends Property {
    DuckProperty() {
        super(PropertyType.DUCK, List.of(PropertyType.SPY));
    }

    @Override
    public boolean isAPropertyOf(Number number) {
        long numberValue = number.VALUE;
        while(numberValue != 0) {
            if (numberValue % 10 == 0) {
                return true;
            }
            numberValue = numberValue / 10;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DuckProperty;
    }
}

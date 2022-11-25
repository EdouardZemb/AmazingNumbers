package numbers;

import java.util.List;

public class OddProperty extends Property {
    OddProperty() {
        super(PropertyType.ODD, List.of(PropertyType.EVEN));
    }

    @Override
    public boolean isAPropertyOf(Number number) {
        return number.VALUE % 2 != 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OddProperty;
    }
}

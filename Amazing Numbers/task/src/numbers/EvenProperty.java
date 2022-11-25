package numbers;

import java.util.List;

public class EvenProperty extends Property {
    EvenProperty() {
        super(PropertyType.EVEN, List.of(PropertyType.ODD));
    }

    public boolean isAPropertyOf(Number number) {
        return number.VALUE % 2 == 0;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof EvenProperty;
    }
}

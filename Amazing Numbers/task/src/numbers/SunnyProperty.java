package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SunnyProperty extends Property {
    SunnyProperty() {
        super(PropertyType.SUNNY, List.of(PropertyType.SQUARE));
    }

    @Override
    public boolean isAPropertyOf(Number number) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return new SquareProperty().isAPropertyOf(number.VALUE + 1);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SunnyProperty;
    }
}

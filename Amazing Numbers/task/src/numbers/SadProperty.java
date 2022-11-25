package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SadProperty extends Property {

    SadProperty() {
        super(PropertyType.SAD, List.of(PropertyType.HAPPY));
    }

    @Override
    public boolean isAPropertyOf(Number number) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        HappyProperty happyProperty = new HappyProperty();
        boolean isHappy = happyProperty.isAPropertyOf(number);
        return !isHappy;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SadProperty;
    }
}

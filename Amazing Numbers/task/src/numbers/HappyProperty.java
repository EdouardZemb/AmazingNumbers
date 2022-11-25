package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class HappyProperty extends Property {

    HappyProperty() {
        super(PropertyType.HAPPY, List.of(PropertyType.SAD));
    }

    @Override
    public boolean isAPropertyOf(Number number) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        List<Number> historicNumbers = new ArrayList<>();
        Number decomposedNumber = number;
         do {
             historicNumbers.add(decomposedNumber);
             long sumDigits = 0;
             for (Integer digit : decomposedNumber.DIGITS) {
                sumDigits += Math.pow(digit, 2);
             }
             decomposedNumber = new WholeNumber(sumDigits);
        } while (!historicNumbersContains(decomposedNumber, historicNumbers) && decomposedNumber.VALUE != 1);
        return decomposedNumber.VALUE == 1;
    }

    private boolean historicNumbersContains(Number number, List<Number> historicNumbers) {
        for (Number historicNumber : historicNumbers) {
            if (historicNumber.VALUE == number.VALUE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HappyProperty;
    }
}

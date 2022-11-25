package numbers;

public class GapfulProperty extends Property {

    GapfulProperty() {
            super(PropertyType.GAPFUL);
        }

    @Override
    public boolean isAPropertyOf(Number number) {
        if (number.VALUE < 100) {
            return false;
        }
        int firstDigit = number.getFirstDigit();
        int lastDigit = number.getLastDigit();
        int concatenation = firstDigit * 10 + lastDigit;
        return number.VALUE % concatenation == 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GapfulProperty;
    }
}


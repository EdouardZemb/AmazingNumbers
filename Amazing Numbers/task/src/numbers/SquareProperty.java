package numbers;

import java.util.List;

public class SquareProperty extends Property {
    SquareProperty() {
        super(PropertyType.SQUARE, List.of(PropertyType.SUNNY));
    }

    @Override
    public boolean isAPropertyOf(Number number) {
        int roundedSquareRoute = (int) Math.sqrt(number.VALUE);
        return (long) roundedSquareRoute * roundedSquareRoute == number.VALUE;
    }

    public boolean isAPropertyOf(long number) {
        int roundedSquareRoute = (int) Math.sqrt(number);
        return (long) roundedSquareRoute * roundedSquareRoute == number;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SquareProperty;
    }
}

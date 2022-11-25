package numbers;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static numbers.ListProperties.instantiateProperty;

public abstract class Number {
    public final long VALUE;

    public final int NUMBER_OF_DIGITS;

    public ListProperties getListProperties() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        findAllProperties();
        return LIST_PROPERTIES;
    }

    public final ListProperties LIST_PROPERTIES;

    public final List<Integer> DIGITS = new ArrayList<>();

    public Number(long value) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        this.VALUE = value;
        this.NUMBER_OF_DIGITS = setNumberOfDigits();
        this.LIST_PROPERTIES = new ListProperties(this);
        setDigits();
    }

    private void setDigits() {
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            DIGITS.add(getDigitAt(i + 1));
        }
    }

    public void findAllProperties() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        if (LIST_PROPERTIES.isEmpty()) {
            LIST_PROPERTIES.findAllProperties();
        }
    }

    private int setNumberOfDigits() {
        long value = VALUE;
        int length = 0;
        while (value > 0) {
                length++;
                value /= 10;
        }
        return length;
    }

    public Number(String value) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        this(Long.valueOf(value));
    }

    public String value() { return String.valueOf(VALUE); }

    public abstract Number next() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotANaturalNumber, NotAWholeNumber;

    public boolean hasProperty(PropertyType propertyType) {
        return LIST_PROPERTIES.isPropertyTypeInProperties(propertyType);
    }

    public boolean hasProperties(List<PropertyType> propertyTypeList) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NotAWholeNumber {
        for (PropertyType propertyType : propertyTypeList) {
            Property instance = instantiateProperty(propertyType);
            if (!instance.isAPropertyOf(this)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasProperties(List<PropertyType> propertyTypeList, List<PropertyType> excludedPropertyTypeList) throws NotAWholeNumber, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (excludedPropertyTypeList.isEmpty()) {
            return hasProperties(propertyTypeList);
        }
        if (hasProperties(propertyTypeList)) {
            for (PropertyType excludedPropertyType : excludedPropertyTypeList) {
                Property instance = instantiateProperty(excludedPropertyType);
                if (instance.isAPropertyOf(this)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int getFirstDigit() {
        return (int) (VALUE / Math.pow(10, NUMBER_OF_DIGITS - 1));
    }

    public int getDigitAt(int position ) {
        int exponent = NUMBER_OF_DIGITS - position;
        if (NUMBER_OF_DIGITS > 10 && exponent < 8) {
            BigDecimal powerOfTen = BigDecimal.TEN.pow(exponent);
            BigDecimal value = new BigDecimal(VALUE);
            return getLastDigitOf(value.divide(powerOfTen));
        }
        double powerOfTen = Math.pow(10, exponent);
        double reduction = VALUE / powerOfTen;
        return getLastDigitOf(reduction);
    }

    private int getLastDigitOf(double value) {
        return (int) (value % 10L);
    }

    private int getLastDigitOf(BigDecimal value) {
        return value.remainder(BigDecimal.TEN).intValue();
    }


    public int getLastDigit() {
        if (NUMBER_OF_DIGITS > 10) {
            return getLastDigitOf(new BigDecimal(VALUE));
        }
        return getLastDigitOf(VALUE);
    }
}

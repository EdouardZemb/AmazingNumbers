package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class Property {
    public final PropertyType PROPERTY_TYPE;
    public final List<PropertyType> EXCLUDED_PROPERTY_TYPES = new ArrayList<>();

    Property(PropertyType propertyType) {
        this.PROPERTY_TYPE = propertyType;
    }
    Property(PropertyType propertyType, List<PropertyType> excludedPropertyTypes) {
        this.PROPERTY_TYPE = propertyType;
        this.EXCLUDED_PROPERTY_TYPES.addAll(excludedPropertyTypes);
    }

    public abstract boolean isAPropertyOf(Number number) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber;

    public boolean isAnExcludedPropertyOf(PropertyType propertyType) {
        return EXCLUDED_PROPERTY_TYPES.contains(propertyType);
    }

    public boolean excludeOneOf(List<PropertyType> propertyTypeList) {
        for (PropertyType propertyType : propertyTypeList) {
            if (EXCLUDED_PROPERTY_TYPES.contains(propertyType)) {
                return true;
            }
        }
        return false;
    }

    public WholeNumber next(WholeNumber number) throws NotAWholeNumber, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        WholeNumber nextPropertyNumber = number.next();
        while (!isAPropertyOf(nextPropertyNumber)) {
            nextPropertyNumber = nextPropertyNumber.next();
        }
        return nextPropertyNumber;
    }
}

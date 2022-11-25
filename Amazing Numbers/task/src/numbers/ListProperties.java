package numbers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class ListProperties {
    private final Number INITIAL_NUMBER;

    private final List<Property> properties = new ArrayList<>();

    public final List<PropertyType> excludedPropertyTypes = new ArrayList<>();

    ListProperties(Number number) {
        INITIAL_NUMBER = number;
    }

    public void findAllProperties() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        initializeProperties();
    }

    private void initializeProperties() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NotAWholeNumber {
        for (PropertyType propertyType : PropertyType.values()) {
            Property instance = instantiateProperty(propertyType);
            if (instance.isAPropertyOf(INITIAL_NUMBER)) {
                properties.add(instance);
                addExcludedPropertyTypes(instance);
            }
        }
    }

    private void addExcludedPropertyTypes(Property instance) {
        excludedPropertyTypes.addAll(instance.EXCLUDED_PROPERTY_TYPES);
    }

    public static Property instantiateProperty(PropertyType propertyType) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<Property> property = (Class<Property>) Class.forName("numbers." + propertyType.PROPERTY_TYPE_CLASS_NAME);
        Constructor<Property> constructor = property.getDeclaredConstructor();
        return constructor.newInstance();
    }

    public String toMultilinesString() {
        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder);
        for (PropertyType propertyType : PropertyType.values()) {
            formatter.format(
                    "%15s: %b %n",
                    propertyType.name().toLowerCase(),
                    isPropertyTypeInProperties(propertyType));
        }

        return stringBuilder.toString();
    }

    public String toInlineString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < properties.size(); i++) {
            stringBuilder.append(properties.get(i).PROPERTY_TYPE.name().toLowerCase());
            if (i != properties.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    boolean isPropertyTypeInProperties(PropertyType propertyType) {
        for (Property property : properties) {
            if (property != null && property.PROPERTY_TYPE == propertyType) return true;
        }
        return false;
    }

    public boolean arePropertyTypesInProperties(List<PropertyType> propertyTypes) {
        for (PropertyType propertyType : propertyTypes) {
            if (!isPropertyTypeInProperties(propertyType)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return properties.isEmpty();
    }
}

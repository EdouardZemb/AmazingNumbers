package numbers;

public enum PropertyType {
    BUZZ(),
    DUCK(),
    EVEN(),
    GAPFUL(),
    HAPPY(),
    JUMPING(),
    ODD(),
    PALINDROMIC(),
    SAD(),
    SPY(),
    SQUARE(),
    SUNNY();

    public final String PROPERTY_TYPE_CLASS_NAME;

    public static int totalPropertyTypes = countTotalPropertyTypes();

    PropertyType() {
        this.PROPERTY_TYPE_CLASS_NAME = formatPropertyTypeClassName();
    }

    @Override
    public String toString() {
        return "Property{" +
                "PROPERTY_TYPE_CLASS_NAME='" + PROPERTY_TYPE_CLASS_NAME + '\'' +
                '}';
    }

    private String formatPropertyTypeClassName() {
        StringBuilder stringBuilder = new StringBuilder(this.name().toLowerCase());
        stringBuilder.replace(0, 1, String.valueOf(stringBuilder.charAt(0)).toUpperCase());
        return stringBuilder.append("Property").toString();
    }

    private static int countTotalPropertyTypes() {
        int total = 0;
        for (PropertyType propertyType : PropertyType.values()) {
            total++;
        }
        return total;
    }

    public static String PropertyTypeListToString() {
        StringBuilder stringBuilder = new StringBuilder();
        PropertyType[] values = PropertyType.values();
        for (int i = 0; i < values.length; i++) {
            PropertyType propertyType = values[i];
            stringBuilder.append(propertyType.name());
            if (i != values.length - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}

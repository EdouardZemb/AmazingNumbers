package numbers;

public class Filter {
    public final PropertyType PROPERTY_TYPE;

    public final boolean isToBeExcluded;

    public Filter(String filterToString) throws InvalidFilterName {
        filterToString = filterToString.toUpperCase();
        if (filterToString.charAt(0) == '-') {
            isToBeExcluded = true;
            filterToString = filterToString.substring(1);
        } else {
            isToBeExcluded = false;
        }
        try {
            PROPERTY_TYPE = PropertyType.valueOf(filterToString);
        } catch (Exception exception) {
            throw new InvalidFilterName(filterToString);
        }
    }
}

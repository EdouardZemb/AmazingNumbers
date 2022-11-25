package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ListFilters {

    public List<Filter> filters = new ArrayList<>();

    public List<Filter> excludedFilters = new ArrayList<>();
    private final List<String> INVALID_FILTER_NAMES = new ArrayList<>();

    public List<PropertyType> getPropertyTypeList() {
        return PROPERTY_TYPE_LIST;
    }
    private final List<PropertyType> PROPERTY_TYPE_LIST = new ArrayList<>();

    public List<PropertyType> getExcludedPropertyTypeList() {
        return EXCLUDED_PROPERTY_TYPES;
    }

    private final List<PropertyType> EXCLUDED_PROPERTY_TYPES = new ArrayList<>();
    public List<Property> getFilterProperties() {
        return FILTER_PROPERTIES;
    }
    private final List<Property> FILTER_PROPERTIES = new ArrayList<>();

    private final List<Property> EXCLUDED_FILTER_PROPERTIES = new ArrayList<>();
    public ListFilters(List<UserInput> userInputList) throws InvalidFilterNames, InvalidFilterName, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, MutuallyExclusiveProperties {
        for (UserInput userInput : userInputList) {
            Filter nextFilter = initializeFilter(userInput);
            if (nextFilter == null) continue;
            if (nextFilter.isToBeExcluded) {
                updateExcludedFilterLists(nextFilter);
            } else {
                updateFilterLists(nextFilter);
            }
        }
        if (!INVALID_FILTER_NAMES.isEmpty()) {
            throw new InvalidFilterNames(INVALID_FILTER_NAMES);
        }
        for (PropertyType propertyType : PROPERTY_TYPE_LIST) {
            Property filterProperty = ListProperties.instantiateProperty(propertyType);
            FILTER_PROPERTIES.add(filterProperty);
        }
        List<PropertyType> mutuallyExclusiveProperties = new ArrayList<>();

        for (Property filterProperty : FILTER_PROPERTIES) {
            if (filterProperty.excludeOneOf(PROPERTY_TYPE_LIST)) {
                mutuallyExclusiveProperties.add(filterProperty.PROPERTY_TYPE);
            }
        }
        if (!mutuallyExclusiveProperties.isEmpty()) {
            throw new MutuallyExclusiveProperties(mutuallyExclusiveProperties);
        }
        for (PropertyType propertyType : EXCLUDED_PROPERTY_TYPES) {
            Property filterProperty = ListProperties.instantiateProperty(propertyType);
            EXCLUDED_FILTER_PROPERTIES.add(filterProperty);
        }
        List<PropertyType> mutuallyExclusiveExcludedProperties = new ArrayList<>();

        for (Property filterProperty : EXCLUDED_FILTER_PROPERTIES) {
            if (!(filterProperty instanceof SquareProperty) &&
                    !(filterProperty instanceof SunnyProperty) &&
                    filterProperty.excludeOneOf(EXCLUDED_PROPERTY_TYPES)) {
                mutuallyExclusiveExcludedProperties.add(filterProperty.PROPERTY_TYPE);
            }
        }
        if (!mutuallyExclusiveExcludedProperties.isEmpty()) {
            throw new MutuallyExclusiveProperties(mutuallyExclusiveProperties, mutuallyExclusiveExcludedProperties);
        }
        for (Property filterProperty : EXCLUDED_FILTER_PROPERTIES) {
            if (FILTER_PROPERTIES.contains(filterProperty)) {
                mutuallyExclusiveProperties.add(filterProperty.PROPERTY_TYPE);
                mutuallyExclusiveExcludedProperties.add(filterProperty.PROPERTY_TYPE);
            }
        }
        if (!mutuallyExclusiveProperties.isEmpty() || !mutuallyExclusiveExcludedProperties.isEmpty()) {
            throw new MutuallyExclusiveProperties(mutuallyExclusiveProperties, mutuallyExclusiveExcludedProperties);
        }
    }

    private void updateFilterLists(Filter filter) {
        updateLists(filter, filters, PROPERTY_TYPE_LIST);
    }
    private void updateExcludedFilterLists(Filter filter) {
        updateLists(filter, excludedFilters, EXCLUDED_PROPERTY_TYPES);
    }

    private void updateLists(Filter filter, List<Filter> filters, List<PropertyType> propertyTypes) {
        if (!filters.contains(filter)) {
            filters.add(filter);
            propertyTypes.add(filter.PROPERTY_TYPE);
        }
    }

    private Filter initializeFilter(UserInput userInput) throws InvalidFilterName {
        Filter nextFilter;
        try {
            nextFilter = new Filter(userInput.VALUE());
        } catch (Exception exception) {
            if (exception instanceof InvalidFilterName) {
                INVALID_FILTER_NAMES.add(((InvalidFilterName) exception).INVALID_FILTER_NAME);
                return null;
            } else {
                throw exception;
            }
        }
        return nextFilter;
    }

}

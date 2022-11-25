package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RequestBuilder {
    RequestParameters requestParameters;

    public void runRequest() throws NotANaturalNumber, InvalidFilterNames, LeaveApp, NotAWholeNumber, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvalidFilterName, MutuallyExclusiveProperties {
        try {
            requestParameters = new RequestParameters(new ListUserInputs().fetchUserInputs());
            if (requestParameters.hasListFilters()) {
                new RequestFilteredListNumbersRange(requestParameters).print();
            } else if (requestParameters.hasListLength()) {
                new RequestListNumbersRange(requestParameters).print();
            } else {
                new RequestNumberProperties(requestParameters).print();
            }
        } catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder();
            if (exception instanceof NotAWholeNumber) {
                stringBuilder.append("The first parameter should be a natural number or zero.");
            } else if (exception instanceof NotANaturalNumber) {
                stringBuilder.append("The second parameter should be a natural number.");
            } else if (exception instanceof InvalidFilterNames) {
                List<String> invalidFilterNames = ((InvalidFilterNames) exception).INVALID_FILTER_NAMES;
                stringBuilder = buildInvalidFilterNamesErrorMessage(invalidFilterNames);
            } else if (exception instanceof MutuallyExclusiveProperties) {
                List<PropertyType> mutuallyExclusiveProperties = ((MutuallyExclusiveProperties) exception).mutuallyExclusiveProperties;
                List<PropertyType> mutuallyExclusiveExcludedProperties = ((MutuallyExclusiveProperties) exception).mutuallyExclusiveExcludedProperties;
                stringBuilder = buildMutuallyExclusivePropertiesErrorMessage(mutuallyExclusiveProperties, mutuallyExclusiveExcludedProperties);
            } else {
                throw exception;
            }
            System.out.println(stringBuilder);
        }
        runRequest();
    }

    private StringBuilder buildMutuallyExclusivePropertiesErrorMessage(List<PropertyType> mutuallyExclusiveProperties, List<PropertyType> mutuallyExclusiveExcludedProperties) {
        StringBuilder stringBuilder = new StringBuilder("The request contains mutually exclusive properties: [");
        for (int i = 0; i < mutuallyExclusiveProperties.size(); i++) {
            PropertyType mutuallyExclusiveProperty = mutuallyExclusiveProperties.get(i);
            stringBuilder.append(mutuallyExclusiveProperty.name());
            if (i != mutuallyExclusiveProperties.size() - 1 || !mutuallyExclusiveExcludedProperties.isEmpty()) {
                stringBuilder.append(", ");
            }
        }
        for (int i = 0; i < mutuallyExclusiveExcludedProperties.size(); i++) {
            PropertyType mutuallyExclusiveExcludedProperty = mutuallyExclusiveExcludedProperties.get(i);
            stringBuilder.append("-" + mutuallyExclusiveExcludedProperty.name());
            if (i != mutuallyExclusiveExcludedProperties.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder
                .append("]\n")
                .append("There are no numbers with these properties.");
        return stringBuilder;
    }

    private StringBuilder buildInvalidFilterNamesErrorMessage(List<String> invalidFilterNames) {
        StringBuilder stringBuilder = new StringBuilder();
        if (invalidFilterNames.size() == 1) {
            stringBuilder.append("The property [").append(invalidFilterNames.get(0)).append("] is wrong.");
        } else {
            stringBuilder.append("The properties [");
            for (int i = 0; i < invalidFilterNames.size(); i++) {
                String invalidFilterName = invalidFilterNames.get(i);
                stringBuilder.append(invalidFilterName);
                if (i != invalidFilterNames.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("] are wrong.");
        }
        stringBuilder
                .append("\nAvailable properties: [")
                .append(PropertyType.PropertyTypeListToString())
                .append("]");
        return stringBuilder;
    }

    public void printInstructions() {
        System.out.println(
                """
                        Supported requests:
                        - enter a natural number to know its properties;
                        - enter two natural numbers to obtain the properties of the list:
                          * the first parameter represents a starting number;
                          * the second parameter shows how many consecutive numbers are to be processed;
                        - two natural numbers and two properties to search for;
                        - a property preceded by minus must not be present in numbers;
                        - separate the parameters with one space;
                        - enter 0 to exit.
                        """);
    }
}

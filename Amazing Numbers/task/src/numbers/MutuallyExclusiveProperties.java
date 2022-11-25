package numbers;

import java.util.ArrayList;
import java.util.List;

public class MutuallyExclusiveProperties extends Exception {
    List<PropertyType> mutuallyExclusiveProperties = new ArrayList<>();

    List<PropertyType> mutuallyExclusiveExcludedProperties = new ArrayList<>();


    public MutuallyExclusiveProperties(List<PropertyType> mutuallyExclusiveProperties) {
        this.mutuallyExclusiveProperties.addAll(mutuallyExclusiveProperties);
    }

    public MutuallyExclusiveProperties(List<PropertyType> mutuallyExclusiveProperties, List<PropertyType> mutuallyExclusiveExcludedProperties) {
        this(mutuallyExclusiveProperties);
        this.mutuallyExclusiveExcludedProperties = mutuallyExclusiveExcludedProperties;
    }
}

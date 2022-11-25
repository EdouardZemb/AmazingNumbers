package numbers;

import java.util.List;

public class InvalidFilterNames extends Exception {
    public final List<String> INVALID_FILTER_NAMES;

    public InvalidFilterNames(List<String> invalidFilterNames) {
        INVALID_FILTER_NAMES = invalidFilterNames;
    }
}

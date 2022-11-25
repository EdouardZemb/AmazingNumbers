package numbers;

import java.util.List;

public class InvalidFilterName extends Exception {
    final String INVALID_FILTER_NAME;

    public InvalidFilterName(String invalidFilterName) {
        INVALID_FILTER_NAME = invalidFilterName;
    }
}

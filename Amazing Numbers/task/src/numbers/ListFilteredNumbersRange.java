package numbers;

import java.lang.reflect.InvocationTargetException;

public class ListFilteredNumbersRange extends ListNumbers{

    private final ListFilters LIST_FILTERS;

    public ListFilteredNumbersRange(RequestParameters requestParameters) throws NotAWholeNumber, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        super(requestParameters.getListLength(), requestParameters.getWholeNumber());
        LIST_FILTERS = requestParameters.getListFilters();
        initializeWholeNumberList();
    }

    @Override
    void initializeWholeNumberList() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        WholeNumber nextWholeNumber = initialWholeNumber;
        int i = 0;
        while (i < LIST_LENGTH.VALUE) {
            if (nextWholeNumber.hasProperties(
                    LIST_FILTERS.getPropertyTypeList(),
                    LIST_FILTERS.getExcludedPropertyTypeList())
            ) {
                    addNumberToList(nextWholeNumber);
                    i++;
            }
            nextWholeNumber = getNextValidWholeNumber(nextWholeNumber);
        }
    }

    private void addNumberToList(WholeNumber nextWholeNumber) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        nextWholeNumber.findAllProperties();
        wholeNumberList.add(nextWholeNumber);
    }

    private WholeNumber getNextValidWholeNumber(WholeNumber nextWholeNumber) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        if (LIST_FILTERS.getFilterProperties().isEmpty() || LIST_FILTERS.getExcludedPropertyTypeList().isEmpty()) {
            return nextWholeNumber.next();
        }
        boolean hasAllProperties = false;
        while (!hasAllProperties) {
            for (Property filterProperty : LIST_FILTERS.getFilterProperties()) {
                nextWholeNumber = filterProperty.next(nextWholeNumber);
                if (nextWholeNumber.hasProperties(
                        LIST_FILTERS.getPropertyTypeList(),
                        LIST_FILTERS.getExcludedPropertyTypeList())
                ) {
                    hasAllProperties = true;
                    break;
                }
            }
        }
        return  nextWholeNumber;
    }
}

package numbers;

import java.lang.reflect.InvocationTargetException;

public class RequestFilteredListNumbersRange extends Request {

    public final NaturalNumber LIST_LENGTH;

    public final ListFilteredNumbersRange LIST_FILTERED_NUMBER_RANGE;

    public RequestFilteredListNumbersRange(RequestParameters requestParameters) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        super(requestParameters);
        LIST_LENGTH = requestParameters.getListLength();
        LIST_FILTERED_NUMBER_RANGE = new ListFilteredNumbersRange(requestParameters);
    }

    @Override
    void print() {
        for (WholeNumber wholeNumber : LIST_FILTERED_NUMBER_RANGE.wholeNumberList) {
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(wholeNumber.VALUE))
                    .append(" is ")
                    .append(wholeNumber.LIST_PROPERTIES.toInlineString());
            System.out.println(stringBuilder);
        }
    }
}

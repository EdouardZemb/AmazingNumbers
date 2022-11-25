package numbers;

import java.lang.reflect.InvocationTargetException;

public class RequestListNumbersRange extends Request{

    public final NaturalNumber LIST_LENGTH;

    public final ListNumbersRange LIST_NUMBER_RANGE;
    RequestListNumbersRange(RequestParameters requestParameters) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        super(requestParameters);
        LIST_LENGTH = requestParameters.getListLength();
        LIST_NUMBER_RANGE = new ListNumbersRange(LIST_LENGTH, NUMBER);
    }

    @Override
    void print() {
        for (WholeNumber wholeNumber : LIST_NUMBER_RANGE.wholeNumberList) {
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(wholeNumber.VALUE))
                    .append(" is ")
                    .append(wholeNumber.LIST_PROPERTIES.toInlineString());
            System.out.println(stringBuilder);
        }
    }
}

package numbers;

import java.lang.reflect.InvocationTargetException;

public class ListNumbersRange extends ListNumbers{
    public ListNumbersRange(NaturalNumber listLength, WholeNumber initialWholeNumber) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        super(listLength, initialWholeNumber);
        initializeWholeNumberList();
    }

    @Override
    void initializeWholeNumberList() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        WholeNumber nextWholeNumber = initialWholeNumber;
        for (int i = 0; i < LIST_LENGTH.VALUE; i++) {
            nextWholeNumber.findAllProperties();
            wholeNumberList.add(nextWholeNumber);
            nextWholeNumber = nextWholeNumber.next();
        }
    }
}

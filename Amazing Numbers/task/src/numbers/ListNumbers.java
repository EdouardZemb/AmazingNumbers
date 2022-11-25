package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class ListNumbers {

    public final NaturalNumber LIST_LENGTH;

    protected List<WholeNumber> wholeNumberList = new ArrayList<>();

    protected final WholeNumber initialWholeNumber;

    public ListNumbers(NaturalNumber listLength, WholeNumber initialWholeNumber) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        this.LIST_LENGTH = listLength;
        this.initialWholeNumber = initialWholeNumber;
    }
    abstract void initializeWholeNumberList() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber;

    public List<WholeNumber> getWholeNumberList() {
        return wholeNumberList;
    }
}

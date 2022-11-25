package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RequestParameters {
    private final ListUserInputs LIST_USER_INPUTS;
    private WholeNumber wholeNumber;

    public WholeNumber getWholeNumber() {
        return wholeNumber;
    }

    public NaturalNumber getListLength() {
        return listLength;
    }

    public ListFilters getListFilters() {
        return listFilters;
    }

    private NaturalNumber listLength;

    private ListFilters listFilters;


    public RequestParameters(ListUserInputs listUserInputs) throws NotANaturalNumber, InvalidFilterNames, LeaveApp, NotAWholeNumber, InvalidFilterName, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, MutuallyExclusiveProperties {
        LIST_USER_INPUTS = listUserInputs;
        initializeRequestParameters();
    }
    private void initializeRequestParameters() throws NotANaturalNumber, InvalidFilterNames, LeaveApp, NotAWholeNumber, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvalidFilterName, MutuallyExclusiveProperties {
        initializeWholeNumber();
        if (LIST_USER_INPUTS.hasListLength()) {
            initializeListLength();
        }
        if (LIST_USER_INPUTS.hasFilters()) {
            initializeListFilters();
        }
    }

    private void initializeListFilters() throws InvalidFilterNames, InvalidFilterName, MutuallyExclusiveProperties, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<UserInput> userInputList = LIST_USER_INPUTS.getUserInputList();
        userInputList.remove(0);
        userInputList.remove(0);
        listFilters = new ListFilters(userInputList);
    }

    private void initializeListLength() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotANaturalNumber {
        UserInput listLengthUserInput = LIST_USER_INPUTS.get(1);
        if (NaturalNumber.isNaturalNumber(listLengthUserInput.VALUE())) {
            listLength = new NaturalNumber(listLengthUserInput.VALUE());
        } else {
            throw new NotANaturalNumber();
        }
    }

    private void initializeWholeNumber() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber, LeaveApp {
        UserInput wholeNumberUserInput = LIST_USER_INPUTS.get(0);
        if(WholeNumber.isWholeNumber(wholeNumberUserInput)) {
            wholeNumber = new WholeNumber(wholeNumberUserInput.VALUE());
        } else {
            throw new NotAWholeNumber();
        }
        if (wholeNumber.isZero()) {
            throw new LeaveApp();
        }
    }

    public boolean hasListFilters() {
        return listFilters != null;
    }

    public boolean hasListLength() {
        return listLength != null;
    }

    public boolean hasWholeNumber() {
        return wholeNumber != null;
    }
}

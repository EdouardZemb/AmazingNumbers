package numbers;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotANaturalNumber, NotAWholeNumber, InvalidFilterNames, LeaveApp, InvalidFilterName, MutuallyExclusiveProperties {
        new NumbersApp().bootstrap();
    }
}

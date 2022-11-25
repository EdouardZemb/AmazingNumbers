package numbers;

import java.lang.reflect.InvocationTargetException;

public class NumbersApp {

    public void bootstrap() throws NotANaturalNumber, InvalidFilterNames, LeaveApp, NotAWholeNumber, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvalidFilterName, MutuallyExclusiveProperties {
        welcomeUser();
        run();
    }

    private static void run() throws NotANaturalNumber, InvalidFilterNames, LeaveApp, NotAWholeNumber, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvalidFilterName, MutuallyExclusiveProperties {
        RequestBuilder requestBuilder = new RequestBuilder();
        try {
            requestBuilder.printInstructions();
            requestBuilder.runRequest();
        } catch (Exception exception) {
            if (exception instanceof EmptyListUserInputs) {
                run();
            }
            if (exception instanceof LeaveApp) {
                System.out.println("Goodbye!");
            } else {
                throw exception;
            }
        }
    }

    private void welcomeUser() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }
}

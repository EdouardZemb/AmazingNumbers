package numbers;

import java.lang.reflect.InvocationTargetException;

public class RequestNumberProperties extends Request {
    RequestNumberProperties(RequestParameters requestParameters) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NotAWholeNumber {
        super(requestParameters);
        NUMBER.findAllProperties();
    }

    @Override
    void print() {
        System.out.printf("Properties of %d %n%s%n",
                NUMBER.VALUE,
                NUMBER.LIST_PROPERTIES.toMultilinesString());
    }

}

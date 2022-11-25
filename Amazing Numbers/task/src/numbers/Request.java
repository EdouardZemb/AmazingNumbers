package numbers;

public abstract class Request {

    public final WholeNumber NUMBER;

    Request(RequestParameters requestParameters) {
        NUMBER = requestParameters.getWholeNumber();
    }

    abstract void print();
}

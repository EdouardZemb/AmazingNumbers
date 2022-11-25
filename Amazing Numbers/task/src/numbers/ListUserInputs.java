package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListUserInputs {

    private List<UserInput> userInputList = new ArrayList<>();

    public ListUserInputs fetchUserInputs() {
        askForUserInput();
        Scanner sr = new Scanner(System.in);
        for (String s : sr.nextLine().split(" ")) {
            userInputList.add(new UserInput(s));
        }
        if (isEmpty()) {
            throw new EmptyListUserInputs();
        }
        return this;
    }

    public List<UserInput> getUserInputList() {
        return userInputList;
    }

    public int size() {
        return userInputList.size();
    }

    public boolean isEmpty() {
        return userInputList.isEmpty();
    }

    public UserInput get(int index) {
        return userInputList.get(index);
    }

    public boolean hasListLength() {
        return size() > 1;
    }

    public boolean hasFilters() {
        return size() > 2;
    }

    public void askForUserInput() {
        System.out.print("Enter a request: ");
    }

}

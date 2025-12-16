package davisantx;

import java.util.ArrayList;


public class Main {
    static ArrayList<Operation> operations = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            Operation operation = new Operation(false);

            if(!operation.startNewOperation()) break;

            operations.add(operation);
        }
    }
}
package davisantx;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static ArrayList<Operation> operations = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            Operation operation = new Operation();

            boolean startNewOperation = operation.startNewOperation();

            if(!startNewOperation) break;

            operations.add(operation);

        }
    }
}
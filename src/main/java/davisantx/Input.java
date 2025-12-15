package davisantx;

import java.util.Scanner;

public class Input {
    public String value;
    public int lastReadValuePosition;
    public int newLastReadValuePosition;
    public boolean nextExpectedValueIsANumber;
    public Expression expression;

    public Input(Expression expression) {
        this.value = "";
        this.lastReadValuePosition = 0;
        this.newLastReadValuePosition = 0;
        this.nextExpectedValueIsANumber = true;
        this.expression = expression;
    }

    public boolean startNewInputLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String input = scanner.nextLine();

        value = input.replaceAll("\\s+", "");

        if(value.equalsIgnoreCase("exit")) {
            scanner.close();
            return false;
        }

        inputScannerToAddValuesOnExpression();

        return true;
    }

    public void inputScannerToAddValuesOnExpression() {
        while ((lastReadValuePosition != value.length())) {
            newLastReadValuePosition = getNewReadValuePositionToVerifyValidNumbers();

            if (verifyIfInputIsVoid()) {
                Operation.getResultOperation();
                break;
            }

            if (nextExpectedValueIsANumber) {
                nextExpectedValueIsANumber = false;

                Integer number = convertCharactersOnANumber();

                if(number == null) {
                   Operation.getResultOperation();
                   break;
                }

                expression.numbers().add(number);
            }else {
                nextExpectedValueIsANumber = true;

                Character operationOnString = convertOperationOnString();

                if(operationOnString == null) {
                    Operation.getResultOperation();
                    break;
                }

                Operations operation = Operation.getOperationFromChar(operationOnString);

                expression.operations().add(operation);
            }
        }
    }

    public boolean verifyIfInputIsVoid() {
        return lastReadValuePosition != newLastReadValuePosition;
    }

    public Character convertOperationOnString() {
        if(isValidInput(value.charAt(lastReadValuePosition), Operation.validOperators)) {
//          Operations operation = Operation.getOperationFromChar(value.charAt(lastReadValuePosition));
            char operation = value.charAt(lastReadValuePosition);
            ++lastReadValuePosition;
            return operation;
        }
        return null;
    }

    public Integer convertCharactersOnANumber() {
        if (verifyIfInputIsVoid()) return null;

        String numbersOnString = "";

        for (int i = 0; i < (newLastReadValuePosition - lastReadValuePosition); ++i) {
            char numberOnChar = value.charAt(lastReadValuePosition + i);
            String numberOnString = String.valueOf(numberOnChar);
            numbersOnString = numbersOnString.concat(numberOnString);
        }

        lastReadValuePosition = newLastReadValuePosition;
        return Integer.parseInt(numbersOnString);
    }

    public boolean isValidInput(char rawInputCharacter, String[] validCharacters) {
        for (String validCharacter : validCharacters) {
            if (String.valueOf(rawInputCharacter).equals(validCharacter)) {
                return true;
            }
        }
        return false;
    }

    public int getNewReadValuePositionToVerifyValidNumbers() {
        while (lastReadValuePosition < value.length() && isValidInput(value.charAt(lastReadValuePosition), Operation.validNumbers)) {
            lastReadValuePosition++;
        }
        return lastReadValuePosition;
    }
}

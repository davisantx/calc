package davisantx;

import java.util.Scanner;

public class InputReader {
    public String value;
    public Expression expression;
    public Scanner scanner;
    public int lastReadValuePosition;
    public int newLastReadValuePosition;
    public boolean nextExpectedValueIsANumber;

    public InputReader(String value, Expression expression) {
        this.value = value;
        this.expression = expression;
        this.scanner = new Scanner(System.in);
        this.lastReadValuePosition = 0;
        this.newLastReadValuePosition = 0;
        this.nextExpectedValueIsANumber = true;
    }

    public boolean startRead() {
        String input = scanner.nextLine();
        value = input.replaceAll("\\s+", "");

        readExpression();

        return verifyIfIsExit();
    }

    private void readExpression() {
        while ((lastReadValuePosition != value.length())) {
            newLastReadValuePosition = getNewReadValuePositionToVerifyValidNumbers();  // ✅ Sem parâmetro

            if (nextExpectedValueIsANumber) {
                nextExpectedValueIsANumber = false;

                Double number = convertCharactersOnANumber();

                if(number == null) {
                    break;
                }

                expression.numbers().add(number);
            } else {
                nextExpectedValueIsANumber = true;

                Character operationOnString = convertOperationOnString();

                if(operationOnString == null) {
                    break;
                }

                Operations operation = Operations.getOperationFromChar(operationOnString);

                expression.operations().add(operation);
            }
        }

    }

    public Character convertOperationOnString() {
        if(isValidInput(value.charAt(lastReadValuePosition), Operation.validOperators)) {
            Character operation = value.charAt(lastReadValuePosition);
            ++lastReadValuePosition;
            return operation;
        }
        return null;
    }

    public Double convertCharactersOnANumber() {
        if (verifyIfInputIsVoid()) return null;

        String numbersOnString = "";

        for (int i = 0; i < (newLastReadValuePosition - lastReadValuePosition); ++i) {
            char numberOnChar = value.charAt(lastReadValuePosition + i);
            String numberOnString = String.valueOf(numberOnChar);

            numbersOnString = numbersOnString.concat(numberOnString);
        }

        lastReadValuePosition = newLastReadValuePosition;
        return Double.parseDouble(numbersOnString);
    }

    public void reset() {
        this.lastReadValuePosition = 0;
        this.newLastReadValuePosition = 0;
        this.nextExpectedValueIsANumber = true;
    }

    public boolean verifyIfInputIsVoid() {
        return lastReadValuePosition == newLastReadValuePosition;
    }

    public boolean verifyIfIsExit() {
        if(value.equalsIgnoreCase("exit")) {
            scanner.close();
            return false;
        }
        return true;
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
        int position = lastReadValuePosition;

        while (position < value.length() && isValidInput(value.charAt(position), Operation.validNumbers)) {
            position++;
        }

        return position;
    }
}

package io.github.davisantx;

import java.util.ArrayList;

public class Operation {
    private final Input input;
    private final Expression expression;
    private boolean debugMode;

    public static final String[] validOperators = {"+", "-", "*", "/", "^"};
    public static final String[] validNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};

    public Operation() {
        this.expression = new Expression(new ArrayList<>(), new ArrayList<>());
        this.input = new Input(expression);
        this.debugMode = false;
    }

    public boolean getDebugMode() {return debugMode;}

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean startNewOperation() {
        if(!input.startNewInputLine()) return false;
        System.out.println(getResultOperation());
        return true;
    }

    public void clearValues() {
        expression.numbers().clear();
        expression.operations().clear();
    }

    private Double getResultOperation() {
        Double result = 0.0;

        if (!expression.numbers().isEmpty()) {
            result = expression.numbers().getFirst();
        }

        if(debugMode) {
            System.out.println("Numbers: " + expression.numbers());
            System.out.println("Operations: " + expression.operations());
        }

        for (int i = 0; i < expression.numbers().size() - 1; ++i) {
            switch (expression.operations().get(i)) {
                case SUM:
                    result = result + expression.numbers().get(i + 1);
                    break;
                case SUB:
                    result = result - expression.numbers().get(i + 1);
                    break;
                case PRO:
                    result = result * expression.numbers().get(i + 1);
                    break;
                case DIV:
                    if((expression.numbers().get(i + 1) == (0.0))) {
                        System.out.println("Error: x / 0");
                        result = 0.0;
                    } else {
                        result = result / expression.numbers().get(i + 1);
                    }
                    break;
                case EXP:
                    result = Math.pow(result, expression.numbers().get(i + 1));
                    break;
            }
        }
        return result;
    }
}

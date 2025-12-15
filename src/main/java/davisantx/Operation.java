package davisantx;

import java.util.ArrayList;

public class Operation {
    public Input input;
    private Expression expression;

    public static final String[] validOperators = {"+", "-", "*", "/"};
    public static final String[] validNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public Operation() {
        this.expression = new Expression(new ArrayList<>(), new ArrayList<>());
        this.input = new Input(expression);
    }

    public boolean startNewOperation() {
        if(!input.startNewInputLine()) {
            return false;
        }
        return true;
    }

    public void clearValues() {
        expression.numbers().clear();
        expression.operations().clear();
    }

    static public int getResultOperation() {
        int result = 0;
        for (int i = 0; i < expression.numbers().size() - 1; ++i) {
            switch (expression.operations().get(i)) {
                case SUM:
                    result = expression.numbers().get(i) + expression.numbers().get(i + 1);
                    break;
                case SUB:
                    result = (expression.numbers().get(i) - expression.numbers().get(i + 1));
                    break;
                case PRO:
                    result = expression.numbers().get(i) * expression.numbers().get(i + 1);
                    break;
                case DIV:
                    result = expression.numbers().get(i) / expression.numbers().get(i + 1);
                    break;
                case EXP:
                    Double pow = Math.pow(expression.numbers().get(i), expression.numbers().get(i + 1));
                    result = pow.intValue();
                    break;
            }
        }
        return result;
    }

    static public Operations getOperationFromChar(char operator) {
        switch(operator) {
            case '+': return Operations.SUM;
            case '-': return Operations.SUB;
            case '*': return Operations.PRO;
            case '/': return Operations.DIV;
            case '^': return Operations.EXP;
            default: return null;
        }
    }
}

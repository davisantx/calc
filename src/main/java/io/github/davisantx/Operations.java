package io.github.davisantx;

public enum Operations {
    SUM("+"),
    SUB("-"),
    PRO("*"),
    DIV("/"),
    EXP("^");

    private String operationSymbol;

    Operations(String operationSymbol) {
        this.operationSymbol = operationSymbol;
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

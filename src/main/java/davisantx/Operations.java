package davisantx;

public enum Operations {
    SUM("+"),
    SUB("-"),
    PRO("*"),
    DIV("/"),
    EXP("^");

    private String operationSimbol;

    Operations(String operationSimbol) {
        this.operationSimbol = operationSimbol;
    }

}

package io.github.davisantx;

public class Input {
    private final InputReader inputReader;

    public Input(Expression expression) {
        this.inputReader = new InputReader(expression);
    }

    public boolean startNewInputLine() {
        System.out.print("> ");

        return inputReader.startRead();
    }
}

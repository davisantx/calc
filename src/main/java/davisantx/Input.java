package davisantx;

public class Input {
    public String value;
    public InputReader inputReader;
    public Expression expression;

    public Input(Expression expression) {
        this.value = "";
        this.expression = expression;
        this.inputReader = new InputReader(value, this.expression);
    }

    public boolean startNewInputLine() {
        System.out.print("> ");

        return inputReader.startRead();
    }
}

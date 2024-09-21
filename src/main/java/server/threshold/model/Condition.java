package server.threshold.model;

public enum Condition {
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL_TO(">="),
    EQUAL_TO("="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL_TO("<=");
    private final String symbol;

    Condition(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}

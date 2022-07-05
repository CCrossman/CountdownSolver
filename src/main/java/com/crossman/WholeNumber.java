package com.crossman;

/**
 * Exists without equals() or hashCode() to
 * allow duplicates of values in CountdownMathSolver.
 */
public class WholeNumber {
    private final int value;

    public WholeNumber(int value) {
        this.value = value;
        if (value < 0) {
            throw new IllegalArgumentException("value must be > 0");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}

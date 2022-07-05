package com.crossman;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

public enum Operator {
    ADD((a,b) -> Optional.of(a + b)),
    SUBTRACT((a,b) -> a > b ? Optional.of(a - b) : Optional.empty()),
    MULTIPLY((a,b) -> Optional.of(a * b)),
    DIVIDE((a,b) -> a % b == 0 ? Optional.of(a / b) : Optional.empty());

    private final BiFunction<Integer, Integer, Optional<Integer>> operation;

    private Operator(BiFunction<Integer,Integer,Optional<Integer>> operation) {
        this.operation = Objects.requireNonNull(operation);
    }

    public Optional<Integer> apply(Integer a, Integer b) {
        if (a == null || b == null) {
            return Optional.empty();
        }
        return operation.apply(a, b);
    }

    public Optional<Integer> applyOptional(Optional<Integer> a, Optional<Integer> b) {
        if (a.isEmpty() || b.isEmpty()) {
            return Optional.empty();
        }
        return operation.apply(a.get(), b.get());
    }
}

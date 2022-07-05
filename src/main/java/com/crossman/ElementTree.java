package com.crossman;

import java.util.Optional;

public class ElementTree extends Tree<Element> {
    public ElementTree() {
        super();
    }

    public Optional<Integer> getNumericValue() {
        return _getNumericValue(getRoot());
    }

    private Optional<Integer> _getNumericValue(Node<Element> curr) {
        if (curr == null) {
            return Optional.empty();
        }
        if (curr.getValue().isNumber()) {
            return Optional.of(curr.getValue().getNumber().getValue());
        }
        Operator operator = curr.getValue().getOperator();
        return operator.applyOptional(_getNumericValue(curr.getLeft()), _getNumericValue(curr.getRight()));
    }
}

package com.crossman;

import java.util.Objects;

public class Solution {
    private final Tree<Element> calculation;
    private final int result;
    private final int target;

    public Solution(Tree<Element> calculation, int result, int target) {
        this.calculation = Objects.requireNonNull(calculation);
        this.result = result;
        this.target = target;
    }

    public Tree<Element> getCalculation() {
        return calculation;
    }

    public int getResult() {
        return result;
    }

    public int getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return calculation.equals(solution.calculation) && target == solution.target && result == solution.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(calculation, result, target);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "calculation=" + prettyPrint(calculation) +
                ", result=" + result +
                ", target=" + target +
                '}';
    }

    public static String prettyPrint(Tree<Element> curr) {
        return _prettyPrint(curr.getRoot());
    }

    private static String _prettyPrint(Tree.Node<Element> curr) {
        if (curr == null) {
            return null;
        }
        if (curr.getValue().isNumber()) {
            return curr.getValue().getNumber().getValue() + "";
        }
        return "(" + _prettyPrint(curr.getLeft()) + " " + curr.getValue().getOperator().name() + " " + _prettyPrint(curr.getRight()) + ")";
    }
}

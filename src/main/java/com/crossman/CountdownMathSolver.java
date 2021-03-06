package com.crossman;

import java.util.*;
import java.util.stream.Collectors;

public class CountdownMathSolver {
    private List<Integer> numbers;
    private Integer target;
    private Integer scoringRange;

    public static void main(String[] args) {
        CountdownMathSolver solver = new CountdownMathSolver();
        solver.setNumbers(25, 50, 10, 6, 3, 7);
        solver.setTarget(984);
        solver.setScoringRange(0);
        Set<Solution> solutions = solver.solve();
        solutions.stream().sorted(Comparator.comparingInt(s -> Math.abs(s.getResult() - s.getTarget()))).forEach(System.out::println);
    }

    public void setScoringRange(int scoringRange) {
        this.scoringRange = scoringRange;
    }

    public Set<Solution> solve() {
        Set<Solution> solutions = new HashSet<>();
        _solve(solutions,numbers.stream().map(wn -> toElementTree(Element.ofNumber(wn))).collect(Collectors.toCollection(ArrayList::new)));
        return solutions;
    }

    private static ElementTree toElementTree(Element el) {
        ElementTree elementTree = new ElementTree();
        elementTree.setRoot(new Tree.Node<>(el));
        return elementTree;
    }

    private void _solve(Set<Solution> solutions, List<ElementTree> numbers) {
        for (int i = 0; i < numbers.size(); ++i) {
            for (int j = 0; j < numbers.size(); ++j) {
                if (i == j) {
                    continue;
                }
                ElementTree left = numbers.get(i);
                ElementTree right = numbers.get(j);

                List<ElementTree> newNumbers = new ArrayList<>(numbers);
                newNumbers.remove(left);
                newNumbers.remove(right);

                for (Operator operator : Operator.values()) {
                    Optional<Integer> result = operator.applyOptional(left.getNumericValue(), right.getNumericValue());
                    if (result.isPresent()) {
                        Tree.Node<Element> node = new Tree.Node<>(Element.ofOperator(operator));
                        node.setLeft(left.getRoot());
                        node.setRight(right.getRoot());

                        ElementTree tree = new ElementTree();
                        tree.setRoot(node);

                        if (Math.abs(target - result.get()) <= scoringRange) {
                            solutions.add(new Solution(tree, result.get(), target));
                        }

                        newNumbers.add(tree);

                        _solve(solutions, newNumbers);

                        newNumbers.remove(tree);
                    }
                }
            }
        }
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public void setNumbers(Integer... numbers) {
        this.numbers = Arrays.stream(numbers).collect(Collectors.toList());
    }
}

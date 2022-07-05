package com.crossman;

import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountdownMathSolver {
    private List<WholeNumber> numbers;
    private Integer target;
    private final Map<String, BiFunction<Integer,Integer, Optional<Integer>>> operators = Map.of(
        "+", (a, b) -> Optional.of(a + b),
        "-", (a, b) -> a > b ? Optional.of(a - b) : Optional.empty(),
        "x", (a, b) -> Optional.of(a * b),
        "/", (a, b) -> a % b == 0 ? Optional.of(a / b) : Optional.empty()
    );

    public static void main(String[] args) {
        CountdownMathSolver solver = new CountdownMathSolver();
        solver.setNumbers(50, 10, 8, 6, 4, 3);
        solver.setTarget(150);
        solver.solve();
    }

    public void solve() {
        Map<String,Integer> results = IntStream.range(2, numbers.size()).boxed().flatMap(len -> Generator.combination(numbers).simple(len).stream().flatMap(nums -> Generator.permutation(nums).simple().stream().distinct())).map(nums -> {
            return Generator.combination(operators.keySet()).multi(nums.size() - 1).stream().map(ops -> {
                Optional<Integer> result = Optional.of(nums.get(0).getValue());
                for (int i = 1; i < nums.size(); ++i) {
                    final int j = i;
                    final String key = ops.get(i - 1);
                    result = result.flatMap(n -> operators.get(key).apply(n, nums.get(j).getValue()));
                }
                if (result.isPresent() && Math.abs(target - result.get()) < 10) {
                    return Map.of(
                        "result: " + result.get() + ", nums: " + nums + ", ops: " + ops, Math.abs(target - result.get())
                    );
                }
                return Map.<String,Integer>of();
            }).reduce(new HashMap<>(), (a, b) -> {
                a.putAll(b);
                return a;
            });
        }).reduce(new HashMap<>(), (a, b) -> {
            a.putAll(b);
            return a;
        });

        var scoreComparator = Map.Entry.<String,Integer>comparingByValue();
        results.entrySet().stream().sorted(scoreComparator).forEach(System.out::println);
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public void setNumbers(Integer... numbers) {
        this.numbers = Arrays.stream(numbers).map(WholeNumber::new).collect(Collectors.toList());
    }
}

package dev.mrodriguezul.practice.functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppMain {
    public static void main(String[] args) {

        //(parameters) -> {body}

        INumericOperation multiply = (a, b) -> a * b;
        INumericOperation divide = (a, b) -> a / b;
        INumericOperation addition = (a, b) -> a + b;
        INumericOperation subtrair = (a, b) -> a - b;

        System.out.println("addition " + addition.operation(3, 4));
        System.out.println("subtrair " + subtrair.operation(3, 4));
        System.out.println("multiply " + multiply.operation(3, 4));
        System.out.println("divide " + divide.operation(3, 4));

        Maximo maximo = list -> {
            return Arrays.stream(list).max().orElse(0);
        };
        System.out.println("Maximo: " + maximo.max(new int[]{1, 20, 3, 4, 5}));

        //Arrays.stream(new int[]{1, 2, 3, 4, 5}, )
        List<Integer> lst = Stream.of(new int[]{1, 2, 3, 4}, new int[]{4, 5, 6, 7}, new int[]{7, 8, 9, 10})
                .flatMapToInt(Arrays::stream)
                .boxed()
                .toList();

        List<Integer> lst2 = Stream.of(new Integer[]{1, 2, 3, 4}, new Integer[]{4, 5, 6, 7}, new Integer[]{7, 8, 9, 10})
                .flatMap(Arrays::stream)
                .toList();

        System.out.println("List: " + lst);
    }

    @FunctionalInterface
    interface Maximo {
        int max(int[] list);
    }

}

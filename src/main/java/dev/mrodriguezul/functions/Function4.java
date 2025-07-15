package dev.mrodriguezul.functions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Function4 {
    public static void main(String[] args) {
        //testFactorialFunction();
        //testEvalLists();
        testStream();
    }

    public static void testStream(){
        List<String> names1 = Arrays.asList("Miguel", "Ana", "José", "Maria", "Pedro");
        List<String> names2 = Arrays.asList("Angel", "Maria", "Leonardo", "Flor", "Jacinto");
        List<String> names3 = Arrays.asList("Sebastian", "Martha", "Iris", "Federico", "Wilfredo");

        // Convert to uppercase
        List<String> upperCaseNames = names1.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase Names: " + upperCaseNames);
        // Count names
        long count = names1.stream().count();
        System.out.println("Total Names: " + count);

        // Filter names that start with 'M'
        List<String> lstAll = Stream.of(names1, names2, names3)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("All names: " + lstAll);

        // Sort names
        lstAll.forEach(System.out::println);

        // Filter names that start with 'M'
        boolean isCont = lstAll.stream().anyMatch(name -> name.contains("el"));
        System.out.println("Is there any name containing 'el'? " + isCont);

        String findAny = lstAll.stream().findAny().orElse(null);
        String findAny2 = lstAll.stream().filter(name -> name.length() == 5).findAny().orElse(null);
        System.out.println("Any name: " + findAny);
        System.out.println("Any name with length 5: " + findAny2);


    }

    public static void testFactorialFunction() {
        FactorialFunction factorial = n -> {
            if(n <= 0){
                return 0;
            }else{
                long result = 1;
                for(int i = 1; i <= n; i++){
                    result *= i;
                }
                return result;
            }
        };
        System.out.println(factorial.apply(3));

        FactorialFunction factorial2 = new FactorialFunction() {
            @Override
            public long apply(int n) {
                if(n <= 0) return 0;
                if(n == 1) return 1;
                return this.apply(n - 1) * n;
            }
        };
        System.out.println(factorial.apply(3));
    }

    public static void testEvalLists() {

        int[] numbers = {3, 7, 2, 9, 2, 3, 8, 1, 1, 6};
        List<Integer> numbersList = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        //List<Integer> numbersList = Arrays.stream(numbers).boxed().toList();//java 16+

        var x = numbersList.stream().max(Integer::compareTo).orElse(0);
        System.out.println("Max number: " + x);

        Function<int[], Integer> function1 = list -> {
            return Arrays.stream(list).max().getAsInt();
        };

        Function<List<Integer>, Integer> function2 = list -> {
            return list.stream()
                    .filter(n -> n % 2 == 0)
                    //.mapToInt(n-> n)
                    .mapToInt(Integer::intValue)
                    .sum();
        };

        Function<List<Integer>, Double> function3 = list -> {
            return list.stream()
                    .mapToDouble(Integer::doubleValue)
                    .average()
                    .orElse(0.0);
        };

        Function<List<Integer>, Integer> function4 = list -> {
            return list.stream()
                    .mapToInt(Integer::intValue)
                    .min()
                    .getAsInt();
                    //.orElse(Integer.MIN_VALUE);
        };

        Function<List<Integer>, Integer> masRepetido1 = list -> {
            int numR = 0;
            int nR = 0;
            for (Integer n : list) {
                long nCount = list.stream().filter(i-> i.equals(n)).count();
                if (nCount >= nR) {
                    nR = (int) nCount;
                    numR = n;
                }
            }
            return numR;
        };


        Function<List<Integer>, Integer> masRepetido2 = list ->
                    list.stream()
                        .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                        .map(Map.Entry::getKey)
                        .orElse(null);


        System.out.println("Max number: " + function1.apply(numbers));
        System.out.println("Sum of even numbers: " + function2.apply(numbersList));
        System.out.println("Average of numbers: " + function3.apply(numbersList));
        System.out.println("Min number: " + function4.apply(numbersList));
        System.out.println("Most repeated number 1: " + masRepetido1.apply(numbersList));
        System.out.println("Most repeated number 2: " + masRepetido2.apply(numbersList));




    }

    @FunctionalInterface
    public interface FactorialFunction{
        long apply(int n);
    }
}

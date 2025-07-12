package dev.mrodriguezul.functions;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.*;

public class Function3 {
    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        Supplier<Integer> sup = () -> 1;

        consumer.accept("Hello, World!");
        consumer.accept("Supplier value: " + sup.get());

        System.out.println("Testing Function with UnaryOperator and Function chaining");
        UnaryOperator<String> welcomeMessage = name -> "Welcome " + name;
        UnaryOperator<String> welcomeEmphasys = name -> name + "!";
        Function<String, String> welcome = welcomeMessage.andThen(welcomeEmphasys);
        System.out.println(welcome.apply("Miguel"));
        System.out.println(welcomeMessage.andThen(welcomeEmphasys).apply("Miguel"));

        BinaryOperator<String> fullaName = (name, lastName) -> name + " " + lastName;
        System.out.println(fullaName.apply("Miguel", "Rodriguez"));

        TripleFunction<Integer, Integer, Integer, Integer> calculateAge =
                (day, month, year) -> Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();
        System.out.println("Age: " + calculateAge.apply(1, 7, 1989));

        NothingFunction nada = () -> {
            System.out.println("Nothing to do here!");
        };
        EnteroFunction entero = () -> {
            return (int) (Math.random() * 100);
        };
        nada.nada();
        System.out.println("Aleatorio returned: " + entero.aleatorio());
        System.out.println("Test interface with default methods");
        LoadingPrint loadingPrint = message -> System.out.println("Loading: " + message);
        loadingPrint.execute(10, "Please wait...");

    }

    @FunctionalInterface
    interface TripleFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    @FunctionalInterface
    interface NothingFunction {
        void nada();
    }

    @FunctionalInterface
    interface EnteroFunction {
        int aleatorio();
    }

    @FunctionalInterface
    interface LoadingPrint {
        void print(String message);

        default void execute(int n, String message) {
            while (n-- > 0) {
                print(message);
            }
        }
    }
}

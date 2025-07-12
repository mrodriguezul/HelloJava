package dev.mrodriguezul.functions;

import dev.mrodriguezul.entity.Doctor;
import dev.mrodriguezul.entity.Patient;
import dev.mrodriguezul.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Function1 {
    public static void main(String[] args) {
        Function<String, String> hey = name-> "Hey " + name + "!";
        System.out.println(hey.apply("Miguel"));

        Function<Integer, Double> operationSqrt = number -> {
            if (number < 0) {
                return Math.sqrt(Math.abs(number));
            } else {
                return Math.sqrt(number);
            }
        };
        System.out.println(operationSqrt.apply(81));

        Function<Integer, String> multiplyByHimselfToString = new Function<Integer, String>() {
            @Override
            public String apply(Integer number) {
                return "the result is: " + (number * number);
            }
        };
        System.out.println(multiplyByHimselfToString.apply(5));

        Function<Person, String> showPersonInfo = person -> {
            return "Name: " + person.getName() + ", Email: " + person.getEmail();
        };
        Person doctor = new Doctor("Miguel", "miguel@gmail.com", "Cardiology");
        Person patient = new Patient("Ana", "anita@gmail.com", "1990-01-01", 60.5, 1.65, "O+");
        Person patient2 = new Patient("José", "jose@gmail.com", "1992-02-02", 70.0, 1.75, "A+");
        System.out.println(showPersonInfo.apply(doctor));

        Function<Person, Function<Person, String>> appointment1 = person -> {
            return person2 -> "Name: " + person.getName() + ", Email: " + person.getEmail() +
                              " | Name2: " + person2.getName() + ", Email2: " + person2.getEmail();
        };
        System.out.println(appointment1.apply(doctor).apply(patient));

        Function<Person, Function<Person, String>> appointment2 = new Function<Person, Function<Person, String>>() {
            @Override
            public Function<Person, String> apply(Person person) {
                return new Function<Person, String>() {;
                    @Override
                    public String apply(Person person2) {
                        Doctor doc = (Doctor) person;
                        Patient patient = (Patient) person2;
                        return "Appointment - " + " Doctor: " + doc.getName() + ", Speciality: " + doc.getSpeciality() +
                               " | Patient: " + patient.getName() + ", H/W: (" + patient.getHeight() + ", " + patient.getWeight() + "), Blood: " + patient.getBlood();
                    }
                };
            }
        };
        System.out.println(appointment2.apply(doctor).apply(patient));

        Function<Person, String> appointmentWithMiguel = appointment2.apply(doctor);
        System.out.println(appointmentWithMiguel.apply(patient));
        System.out.println(appointmentWithMiguel.apply(patient2));

        List<Integer> lstNumbers = List.of(1, 2, 3, 4, 5);
        Function<Integer, Integer> addition10 = x -> x + 10;
        Function<Integer, Integer> subtraction10 = x -> x - 10;
        Function<Integer, Integer> multiplication10 = x -> x * 10;
        Function<Integer, Integer> division10 = x -> x / 10;
        Function<Integer, Integer> operacion = addition10.compose(subtraction10).compose(multiplication10).compose(num -> {
            System.out.println("NUMERO: " + num);
            return num / 10;
        });
        //con compose se ejecuta de derecha a izquierda!!!

        System.out.println("Original List:"+lstNumbers);
        System.out.println("Addition 10:");
        printList(lstNumbers, addition10);
        System.out.println("Subtraction 10:");
        printList(lstNumbers, subtraction10);
        System.out.println("Multiplication 10:");
        printList(lstNumbers, multiplication10);
        System.out.println("Division 10:");
        printList(lstNumbers, division10);
        System.out.println("Operation composed (add, sub, mult, div):"+operacion.apply(21));

        List<Function<String, String>> lstTransformer = new ArrayList<>();
        lstTransformer.add(name -> name = "Hey " + name + "!");
        lstTransformer.add(name -> name += " Welcome back to the office ");
        lstTransformer.add(name -> name += " The team was waiting for you ");
        lstTransformer.add(name -> "Have a nice day!");
        lstTransformer.add(name -> name.toUpperCase(Locale.getDefault()));

        for (Function<String, String> transformer : lstTransformer) {
            System.out.println(transformer.apply("Miguel"));
        }

        Function<String, String> lowerFunction = String::toLowerCase;
        Function<String, String> upperFunction = String::toUpperCase;
        Function<String, String> trimFunction = String::trim;
        Function<String, String> replaceFunction = s -> s.replace(" ", "_");

        Function<String, String> mixValidation = lowerFunction
                .andThen(trimFunction)
                .andThen(replaceFunction)
                .andThen(upperFunction);
        System.out.println(mixValidation.apply("  Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit "));

        String result = lowerFunction
                .andThen(trimFunction)
                .andThen(replaceFunction)
                .andThen(upperFunction)
                .apply("  Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit ");
        System.out.println(result);

        String result2 = upperFunction.apply(replaceFunction.apply(trimFunction.apply(lowerFunction.apply("  Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit "))));
        System.out.println(result2);

        BiFunction<Integer, Double, String> multiplyAndFormat = (num, factor) -> {
            double resultM = num * factor;
            return String.format("The result of multiplying %d by %.2f is: %.2f", num, factor, resultM);
        };
        System.out.println(multiplyAndFormat.apply(5, 2.5));


    }

    public static void printList(List<Integer> lst, Function<Integer, Integer> function) {
        List<Integer> lstResult = new ArrayList<>();
        for (Integer number : lst) {
            lstResult.add(function.apply(number));
        }
        System.out.println(lstResult);
        //System.out.println(String.join(", ", lstResult.stream().map(String::valueOf).toArray(String[]::new)));
    }
}

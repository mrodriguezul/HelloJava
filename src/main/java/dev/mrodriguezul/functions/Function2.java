package dev.mrodriguezul.functions;

import dev.mrodriguezul.entity.Doctor;
import dev.mrodriguezul.entity.Nurse;
import dev.mrodriguezul.entity.Person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Function2 {
    public static void main(String[] args) {
        Predicate<Integer> isEven = number -> number % 2 == 0;
        Predicate<Integer> isOdd = number -> number % 2 == 1;

        System.out.println(isEven.test(4)); // true
        System.out.println(isEven.test(5)); // false
        System.out.println(isOdd.test(4));  // false
        System.out.println(isOdd.test(5));  // true

        System.out.println("Testing Person types with predicates");
        Predicate<Person> isNurse = person -> person instanceof Nurse;
        Predicate<Person> isNotNurse = isNurse.negate();
        Person nurse = new Nurse("Maria", "maria@gmail.com", "Pediatrics");
        Person doctor = new Doctor("Miguel", "miguel@gmail.com", "cardiology");

        System.out.println(isNurse.test(nurse)); // true
        System.out.println(isNurse.test(doctor)); // false
        System.out.println(isNotNurse.test(nurse)); // false
        System.out.println(isNotNurse.test(doctor)); // true

        System.out.println("Testing valid email and name predicates");
        Predicate<Person> validEmail = p -> p.getEmail() != null && p.getEmail().contains("@");
        Predicate<Person> validName = p -> p.getName() != null && !p.getName().isEmpty();
        Predicate<Person> validPerson = validEmail.and(validName);

        Doctor doctor1 = new Doctor("Miguel", "", "cardiology");
        Doctor doctor2 = new Doctor("Jose", "jose@gmail.com", "Traumatology");
        Nurse nurse1 = new Nurse("Maria", "maria@gmail.com", "Pediatrics");
        Nurse nurse2 = new Nurse("", "Amanda", "Pediatrics");
        System.out.println(validPerson.test(doctor1)); // false
        System.out.println(validPerson.test(doctor2)); // true
        System.out.println(validPerson.test(nurse1)); // true
        System.out.println(validPerson.test(nurse2)); // false

        List<Person> lstPersons = List.of(nurse1, doctor1, nurse2, doctor2);
        System.out.println("Valid persons in the list:");
        lstPersons.stream()
                .filter(validPerson)
                .forEach(p -> System.out.println("Valid Person: " + p.getName() + ", Email: " + p.getEmail()));

        lstPersons.stream()
                .filter(validPerson)
                .forEach(Person::successValidationMessage);

        List<Person> validList = lstPersons.stream()
                .filter(validPerson)
                .collect(Collectors.toList());

        System.out.println("Valid persons collected in a new list:");
        System.out.println(validList);

    }
}

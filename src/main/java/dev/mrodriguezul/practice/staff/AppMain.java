package dev.mrodriguezul.practice.staff;

import dev.mrodriguezul.entity.Employee;
import dev.mrodriguezul.enums.EmployeePosition;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppMain {
    public static void main(String[] args) {
        List<Employee> employees = generateEmployees();
        //employees.forEach(System.out::println);

        //Get the email of three younger people of the list which position is DEVELOPER
//        getEmailOfThreeYoungerDevelopers(employees);
//        getEmailOfThreeYoungerDevelopersWithSalary(employees);

        //Get map of employees grouped by position
        //getMapOfEmployeesGroupedByPosition(employees);
        //getMapOfEmployeesGroupedByPositionWithCount(employees);
        getMapOfEmployeesGroupedByPositionWithAverageSalary(employees);
//        getMapOfEmployeesGroupedByPositionWithAverageSalaryAndCount(employees);

        //Get the average salary, the highes, the lowest of employees in the company using just one operation
//        getAverageSalaryHighestLowest(employees);

        //Get two groups of employees grouped by salary, one with employees with salary higher than 60000 and another with employees with salary lower than 60000
        //getEmployeesGroupedBySalary(employees);
        //getEmployeesGroupedBySalaryThreeGroups(employees);


    }

    public static void getEmailOfThreeYoungerDevelopers(List<Employee> employees) {
        employees.stream()
                .filter(e -> e.getPosition() == EmployeePosition.DEVELOPER)
                .sorted(Comparator.comparing(Employee::getBirthdate).reversed())
                .limit(3)
                .map(Employee::getEmail)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void getEmailOfThreeYoungerDevelopersWithSalary(List<Employee> employees) {
        employees.stream()
                .filter(e -> e.getPosition() == EmployeePosition.DEVELOPER)
                .sorted(Comparator.comparing(Employee::getBirthdate).reversed())
                .limit(3)
                .map(e -> e.getEmail() + " - " + e.getSalary())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void getMapOfEmployeesGroupedByPosition(List<Employee> employees) {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getNames))
                .collect(Collectors.groupingBy(Employee::getPosition))
                .forEach((position, employeesList) -> {
                    System.out.println("Position: " + position);
                    employeesList.forEach(System.out::println);
                });
    }

    public static void getMapOfEmployeesGroupedByPositionWithCount(List<Employee> employees) {
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.counting()))
                .forEach((position, count) -> {
                    System.out.println("Position: " + position + ", Count: " + count);
                });

        employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(EmployeePosition::toString)))
                .forEach(entry -> {
                    System.out.println("Position: " + entry.getKey() + ", Count: " + entry.getValue());
                });
    }

    public static void getMapOfEmployeesGroupedByPositionWithAverageSalary(List<Employee> employees) {
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.averagingDouble(Employee::getSalary)))
                .forEach((position, averageSalary) -> {
                    System.out.println("Position: " + position + ", Average Salary: " + averageSalary);
                });

        employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> {
                    System.out.println("Position: " + entry.getKey() + ", Average Salary: " + entry.getValue());
                });
    }

    public static void getMapOfEmployeesGroupedByPositionWithAverageSalaryAndCount(List<Employee> employees) {
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            double averageSalary = list.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
                            long count = list.size();
                            return new Object[]{averageSalary, count};
                        }
                )))
                .forEach((position, stats) -> {
                    System.out.println("Position: " + position + ", Average Salary: " + stats[0] + ", Count: " + stats[1]);
                });
    }

    //Get group of employees grouped by position and sorted by salary in descending order

    public static void getAverageSalaryHighestLowest(List<Employee> employees) {
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        double highestSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0.0);
        double lowestSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0.0);

        DoubleSummaryStatistics salaryStats = employees.stream()
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();

        System.out.println("Average Salary: " + salaryStats.getAverage()+ " - " + averageSalary);
        System.out.println("Highest Salary: " + salaryStats.getMax()+" - " + highestSalary);
        System.out.println("Lowest Salary: " + salaryStats.getMin()+" - " + lowestSalary);
    }

    //Get two groups of employees grouped by salary, one with employees with salary higher than 60000 and another with employees with salary lower than 60000
    public static void getEmployeesGroupedBySalary(List<Employee> employees) {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 60000))
                .forEach((isHigher, employeesList) -> {
                    System.out.println("Salary " + (isHigher ? "higher" : "lower") + " than 60000:");
                    employeesList.forEach(System.out::println);
                });
    }

    //Get three groups of employees grouped by salary, one group with employees with salary lower than 60000, another group with employees with salary between 60000 and 70000, and another group with employees with salary higher than 70000
    public static void getEmployeesGroupedBySalaryThreeGroups(List<Employee> employees) {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.groupingBy(e -> {
                    if (e.getSalary() < 60000) {
                        return "Lower than 60000";
                    } else if (e.getSalary() >= 60000 && e.getSalary() <= 70000) {
                        return "Between 60000 and 70000";
                    } else {
                        return "Higher than 70000";
                    }
                }))
                .forEach((salaryRange, employeesList) -> {
                    System.out.println("Salary range: " + salaryRange);
                    employeesList.forEach(System.out::println);
                });
    }

    public static List<Employee> generateEmployees() {
        List<Employee> employees = List.of(
                new Employee(1L, "John", "Doe", "john.doe@example.com", "1998-05-15", 55000.0, EmployeePosition.MANAGER),
                new Employee(2L, "Jane", "Smith", "jane.smith@example.com", "1993-08-22", 60000.0, EmployeePosition.DEVELOPER),
                new Employee(3L, "Alice", "Johnson", "alice.johnson@example.com", "1995-12-10", 58000.0, EmployeePosition.DEVELOPER),
                new Employee(4L, "Bob", "Brown", "bob.brown@example.com", "1988-03-30", 65000.0, EmployeePosition.DEVELOPER),
                new Employee(5L, "Charlie", "Davis", "charlie.davis@example.com", "1983-07-19", 70000.0, EmployeePosition.DESIGNER),
                new Employee(6L, "Diana", "Miller", "diana.miller@example.com", "1991-11-05", 62000.0, EmployeePosition.MANAGER),
                new Employee(7L, "Eve", "Wilson", "eve.wilson@example.com", "1994-04-25", 59000.0, EmployeePosition.DESIGNER),
                new Employee(8L, "Frank", "Moore", "frank.moore@example.com", "1978-09-12", 75000.0, EmployeePosition.MANAGER),
                new Employee(9L, "Grace", "Taylor", "grace.taylor@example.com", "1996-02-18", 57000.0, EmployeePosition.DEVELOPER),
                new Employee(10L, "Hank", "Anderson", "hank.anderson@example.com", "1990-06-14", 63000.0, EmployeePosition.DESIGNER),
                new Employee(11L, "Ivy", "Thomas", "ivy.thomas@example.com", "1992-10-09", 61000.0, EmployeePosition.QA),
                new Employee(12L, "Jack", "Jackson", "jack.jackson@example.com", "1997-01-23", 56000.0, EmployeePosition.QA),
                new Employee(13L, "Karen", "White", "karen.white@example.com", "1985-08-03", 68000.0, EmployeePosition.HR),
                new Employee(14L, "Leo", "Harris", "leo.harris@example.com", "1989-12-17", 64000.0, EmployeePosition.DEVELOPER),
                new Employee(15L, "Mia", "Martin", "mia.martin@example.com", "1994-03-11", 59000.0, EmployeePosition.QA),
                new Employee(16L, "Nina", "Garcia", "nina.garcia@example.com", "1987-07-27", 67000.0, EmployeePosition.DEVELOPER),
                new Employee(17L, "Oscar", "Martinez", "oscar.martinez@example.com", "1982-11-30", 72000.0, EmployeePosition.MANAGER),
                new Employee(18L, "Paul", "Robinson", "paul.robinson@example.com", "1993-05-20", 60000.0, EmployeePosition.HR),
                new Employee(19L, "Quinn", "Clark", "quinn.clark@example.com", "1995-09-15", 58000.0, EmployeePosition.DEVELOPER),
                new Employee(20L, "Rachel", "Rodriguez", "rachel.rodriguez@example.com", "1986-12-05", 69000.0, EmployeePosition.MANAGER),
                new Employee(21L, "Steve", "Lewis", "steve.lewis@example.com", "1984-04-10", 71000.0, EmployeePosition.HR),
                new Employee(22L, "Tina", "Walker", "tina.walker@example.com", "1990-08-29", 63000.0, EmployeePosition.DEVELOPER),
                new Employee(23L, "Uma", "Hall", "uma.hall@example.com", "1996-01-14", 57000.0, EmployeePosition.DEVELOPER),
                new Employee(24L, "Victor", "Allen", "victor.allen@example.com", "1981-06-22", 76000.0, EmployeePosition.DEVELOPER),
                new Employee(25L, "Wendy", "Young", "wendy.young@example.com", "1992-10-03", 61000.0, EmployeePosition.DESIGNER),
                new Employee(26L, "Xander", "King", "xander.king@example.com", "1994-02-19", 59000.0, EmployeePosition.DEVELOPER),
                new Employee(27L, "Yara", "Scott", "yara.scott@example.com", "1988-07-07", 65000.0, EmployeePosition.MANAGER),
                new Employee(28L, "Zane", "Adams", "zane.adams@example.com", "1983-11-25", 70000.0, EmployeePosition.DESIGNER),
                new Employee(29L, "Amy", "Baker", "amy.baker@example.com", "1991-03-08", 62000.0, EmployeePosition.DESIGNER),
                new Employee(30L, "Brian", "Carter", "brian.carter@example.com", "1985-09-17", 68000.0, EmployeePosition.QA)
        );
        return employees;
    }

}

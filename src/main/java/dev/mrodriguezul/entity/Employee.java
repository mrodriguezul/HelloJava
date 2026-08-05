package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.EmployeePosition;

import java.time.LocalDate;

public class Employee {
    private Long id;
    private String names;
    private String lastNames;
    private String email;
    private LocalDate birthdate;
    private double salary;
    private EmployeePosition position;

    public Employee(Long id, String names, String lastNames, String email, String birthdate, double salary, EmployeePosition position) {
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
        this.setBirthdate(birthdate);
        this.salary = salary;
        this.position = position;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = LocalDate.parse(birthdate);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public void setPosition(String position) {
        this.position = EmployeePosition.valueOf(position.toUpperCase());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", salary=" + salary +
                ", position=" + position +
                '}';
    }
}

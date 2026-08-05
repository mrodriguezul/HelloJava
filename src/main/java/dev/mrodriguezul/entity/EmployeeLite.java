package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.EmployeePosition;

import java.time.LocalDate;

public record EmployeeLite(
        Long id,
        String fullName,
        double salary,
        EmployeePosition position) {

}

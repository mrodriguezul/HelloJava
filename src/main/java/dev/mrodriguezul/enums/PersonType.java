package dev.mrodriguezul.enums;

public enum PersonType {
    DOCTOR("Doctor"),
    PATIENT("Patient"),
    NURSE("Nurse");

    private final String type;

    PersonType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

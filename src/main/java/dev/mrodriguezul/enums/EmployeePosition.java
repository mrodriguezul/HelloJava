package dev.mrodriguezul.enums;

public enum EmployeePosition {
    MANAGER("Manager"),
    DEVELOPER("Developer"),
    DESIGNER("Designer"),
    QA("QA"),
    HR("HR");

    private final String position;

    EmployeePosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position;
    }
}

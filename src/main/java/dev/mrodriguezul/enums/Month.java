package dev.mrodriguezul.enums;

public enum Month {
    JANUARY("01", "January", 31),
    FEBRUARY("02", "February", 28),
    MARCH("03", "March", 31),
    APRIL("04", "April", 30),
    MAY("05", "May", 31),
    JUNE("06", "June", 30),
    JULY("07", "July", 31),
    AUGUST("08", "August", 31),
    SEPTEMBER("09", "September", 30),
    OCTOBER("10", "October", 31),
    NOVEMBER("11", "November", 30),
    DECEMBER("12", "December", 31);

    private final String code;
    private final String name;
    private final int days;

    Month(String code, String name, int days) {
        this.code = code;
        this.name = name;
        this.days = days;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getDays() {
        return days;
    }
}

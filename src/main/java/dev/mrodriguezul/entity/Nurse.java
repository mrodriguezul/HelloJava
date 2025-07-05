package dev.mrodriguezul.entity;

public class Nurse extends Person {
    private String speciality;

    public Nurse(String name, String email, String speciality) {
        super(name, email);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        System.out.println("Nurse ID: " + getId());
    }
}


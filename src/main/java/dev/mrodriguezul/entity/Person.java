package dev.mrodriguezul.entity;

public abstract class Person {

    private int id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    private static int nextId = 1;

    public Person(String name, String email) {
        this.id = autoincrement();
        this.name = name;
        this.email = email;
    }

    private static int autoincrement() {
        return nextId++;
    }
    public static void setNextId(int i) {
        nextId = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name + " - " + email + " - " + address + " - " + phoneNumber;
    }

    public void successValidationMessage(){
        System.out.println("Success validation for Person: " + this.getName() + ", Email: " + this.getEmail());
    }

    public abstract void displayInfo();
}

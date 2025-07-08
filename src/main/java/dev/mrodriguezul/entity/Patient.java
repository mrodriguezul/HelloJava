package dev.mrodriguezul.entity;

import java.util.ArrayList;
import java.util.Date;

public class Patient extends Person {
    private String birthday;
    private double weight;
    private double height;
    private String blood;
    private ArrayList<AppointmentDoctor> lstAppointmentsDoctor = new ArrayList<>();
    private ArrayList<AppointmentNurse> lstAppointmentsNurse = new ArrayList<>();

    public Patient(String name, String email) {
        super(name, email);
    }

    public Patient(String name, String email, String birthday, double weight, double height, String blood) {
        super(name, email);
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
    @Override
    public String toString() {
        return "Patient{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", birthday='" + birthday + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", blood='" + blood + '\'' +
                '}' + lstAppointmentsDoctor;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient Information: " + this.toString());
    }

    public ArrayList<AppointmentDoctor> getLstAppointmentsDoctor() {
        return lstAppointmentsDoctor;
    }

    public void setLstAppointmentsDoctor(ArrayList<AppointmentDoctor> lstAppointmentsDoctor) {
        this.lstAppointmentsDoctor = lstAppointmentsDoctor;
    }

    public void addAppointmentsDoctor(Doctor doctor, Date date, String time) {
        AppointmentDoctor appointment = new AppointmentDoctor(doctor, this, date, time);
        appointment.schedule(date, time);
        this.lstAppointmentsDoctor.add(appointment);
    }

    public ArrayList<AppointmentNurse> getLstAppointmentsNurse() {
        return lstAppointmentsNurse;
    }

    public void setLstAppointmentsNurse(ArrayList<AppointmentNurse> lstAppointmentsNurse) {
        this.lstAppointmentsNurse = lstAppointmentsNurse;
    }
}

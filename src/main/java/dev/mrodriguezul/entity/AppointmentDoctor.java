package dev.mrodriguezul.entity;

import java.util.Date;
import dev.mrodriguezul.interface_.ISchedulable;

public class AppointmentDoctor implements ISchedulable {
    private int id;
    private Doctor doctor;
    private Patient patient;
    private Date date;
    private String time;

    public AppointmentDoctor(Doctor doctor, Patient patient, Date date, String time) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public void schedule(Date date, String time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "AppointmentDoctor{" +
                "id=" + id +
                ", doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }
}


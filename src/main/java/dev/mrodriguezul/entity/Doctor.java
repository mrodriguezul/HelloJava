package dev.mrodriguezul.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {

    private String speciality;
    private List<AvailableAppointment> availableAppointments = new ArrayList<>();

    public Doctor(String name, String email, String speciality) {
        super(name, email);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void addAvailableAppointment(String date, String time) {
        availableAppointments.add(new AvailableAppointment(date, time));
    }

    public List<AvailableAppointment> getAvailableAppointments() {
        return availableAppointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", speciality='" + speciality + '\'' +
                ", availableAppointments=" + availableAppointments +
                '}';
    }

    @Override
    public void displayInfo() {
        System.out.println("Doctor ID: " + getId());
    }

    public static class AvailableAppointment {
        private int id;
        private Date date;
        private String time;
        private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");

        public AvailableAppointment(String date, String time) {
            try {
                this.date = sdFormat.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
        }

        public String getDate(String dateFormat) {
            return sdFormat.format(date);
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
        public String toString() {
            return "Available Appointments{" +
                    " date=" + date +
                    ", time=" + time + "}";
        }
    }
}

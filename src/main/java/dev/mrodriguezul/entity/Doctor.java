package dev.mrodriguezul.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {

    private String speciality;
    private static int nextId = 1;
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

    public void addAvailableAppointment(Date date, String time) {
        availableAppointments.add(new AvailableAppointment(date, time));
    }

    public List<AvailableAppointment> getAvailableAppointments() {
        return availableAppointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +

                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", speciality='" + speciality + '\'' +
                ", availableAppointments=" + availableAppointments +
                '}';
    }

    public static class AvailableAppointment {
        private int id;
        private Date date;
        private String time;

        public AvailableAppointment(Date date, String time) {
            this.date = date;
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
            return "{" +
                    " date=" + date +
                    ", time=" + time + "}";
        }
    }
}

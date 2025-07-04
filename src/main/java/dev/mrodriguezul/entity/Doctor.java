package dev.mrodriguezul.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int id;
    private String name;
    private String speciality;
    private static int nextId = 1;
    private List<AvailableAppointment> availableAppointments = new ArrayList<>();

    public Doctor(int id, String name, String speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    public Doctor(String name, String speciality) {
        this.id = autoincrement();
        this.name = name;
        this.speciality = speciality;
    }

    private static int autoincrement() {
        return nextId++;
    }

    public static void setNextId(int i) {
        nextId = i;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "id=" + id +
                ", name='" + name + '\'' +
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

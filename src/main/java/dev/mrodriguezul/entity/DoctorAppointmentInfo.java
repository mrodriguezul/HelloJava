package dev.mrodriguezul.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorAppointmentInfo {
    private Doctor doctor;
    private Date date;
    private String time;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");

    public DoctorAppointmentInfo(Doctor doctor, Date date, String time) {
//        try {
//
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
        this.date = date;
        this.doctor = doctor;
        this.time = time;
    }

    public Doctor getDoctor() { return doctor; }
    public Date getDate() { return date; }
    public String getDate(String dateFormat) {
        return sdFormat.format(date);
    }
    public String getTime() { return time; }

    @Override
    public String toString() {
        return "Doctor: " + doctor.getName() + ", Date: " + date + ", Time: " + time;
    }
}

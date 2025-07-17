package dev.mrodriguezul.functions;

import dev.mrodriguezul.entity.Doctor;
import dev.mrodriguezul.entity.DoctorAppointmentInfo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Function5 {
    public static void main(String[] args) {
        //testOptional();
        //testParallelStream();
        testFlatMap();
    }

    public static  void testFlatMap(){
        List<Doctor> listDoctors = getDoctors();
        listDoctors.stream()
                .flatMap(d -> d.getAvailableAppointments().stream()
                        .map(a -> "Doctor: " + d.getName() + ", Date: " + a.getDate("") + ", Time: " + a.getTime()))
                .distinct()
                .sorted(Comparator.reverseOrder())
                //.sorted(Comparator.naturalOrder())
                .sorted((s1, s2) -> {
                    String[] parts1 = s1.split(", ");
                    String[] parts2 = s2.split(", ");
                    String date1 = parts1[1].split(": ")[1];
                    String date2 = parts2[1].split(": ")[1];
                    return date1.compareTo(date2);
                })
                .forEach(System.out::println);

        // Doctors with available appointments between dates
        System.out.println("\nDoctors with available appointments between 02/08/2025 and 04/08/2025:");
        String fechaInicio = "02/08/2025";
        String fechaFin = "04/08/2025";
        listDoctors.stream()
                .filter(d -> d.getAvailableAppointments().stream()
                        .anyMatch(a -> {
                            String fecha = a.getDate("");
                            return fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0;
                        }))
                .forEach(d -> {
                    System.out.println("Doctor: " + d.getName());
                    d.getAvailableAppointments().stream()
                            .filter(a -> {
                                String fecha = a.getDate("");
                                return fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0;
                            })
                            .forEach(a -> System.out.println("  Available Appointment: " + a.getDate("") + " at " + a.getTime()));
                });


        List<DoctorAppointmentInfo> appointmentInfos = listDoctors.stream()
            .flatMap(d -> d.getAvailableAppointments().stream()
                .map(a -> new DoctorAppointmentInfo(d, a.getDate(), a.getTime())))
            .collect(Collectors.toList());

        appointmentInfos.forEach(System.out::println);

        System.out.println("\nDoctors with available appointments between 02/08/2025 and 04/08/2025:");
        List<DoctorAppointmentInfo> appointmentAvailable = listDoctors.stream()
                .flatMap(d -> d.getAvailableAppointments().stream()
                        .map(a -> new DoctorAppointmentInfo(d, a.getDate(), a.getTime())))
                .filter(inf -> {
                    String fecha = inf.getDate("");
                    return fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0;
                })
                .collect(Collectors.toList());

        appointmentAvailable.forEach(System.out::println);
    }

    public static void testParallelStream() {
        long startTime1 = System.currentTimeMillis();
        IntStream.iterate(0, i -> i + 1).limit(1)
                //.parallel()
                .filter(value -> value % 2 == 0)
                .forEach(i -> System.out.println("Stream Value: " + i));
        long finishTime1 = System.currentTimeMillis();

        long startTime2 = System.currentTimeMillis();
        IntStream.iterate(0, i -> i + 1).limit(1)
                .parallel()
                .filter(value -> value % 2 == 0)
                .forEach(i -> System.out.println("Parallel Stream Value: " + i));
        long finishTime2 = System.currentTimeMillis();

        System.out.println("Time taken for sequential stream: " + (finishTime1 - startTime1) + " ms");
        System.out.println("Time taken for parallel stream: " + (finishTime2 - startTime2) + " ms");
    }

    public static void testOptional() {
        //optional tests cases
        List<Doctor> doctors = getDoctors();
        //System.out.println("Doctors List:");
        //doctors.forEach(doctor -> System.out.println("Doctor: " + doctor.getName() + ", Speciality: " + doctor.getSpeciality()));

        Optional<List<Doctor>> optionalDoctors = Optional.ofNullable(doctors);

        //option1 when the list is null
        optionalDoctors.ifPresentOrElse(list -> list.forEach(System.out::println), () -> System.out.println("No doctors found."));
        //option2 when the list is null
        optionalDoctors.orElseGet(() -> {
            List<Doctor> newDoctors = new ArrayList<>();
            newDoctors.add(new Doctor("Default Doctor", "", "General Medicine"));
            return newDoctors;
        }).forEach(doctor -> System.out.println("Doctor: " + doctor.getName() + ", Speciality: " + doctor.getSpeciality()));
        //option3 when the list is null
        optionalDoctors.orElse(Arrays.asList(new Doctor("Default Doctor", "", "General Medicine"))).forEach(System.out::println);

        if(optionalDoctors.isPresent()) {
            // If the list is not null, we can safely use it
            System.out.println("Doctors are present.");
            optionalDoctors.get().forEach(doctor -> System.out.println("Doctor: " + doctor.getName() + ", Speciality: " + doctor.getSpeciality()));
        } else {
            System.out.println("No doctors found.");
        }
    }

    public static List<Doctor> getDoctors() {
        List<Doctor> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Doctor doctor = new Doctor("Doctor" + i, "doctor" + i + "@mail.com", "Speciality" + i);
            int numAppointments = (int) (Math.random() * 6); // entre 0 y 5
            for (int j = 1; j <= numAppointments; j++) {
                String date = String.format("%02d/08/2025", j); // Fechas de ejemplo
                String time = String.format("%02d:00", 8 + j); // Horas de ejemplo
                doctor.addAvailableAppointment(date, time);
            }
            list.add(doctor);
        }
        return list;
    }
}

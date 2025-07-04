package dev.mrodriguezul.practice;

import dev.mrodriguezul.entity.Doctor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Practice Application!");
        List<Doctor> lstDoctors = new ArrayList<>();
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int response;
        do{
            printMenu();
            response = scanner.nextInt();
            switch (response){
                case 1:
                    System.out.println("Create a Doctor");
                    scanner.nextLine(); // Limpiar el salto de línea pendiente
                    System.out.println("Enter the name of the doctor:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the email of the doctor:");
                    String email = scanner.nextLine();
                    System.out.println("Enter the name of the speciality:");
                    String speciality = scanner.nextLine();
                    Doctor doctor = createDoctor(name, email, speciality);
                    System.out.println("Add available appointments (optional, press 0 to skip):");
                    int addAppointmentResponse;
                    do{
                        System.out.println("1. Add Appointment");
                        System.out.println("0. Skip");
                        addAppointmentResponse = scanner.nextInt();
                        if (addAppointmentResponse == 1) {
                            scanner.nextLine(); // Limpiar el salto de línea pendiente
                            System.out.println("Enter the date (YYYY-MM-DD):");
                            String date = scanner.nextLine();
                            System.out.println("Enter the time (HH:MM):");
                            String time = scanner.nextLine();
                            // Assuming Doctor has a method to add available appointments
                            doctor.addAvailableAppointment(new Date(), time);
                        }
                    } while (addAppointmentResponse != 0);

                    lstDoctors.add(doctor);
                    break;
                case 2:
                    System.out.println("Listing all doctors...");
                    listDoctors(lstDoctors);
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    //Doctor.setNextId(1); // Reset the ID counter
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }

        }while (response != 0);

        // Close the scanner to avoid resource leaks
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("=========================");
        System.out.println("1. Create a Doctor");
        System.out.println("2. List Doctors");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public static Doctor createDoctor(String name, String email, String speciality) {
        System.out.println("Doctor created successfully!");
        return new Doctor(name, email, speciality);
    }

    public static void listDoctors(List<Doctor> lstDoctors) {
        for (Doctor doctor : lstDoctors) {
            System.out.println(doctor);

        }
    }
}



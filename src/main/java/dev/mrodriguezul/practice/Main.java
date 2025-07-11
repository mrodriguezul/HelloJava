package dev.mrodriguezul.practice;

import dev.mrodriguezul.entity.Doctor;
import dev.mrodriguezul.entity.Patient;
import dev.mrodriguezul.enums.Month;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Practice Application!");
        List<Doctor> lstDoctors = new ArrayList<>();
        List<Patient> lstPatients = new ArrayList<>();
        loadDoctors(lstDoctors);
        loadPatients(lstPatients);
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
                            System.out.println("Enter the date (dd/MM/yyyy):");
                            String date = scanner.nextLine();
                            System.out.println("Enter the time (HH:MM):");
                            String time = scanner.nextLine();
                            // Assuming Doctor has a method to add available appointments
                            doctor.addAvailableAppointment(date, time);
                        }
                    } while (addAppointmentResponse != 0);

                    lstDoctors.add(doctor);
                    break;
                case 2:
                    System.out.println("Listing all doctors...");
                    listDoctors(lstDoctors);
                    break;
                case 3:
                    System.out.println("Searching doctor ...... ");
                    scanner.nextLine();
                    System.out.println("Enter the email of the doctor:");
                    String emailDoctorSearch = scanner.nextLine();
                    Doctor doctorFound = searchDoctorByEmail(lstDoctors, emailDoctorSearch);

                    if(doctorFound != null) {
                        System.out.println("Doctor found: " + doctorFound);
                        System.out.println("Available appointments: " + doctorFound.getAvailableAppointments());

                        System.out.println("select Month to update available appointments ():");
                        int monthInit = new Date().getMonth();

                        for(int i = monthInit+1; i <= monthInit + 3; i++) {
                            System.out.println(i + ". " + Month.values()[i-1].getName());
                        }
                        int monthResponse = scanner.nextInt();
                        if (monthResponse >= 0 && monthResponse <= monthInit + 3) {
                            Month selectedMonth = Month.values()[monthResponse-1];
                            System.out.println("Updating available appointments for " + selectedMonth.getName());
                            scanner.nextLine(); // Limpiar el salto de línea pendiente
                            System.out.println("Enter the date (dd/MM/yyyy):");
                            String date = scanner.nextLine();
                            System.out.println("Enter the time (HH:MM):");
                            String time = scanner.nextLine();
                            doctorFound.addAvailableAppointment(date, time);
                            System.out.println("Available appointments updated for " + selectedMonth.getName());
                        } else {
                            System.out.println("Invalid month selection.");
                        }

                    }else{
                        System.out.println("Doctor not found with email: " + emailDoctorSearch);
                    }
                    break;
                case 4:
                    System.out.println("Listing all patients...");
                    listPatients(lstPatients);
                    break;
                case 5:
                    System.out.println("Searching patient ...... ");
                    scanner.nextLine();
                    System.out.println("Enter the email of the patient:");
                    String emailPatientSearch = scanner.nextLine();
                    Patient patientFound = searchPatientByEmail(lstPatients, emailPatientSearch);

                    if(patientFound != null) {
                        System.out.println("Patient found: " + patientFound);
                        System.out.println("Select a doctor:");
                        int k = 0;
                        for (Doctor doctorItem : lstDoctors) {
                            if(!doctorItem.getAvailableAppointments().isEmpty()) {
                                System.out.println("["+(k+1)+"] Doctor: " + doctorItem.getName() + " - Speciality: " + doctorItem.getSpeciality());
                            }else{
                                System.out.println("["+(k+1)+"] Doctor: " + doctorItem.getName() + " has no available appointments.");
                            }
                            k++;
                        }

                        int doctorIndexSelection = scanner.nextInt();
                        if (doctorIndexSelection <= 0 || doctorIndexSelection > lstDoctors.size() || lstDoctors.get(doctorIndexSelection - 1).getAvailableAppointments().isEmpty()) {
                            System.out.println("Invalid doctor selection.");
                            break;
                        }
                        k=0;
                        Doctor selectedDoctor = lstDoctors.get(doctorIndexSelection - 1);
                        for (Doctor.AvailableAppointment appointment : selectedDoctor.getAvailableAppointments()) {
                            System.out.println("["+(k+1)+"] Date: " + appointment.getDate() + ", Time: " + appointment.getTime());
                            k++;
                        }
                        System.out.println("Select an available appointment:");
                        int availableAppointmentSelection = scanner.nextInt();
                        if (availableAppointmentSelection < 1 || availableAppointmentSelection > selectedDoctor.getAvailableAppointments().size()) {
                            System.out.println("Invalid appointment selection.");
                            break;
                        }
                        Doctor.AvailableAppointment selectedAppointment = selectedDoctor.getAvailableAppointments().get(availableAppointmentSelection - 1);
                        System.out.println("Scheduling appointment for patient: " + patientFound.getName()
                                + " with doctor: " + selectedDoctor.getName() + " on " + selectedAppointment.getDate() + " at " + selectedAppointment.getTime());

                        // Here you would typically create an AppointmentDoctor object and save it to a list or database
                        patientFound.addAppointmentsDoctor(selectedDoctor, selectedAppointment.getDate(), selectedAppointment.getTime());

                    }else{
                        System.out.println("Patient not found with email: " + emailPatientSearch);
                    }
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
        System.out.println("3. Update Doctor Available appointments");
        System.out.println("4. List Patients");
        System.out.println("5. Register an Appointment for a patient");
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

    public static void listPatients(List<Patient> lstPatients) {
        for (Patient patient : lstPatients) {
            System.out.println(patient);
        }
    }

    public static void loadDoctors(List<Doctor> lstDoctors) {
        System.out.println("Loading doctors from a data source...");
        lstDoctors.add(new Doctor("John Doe", "john@gmail.com", "Cardiology"));
        lstDoctors.add(new Doctor("Jane Smith", "jane@gmail.com", "Neurology"));
        lstDoctors.add(new Doctor("Alice Johnson", "alice@gmail.com", "Pediatrics"));
    }

    public static void loadPatients(List<Patient> lstPatients) {
        System.out.println("Loading patients from a data source...");
        lstPatients.add(new Patient("Miguel", "miguel@gmail.com", "01/01/1990", 70.5, 1.75, "O+"));
    }

    public static Doctor searchDoctorByEmail(List<Doctor> lstDoctors, String email) {
        return lstDoctors.stream()
                .filter(doctor -> doctor.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public static Patient searchPatientByEmail(List<Patient> lstPatients, String email) {
        return lstPatients.stream()
                .filter(patient -> patient.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}



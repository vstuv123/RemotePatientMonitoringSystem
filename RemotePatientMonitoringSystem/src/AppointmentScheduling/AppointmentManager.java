package AppointmentScheduling;

import UserManagement.Doctor;
import UserManagement.Patient;
import UserManagement.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentManager {
    Patient[] patients = (Patient[]) User.getRegisteredPatients();
    Doctor[] doctors = (Doctor[]) User.getRegisteredDoctors();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Doctor getDoctorById(long doctorID) {
        for (int i = 0; i < User.getNoOfDoctors(); i++) {
            if (doctors[i].getUnique_id() == doctorID) {
                return doctors[i];
            }
        }
        return null;
    }

    public Patient getPatientById(long patientID) {
        for (int i = 0; i < User.getNoOfPatients(); i++) {
            if (patients[i].getUnique_id() == patientID) {
                return patients[i];
            }
        }
        return null;
    }

    public void displayAppointmentDetails() {
        System.out.println("Total Appointments are " + appointments.size());
        for (Appointment appointment : appointments) {
            Doctor doctor = getDoctorById(appointment.getDoctorID());
            Patient patient = getPatientById(appointment.getPatientID());

            System.out.println("\nAppointment ID: " + appointment.getAppointmentID());
            System.out.println("Appointment Date: " + appointment.getDate());
            System.out.println("Doctor: " + (doctor != null ? doctor.getName() : "Not Found"));
            System.out.println("Patient: " + (patient != null ? patient.getName() : "Not Found"));
            System.out.println("Status: " + appointment.getStatus() + "\n");
        }
    }

    public void requestAppointments() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date of appointment (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        LocalDate appointmentDate = LocalDate.parse(date);

        System.out.print("Enter Doctor ID: ");
        long doctorID = scanner.nextLong();
        System.out.print("Enter Patient ID: ");
        long patientID = scanner.nextLong();
        System.out.print("Enter status of appointment (Pending/ Approved): ");
        String status = scanner.nextLine();

        appointments.add(new Appointment(appointmentDate, doctorID, patientID, status));
        System.out.println("Appointment requested successfully!");
    }

    public void approveAppointment(int appointmentID) {
        for (Appointment appointment: appointments) {
            if (appointment.getAppointmentID() == appointmentID && appointment.getStatus().equals("Pending")) {
                appointment.setStatus("Approved");
                System.out.println("Appointment approved successfully!");
                return;
            }
        }
        System.out.println("No pending appointment found for this doctor and patient.");
    }

    public void cancelAppointment(int appointmentID) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentID() == appointmentID) {
                appointments.remove(i);
                System.out.println("Appointment Cancelled Successfully");
                return;
            }
        }
        System.out.println("No Appointment Found with this ID");
    }
}

package UserManagement;

import AppointmentScheduling.Appointment;
import AppointmentScheduling.AppointmentManager;
import ChatAndVideoConsultation.VideoCall;
import DoctorPatientInteraction.Feedback;
import HealthDataHandling.VitalSign;
import HealthDataHandling.VitalsDatabase;
import Helper.Helper;

import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends User {

    private static int feedBackArrayTracker = -1;
    Feedback feedback = new Feedback();
    Feedback[] feedbacksArray = feedback.getFeedbacks();
    AppointmentManager am = new AppointmentManager();
    ArrayList<Appointment> appointments = am.getAppointments();
    Scanner scanner = new Scanner(System.in);
    VitalsDatabase vd = new VitalsDatabase();
    Helper helper = new Helper();
    ArrayList<Patient> patients = new ArrayList<>();

    public static int getFeedBackArrayTracker() {
        return feedBackArrayTracker;
    }

    public void patientsData() {

        System.out.println("\nTotal Patients of Dr. " + this.getName() + " are: " + patients.size() + "\n");

        for (Patient p: patients) {
            System.out.println("Patient Name: " + p.getName());
            System.out.println("Patient Email: " + p.getEmail());
            System.out.println("Patient Contact: " + p.getContactNumber());
            System.out.println("Patient Address: " + p.getAddress());
            System.out.println("Patient DOB: " + p.getDob());
            System.out.println("Patient Health Data: ");
            for (VitalSign vital: vd.getVitals()) {
                if (vital.getPatientID() == p.getUnique_id()) {
                    System.out.println("\t" + vital + "\n");
                }
            }
        }
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void deletePatient(Patient p) {
        patients.remove(p);
    }

    public void provideFeedback() {
        System.out.print("Please enter ID of patient to whom you want to give feedback: ");
        long patientID = scanner.nextLong();
        boolean validID = helper.isValidPatientID(patientID);
        scanner.nextLine();  // to clear buffer

        if (validID) {
            Feedback.setNoOfFeedbacks(Feedback.getNoOfFeedbacks() + 1);
            feedBackArrayTracker++;
            if (Feedback.getNoOfFeedbacks() == 51) {
                feedback.increaseArraySize();
                Feedback.setNoOfFeedbacks(0);
            }

            feedbacksArray[feedBackArrayTracker] = new Feedback();
            System.out.print("Please enter feedback: ");
            String notes = scanner.nextLine();
            feedbacksArray[feedBackArrayTracker].setNotes(notes);
            feedbacksArray[feedBackArrayTracker].setPatientID(patientID);
            feedbacksArray[feedBackArrayTracker].setDoctorID(this.getUnique_id());
            System.out.println("Feedback submitted successfully!\n");
        }else {
            System.out.println("You have entered incorrect Patient ID. Plz enter correct one");
        }
    }

    // Manage Appointments

    // doctor can see only details of his/her appointments
    public void doctorAppointmentDetails() {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorID() == this.getUnique_id()) {
                Doctor doctor = am.getDoctorById(appointment.getDoctorID());
                Patient patient = am.getPatientById(appointment.getPatientID());

                System.out.println("\nAppointment ID: " + appointment.getAppointmentID());
                System.out.println("Appointment Date: " + appointment.getDate());
                System.out.println("Doctor: " + (doctor != null ? doctor.getName() : "Not Found"));
                System.out.println("Patient: " + (patient != null ? patient.getName() : "Not Found"));
                System.out.println("Status: " + appointment.getStatus() + "\n");
            }
        }
    }

    public void approveAppointmentByDoctor() {
        System.out.println("Enter appointment Id: ");
        int appointmentID = scanner.nextInt();

        for (Appointment appointment: appointments) {
            if (appointment.getAppointmentID() == appointmentID && appointment.getStatus().equals("Pending")) {
                appointment.setStatus("Approved");
                System.out.println("Appointment approved successfully!");
                return;
            }
        }
        System.out.println("No pending appointment found for this doctor and patient.");
    }

    public String getDoctorName(long id) {
        StringBuilder name = new StringBuilder();
        Doctor[] doctors = (Doctor[]) User.getRegisteredDoctors();
        int doctorCount = User.getNoOfDoctors();

        for (int i = 0; i < doctorCount; i++) {
            if(doctors[i].getUnique_id() == id) {
                name.append(doctors[i].getName());
            }
        }
        return name.toString();
    }

    public void cancelAppointmentByDoctor() {
        System.out.println("Enter appointment Id: ");
        int appointmentID = scanner.nextInt();

        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentID() == appointmentID) {
                appointments.remove(i);
                System.out.println("Appointment Cancelled Successfully");
                return;
            }
        }
        System.out.println("No Appointment Found with this ID");
    }

    public void startVideoCall() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Plz enter Patient ID: ");
        long patientID = scanner.nextLong();

        scanner.nextLine();

        System.out.println("Plz enter Meeting Link: ");
        String meetLink = scanner.nextLine();

        Patient patient = helper.getPatient(patientID);
        Doctor doctor = helper.getDoctor(this.getUnique_id());

        VideoCall vc = new VideoCall(doctor.getName(), patient.getName(), meetLink);
        vc.start();
        scanner.close();
    }
}

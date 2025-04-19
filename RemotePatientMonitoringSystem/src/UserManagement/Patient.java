package UserManagement;

import AppointmentScheduling.Appointment;
import AppointmentScheduling.AppointmentManager;
import ChatAndVideoConsultation.ChatClient;
import ChatAndVideoConsultation.VideoCall;
import DoctorPatientInteraction.Feedback;
import EmergencyAlertSystem.EmergencyAlert;
import EmergencyAlertSystem.PanicButton;
import HealthDataHandling.VitalSign;
import HealthDataHandling.VitalsDatabase;
import Helper.Helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends User {

    Feedback feedback = new Feedback();
    Feedback[] feedbacksArray = feedback.getFeedbacks();
    int feedbackCount = Feedback.getNoOfFeedbacks();
    AppointmentManager am = new AppointmentManager();
    ArrayList<Appointment> appointments = am.getAppointments();
    VitalsDatabase vd = new VitalsDatabase();
    EmergencyAlert ea = new EmergencyAlert();
    Helper helper = new Helper();
    PanicButton pb = new PanicButton();

    public void uploadVitals() {
        VitalSign vital = vd.storeVitalRecords(this.getUnique_id());
        if (vital != null) {
            ea.checkVitals(vital);
        }
    }

    public void displayVitals() {
        for (VitalSign vital: vd.getVitals()) {
            if (vital.getPatientID() == this.getUnique_id()) {
                System.out.println(vital);
            }
        }
    }

    public void feedbacksCount(int[] counter, boolean isCount) {
        if (!isCount) {
            System.out.println("\nTotal Feedbacks: " + counter[0] + "\n");
        }
        for (int i = 0; i < feedbackCount; i++) {
            if (feedbacksArray[i].getPatientID() == this.getUnique_id()) {
                if (isCount) {
                    counter[0]++;
                }else {
                    System.out.println("Doctor Name: " + feedbacksArray[i].getDoctorName());
                    System.out.println("Doctor Notes: " + feedbacksArray[i].getNotes() + "\n");
                }
            }
        }
    }

    public void pressPanicButton() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = scanner.nextLine();

        pb.patientManuallyTriggerEmergency(this, message);
    }

    public void viewFeedbacks() {
        int[] counter = new int[1];
        feedbacksCount(counter,true);
        feedbacksCount(counter, false);
    }

    public void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date of appointment (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        LocalDate appointmentDate = LocalDate.parse(date);

        System.out.print("Enter Doctor ID: ");
        long doctorID = scanner.nextLong();
        boolean validId = helper.isValidDoctorID(doctorID);

        long patientID = this.getUnique_id();
        String status = "Pending";

        if (validId) {
            appointments.add(new Appointment(appointmentDate, doctorID, patientID, status));
            System.out.println("Appointment scheduled successfully! You will get update" +
                    "on appointment you booked by manager in short time\n");
        }else {
            System.out.println("You have entered incorrect Doctor Id. Plz enter correct one");
        }
    }

    public void realTimeChatting() {
        Patient patient = helper.getPatient(this.getUnique_id());
        Doctor doctor = helper.getDoctor(this.getAssignedTo());

        ChatClient cc = new ChatClient(doctor.getName(), patient.getName(), doctor.getContactNumber());
        cc.start();
    }

    public void startVideoCall() {
        Scanner scanner = new Scanner(System.in);

        Patient patient = helper.getPatient(this.getUnique_id());
        Doctor doctor = helper.getDoctor(this.getAssignedTo());

        System.out.println("Plz enter Meeting Link: ");
        String meetLink = scanner.nextLine();

        VideoCall vc = new VideoCall(doctor.getName(), patient.getName(), meetLink);
        vc.start();
        scanner.close();
    }
}

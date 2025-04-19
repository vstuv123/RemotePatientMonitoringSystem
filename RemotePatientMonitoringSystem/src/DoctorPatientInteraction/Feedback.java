package DoctorPatientInteraction;

import java.util.ArrayList;
import java.util.Scanner;

import AppointmentScheduling.AppointmentManager;
import UserManagement.Doctor;
import UserManagement.Patient;
import UserManagement.User;


public class Feedback {
    private String notes;
    private long doctorID;
    private long patientID;
    private final int MAX_FEEDBACKS = 50;
    private static int noOfFeedbacks = 0;
    private Feedback[] feedbacks = new Feedback[MAX_FEEDBACKS];
    ArrayList<Prescription> prescriptions = new ArrayList<>();
    AppointmentManager ap;
    Doctor[] doctors = (Doctor[]) User.getRegisteredDoctors();
    Patient[] patients = (Patient[]) User.getRegisteredPatients();

    public Feedback[] getFeedbacks() {
        return feedbacks;
    }

    public static int getNoOfFeedbacks() {
        return noOfFeedbacks;
    }

    public static void setNoOfFeedbacks(int noOfFeedbacks) {
        Feedback.noOfFeedbacks = noOfFeedbacks;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public int getMAX_FEEDBACKS() {
        return MAX_FEEDBACKS;
    }

    public void setFeedbacks(Feedback[] feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setPrescriptions(ArrayList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public AppointmentManager getAp() {
        return ap;
    }

    public void setAp(AppointmentManager ap) {
        this.ap = ap;
    }

    public void increaseArraySize() {
        feedbacks = new Feedback[MAX_FEEDBACKS + 50];
    }

    public String getNotes() {
        return notes;
    }

    public long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDoctorName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < User.getNoOfDoctors(); i++) {
            if (doctors[i].getUnique_id() == doctorID) {
                name.append(doctors[i].getName());
            }
        }
        return name.toString();
    }

    public Doctor getDoctor() {
        for (int i = 0; i < User.getNoOfDoctors(); i++) {
            if (doctors[i].getUnique_id() == doctorID) {
                return doctors[i];
            }
        }
        return null;
    }

    public boolean isValidPatientID() {
        for (int i = 0; i < User.getNoOfPatients(); i++) {
            if (patients[i].getUnique_id() == patientID) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidDoctorID() {
        for (int i = 0; i < User.getNoOfDoctors(); i++) {
            if (doctors[i].getUnique_id() == doctorID) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "The name of Doctor is " + getDoctorName() + " and `notes that doctor "
                + " provide are " + getNotes();
    }

    // storing feedbacks
    public void storeFeedback() {
        if (getDoctor() != null) {
            getDoctor().provideFeedback();
        }else {
            System.out.println("Get Doctor is null");
        }
    }

    public void storePrescriptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter Patient ID: ");
        long patientID = scanner.nextLong();
        boolean isValidPatientID = isValidPatientID();
        System.out.print("Please enter Doctor ID: ");
        long doctorID = scanner.nextLong();
        boolean isValidDoctorID = isValidDoctorID();

        if (isValidDoctorID && isValidPatientID) {
            System.out.println("Please enter Instructions: ");
            String instructions = scanner.nextLine();
            System.out.println("Please enter Name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter Dosage: ");
            String dosage = scanner.nextLine();
            System.out.println("Please enter Schedule: ");
            String schedule = scanner.nextLine();
            prescriptions.add(new Prescription(doctorID, patientID, instructions, name,
                    dosage, schedule));
        }else {
            System.out.println("You have entered incorrect Patient or Doctor ID. Plz enter " +
                    "correct one");
        }
    }

    // displaying feedbacks
    public void allFeedbacks() {
        System.out.println("Total Feedbacks are " + (Doctor.getFeedBackArrayTracker() - 1));
        for (int i = 0; i < Doctor.getFeedBackArrayTracker(); i++) {
            Patient pat = ap.getPatientById(feedbacks[i].getPatientID());
            Doctor doc = ap.getDoctorById(feedbacks[i].getDoctorID());
            System.out.println("\nPatient Name: " + ((pat != null) ? pat.getName() : "Not Found"));
            System.out.println("Doctor Name: " + ((doc != null) ? doc.getName() : "Not Found"));
            System.out.println("Feedback Notes: " + feedbacks[i].getNotes() + "\n");
        }
    }
}
package DoctorPatientInteraction;

import java.util.ArrayList;
import java.util.Scanner;

public class MedicalHistory {
    Scanner scanner = new Scanner(System.in);
    Feedback feedback = new Feedback();
    Feedback[] feedbacks = feedback.getFeedbacks();
    ArrayList<Prescription> prescriptions = feedback.getPrescriptions();

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Feedback[] getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Feedback[] feedbacks) {
        this.feedbacks = feedbacks;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(ArrayList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void pastConsultationsOfPatient() {
        System.out.print("Please enter Patient ID: ");
        long patientID = scanner.nextLong();
        System.out.println("The appointments are: ");
        boolean isPresent = false;
        for (int i = 0; i < Feedback.getNoOfFeedbacks(); i++) {
            if (feedbacks[i].getPatientID() == patientID) {
                isPresent = true;
                System.out.println("\t" + feedbacks[i]);
            }
        }
        if (!isPresent) {
            System.out.println("\tNone");
        }
    }

    public void pastPrescriptionsOfPatient() {
        System.out.print("Please enter Patient ID: ");
        long patientID = scanner.nextLong();
        System.out.println("The prescriptions are: ");
        boolean isPresent = false;
        for (Prescription pr: prescriptions) {
            if (pr.getPatientID() == patientID) {
                isPresent = true;
                System.out.println("\t" + pr);
            }
        }
        if (!isPresent) {
            System.out.println("\tNone");
        }
    }
}

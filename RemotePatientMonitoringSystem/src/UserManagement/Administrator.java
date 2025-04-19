package UserManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends User {

    User user = new User();
    Patient[] patients = (Patient[]) User.getRegisteredPatients();
    Doctor[] doctors = (Doctor[]) User.getRegisteredDoctors();
    private static ArrayList<String> logs = new ArrayList<>();

    public static ArrayList<String> getLogs() {
        return logs;
    }

    public static void setLogs(ArrayList<String> logs) {
        Administrator.logs = logs;
    }

    // Doctor Management
    public void addDoctor() {
        user.registerUser("Doctor");
    }

    public void getAllDoctorRecords() {
        int doctorCount = User.getNoOfDoctors();
        System.out.println("\nTotal No Of Doctors: " + doctorCount);
        for (int i = 0; i < doctorCount; i++) {
            System.out.println("\n\tDoctor ID: " + doctors[i].getUnique_id());
            System.out.println("\tDoctor Name: " + doctors[i].getName());
            System.out.println("\tDoctor Email: " + doctors[i].getEmail());
            System.out.println("\tDoctor Contact Number: " + doctors[i].getContactNumber());
            System.out.println("\tDoctor Date Of Birth: " + doctors[i].getDob());
            System.out.println("\tDoctor Address: " + doctors[i].getAddress() + "\n");
        }
    }

    public void getAllPatientRecords() {
        int patientCount = User.getNoOfPatients();
        System.out.println("\nTotal No Of Patients: " + patientCount);
        for (int i = 0; i < patientCount; i++) {
            System.out.println("\n\tPatient ID: " + patients[i].getUnique_id());
            System.out.println("\tPatient Name: " + patients[i].getName());
            System.out.println("\tPatient Email: " + patients[i].getEmail());
            System.out.println("\tPatient Contact Number: " + patients[i].getContactNumber());
            System.out.println("\tPatient Date Of Birth: " + patients[i].getDob());
            System.out.println("\tPatient Address: " + patients[i].getAddress());
            System.out.println("\tPatient Assigned To: " + patients[i].getAssignedTo() + "\n");
        }
    }

    public void updateDoctor(long doctorID) {
        Scanner scanner = new Scanner(System.in);
        int indexToUpdate = -1;

        for(int i = 0; i < User.getNoOfDoctors(); i++) {
            if (doctors[i].getUnique_id() == doctorID && doctors[i].getRole().equals("Doctor")) {
                indexToUpdate = i;
                System.out.print("Enter Doctor's Updated Name: ");
                doctors[i].setName(scanner.nextLine());

                System.out.print("Enter Doctor's Updated Email: ");
                doctors[i].setEmail(scanner.nextLine());
                validation(doctors[i].getEmail(), doctors, false, doctors[i].getRole());

                System.out.print("Enter Doctor's Updated Password: ");
                doctors[i].setPassword(scanner.nextLine());
                System.out.print("Enter Doctor's Updated Contact Number: ");
                doctors[i].setContactNumber(scanner.nextLine());

                System.out.print("Enter Doctor's Updated Date Of Birth (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                doctors[i].setDob(LocalDate.parse(date));

                System.out.print("Enter Doctor's Updated Address: ");
                doctors[i].setAddress(scanner.nextLine());
                System.out.print("Enter Doctor's Updated Role(Admin / Doctor / Patient): ");
                doctors[i].setRole(scanner.nextLine());
                break;
            }
        }
        if (indexToUpdate == -1) {
            System.out.println("Doctor Not Found!");
        }
    }

    public void deleteDoctor(long doctorID) {;
        int indexToDelete = -1;
        int doctorCount = User.getNoOfDoctors();
        for(int i = 0; i < doctorCount; i++) {
            if (doctors[i].getUnique_id() == doctorID && doctors[i].getRole().equals("Doctor")) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete == -1) {
            System.out.println("Doctor Not Found!");
            return;
        }
        for (int i = indexToDelete; i < doctorCount - 1; i++) {
            doctors[i] = doctors[i + 1];
        }
        doctors[doctorCount - 1] = null;
        setDoctorCount(doctorCount - 1);
    }


    // Patient Management
    public void addPatient() {
        user.registerUser("Patient");
        Patient p = (Patient) ObjectStorage.userObject;
        Doctor doctor = helper.getDoctor(p.getAssignedTo());

        doctor.addPatient(p);
    }

    public void updatePatient(long patientID) {
        Scanner scanner = new Scanner(System.in);
        int indexToUpdate = -1;
        long oldAssignedTo = 0;

        for(int i = 0; i < User.getNoOfPatients(); i++) {
            if (patients[i].getUnique_id() == patientID && patients[i].getRole().equals("Patient")) {
                indexToUpdate = i;
                oldAssignedTo = patients[i].getAssignedTo();

                System.out.print("Enter Patient's Updated Name: ");
                patients[i].setName(scanner.nextLine());

                System.out.print("Enter Patient's Updated Email: ");
                patients[i].setEmail(scanner.nextLine());
                validation(patients[i].getEmail(), patients, false, patients[i].getRole());

                System.out.print("Enter Patient's Updated Password: ");
                patients[i].setPassword(scanner.nextLine());
                System.out.print("Enter Patient's Updated Contact Number: ");
                patients[i].setContactNumber(scanner.nextLine());

                System.out.print("Enter Patient's Updated Date Of Birth (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                patients[i].setDob(LocalDate.parse(date));

                System.out.print("Enter Patient's Updated Address: ");
                patients[i].setAddress(scanner.nextLine());
                System.out.println("Enter id of doctor to which patient is assignedTo: ");
                patients[i].setAssignedTo(scanner.nextLong());

                scanner.nextLine();

                System.out.print("Enter Patient's Updated Role(Admin / Doctor / Patient): ");
                patients[i].setRole(scanner.nextLine());
                break;
            }
        }
        if (indexToUpdate == -1) {
            System.out.println("Patient Not Found!");
            return;
        }

        long newAssignedTo = patients[indexToUpdate].getAssignedTo();

        if (oldAssignedTo != newAssignedTo) {
            Doctor oldDoctor = helper.getDoctor(oldAssignedTo);
            Doctor newDoctor = helper.getDoctor(newAssignedTo);

            oldDoctor.deletePatient(patients[indexToUpdate]);
            newDoctor.addPatient(patients[indexToUpdate]);
        }
    }

    public void deletePatient(long patientID) {
        int indexToDelete = -1;
        int patientCount = User.getNoOfPatients();

        for(int i = 0; i < patientCount; i++) {
            if (patients[i].getUnique_id() == patientID && patients[i].getRole().equals("Patient")) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete == -1) {
            System.out.println("Patient Not Found!");
            return;
        }

        Doctor doctor = helper.getDoctor(patients[indexToDelete].getAssignedTo());
        doctor.deletePatient(patients[indexToDelete]);

        for (int i = indexToDelete; i < patientCount - 1; i++) {
            patients[i] = patients[i + 1];
        }
        patients[patientCount - 1] = null;
        setPatientCount(patientCount - 1);
    }

    public void displayLogs() {
        System.out.println("Total Logs in System are " + logs.size() + "\n");
        if (logs.isEmpty()) {
            System.out.println("No logs available!");
            return;
        }
        int counter = 1;
        for (String log: logs) {
            System.out.println("\t" + (counter++) +") " + log);
        }
    }

    public void deleteLog(int position) {
        position -= 1;
        if (position >= 0 && position < logs.size()) {
            logs.remove(position);
            System.out.println("Log Deleted Successfully");
        }else {
            System.out.println("Invalid Position entered. Plz enter correct one");
        }
    }
}

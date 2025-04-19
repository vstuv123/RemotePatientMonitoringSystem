package Helper;

import UserManagement.Doctor;
import UserManagement.Patient;
import UserManagement.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    Doctor[] doctors = (Doctor[]) User.getRegisteredDoctors();
    Patient[] patients = (Patient[]) User.getRegisteredPatients();

    public static String currentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public boolean isValidDoctorID(long id) {
        for (int i = 0; i < User.getNoOfDoctors(); i++) {
            if (doctors[i].getUnique_id() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidPatientID(long id) {
        for (int i = 0; i < User.getNoOfPatients(); i++) {
            if (patients[i].getUnique_id() == id) {
                return true;
            }
        }
        return false;
    }

    public Patient getPatient(long id) {
        for (int i = 0; i < User.getNoOfPatients(); i++) {
            if (patients[i].getUnique_id() == id) {
                return patients[i];
            }
        }
        return null;
    }

    public Doctor getDoctor(long id) {
        for (int i = 0; i < User.getNoOfDoctors(); i++) {
            if (doctors[i].getUnique_id() == id) {
                return doctors[i];
            }
        }
        return null;
    }
}



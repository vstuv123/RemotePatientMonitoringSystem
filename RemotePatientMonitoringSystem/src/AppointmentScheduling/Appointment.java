package AppointmentScheduling;

import java.time.LocalDate;

public class Appointment {
    private static int idGenerator = 101;
    private int appointmentID;
    private LocalDate date;
    private long doctorID;
    private long patientID;
    private String status;

    public Appointment(LocalDate date, long doctorID, long patientID, String status) {
        this.appointmentID = idGenerator++;
        this.date = date;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.status = status;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment Date: " + getDate() + ", Doctor ID: " + getDoctorID() + ", " +
                "Patient ID: " + getPatientID() + ", Status: " + getStatus();
    }
}

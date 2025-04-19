package DoctorPatientInteraction;

import java.time.LocalDate;

public class Prescription {
    private static int idGenerator = 5001;  // Start from a unique ID
    private int prescriptionID;
    private long doctorID;
    private long patientID;
    private LocalDate dateIssued;
    private String instructions;
    private String name;
    private String dosage;
    private String schedule;

    public Prescription(long doctorID, long patientID, String instructions, String name,
                        String dosage, String schedule) {
        this.prescriptionID = idGenerator++;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.dateIssued = LocalDate.now();
        this.instructions = instructions;
        this.name = name;
        this.dosage = dosage;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        Prescription.idGenerator = idGenerator;
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }

    public void setDateIssued(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getPrescriptionID() {
        return prescriptionID;
    }

    public long getDoctorID() {
        return doctorID;
    }

    public long getPatientID() {
        return patientID;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return "Prescription ID: " + prescriptionID + "\nDoctor ID: " + doctorID +
                "\nPatient ID: " + patientID + "\nDate Issued: " + dateIssued +
                "\nInstructions: " + instructions + "\nMedications: " + name +
                " (" + dosage + ", " + schedule + ")";
    }
}

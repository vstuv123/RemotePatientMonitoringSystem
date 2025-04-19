package EmergencyAlertSystem;

import HealthDataHandling.VitalSign;
import Helper.Helper;
import UserManagement.Doctor;
import UserManagement.Patient;

public class EmergencyAlert {

    NotificationService ns = new NotificationService();
    Helper helper = new Helper();


    public void checkVitals(VitalSign vitals) {
        if (vitals.getHeartRate() > VitalsThreshold.HEART_RATE_MAX ||
                vitals.getHeartRate() < VitalsThreshold.HEART_RATE_MIN) {
            triggerAlert(vitals.getPatientID(), "Heart Rate critical: " + vitals.getHeartRate());
        }

        if (vitals.getTemperature() > VitalsThreshold.TEMPERATURE_MAX ||
                vitals.getTemperature() < VitalsThreshold.TEMPERATURE_MIN) {
            triggerAlert(vitals.getPatientID(), "Temperature critical: " + vitals.getTemperature());
        }

        if (vitals.getBloodPressureSystolic() > VitalsThreshold.SYSTOLIC_BP_MAX ||
                vitals.getBloodPressureSystolic() < VitalsThreshold.SYSTOLIC_BP_MIN) {
            triggerAlert(vitals.getPatientID(), "Systolic Blood Pressure critical: " + vitals.getBloodPressureSystolic());
        }

        if (vitals.getBloodPressureDiastolic() > VitalsThreshold.DIASTOLIC_BP_MAX ||
                vitals.getBloodPressureDiastolic() < VitalsThreshold.DIASTOLIC_BP_MIN) {
            triggerAlert(vitals.getPatientID(), "Diastolic Blood Pressure critical: " + vitals.getBloodPressureDiastolic());
        }

        if (vitals.getOxygenLevel() < VitalsThreshold.OXYGEN_LEVEL_MIN) {
            triggerAlert(vitals.getPatientID(), "Oxygen Level critical: " + vitals.getOxygenLevel());
        }
    }

    private void triggerAlert(long patientId, String message) {
        Patient patient = helper.getPatient(patientId);
        if (patient != null) {
            long doctorId = patient.getAssignedTo();
            Doctor doctor = helper.getDoctor(doctorId);
            String subject = "🚑 Emergency Alert: Patient in Critical Condition!";
            String htmlMessage = "<html>" +
                    "<body style='font-family: Arial, sans-serif;'>" +
                    "<h2 style='color: red;'>🚨 Critical Vitals Detected!</h2>" +
                    "<p>Dear Doctor" + doctor.getName() + ",</p>" +
                    "<p style='color: black;'>One of your patients with name " + patient.getName() +
                    " and email " + patient.getEmail() + " has reported <b>critical vital signs</b>:</p>" +
                    "<ul>" +
                    "<li><b>" + message + "</b></li>" +
                    "</ul>" +
                    "<p style='color: darkred; font-weight: bold;'>Immediate action is recommended!</p>" +
                    "<p>Stay Safe, <br> Remote Patient Monitoring System</p>" +
                    "</body>" +
                    "</html>";

            ns.sendEmail(doctor.getEmail(), subject, htmlMessage);
        }
    }
}


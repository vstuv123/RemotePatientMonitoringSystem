package EmergencyAlertSystem;

import Helper.Helper;
import UserManagement.Doctor;
import UserManagement.Patient;

public class PanicButton {

    Helper helper = new Helper();
    NotificationService nf = new NotificationService();

    public void patientManuallyTriggerEmergency(Patient patient, String message) {
       long doctorId = patient.getAssignedTo();
        Doctor doctor = helper.getDoctor(doctorId);

        String to = doctor.getEmail();
        String subject = "🚑 Emergency Alert: Patient in very Critical Condition!";
        String content = "<html>\"" +
                "\"<body style='font-family: Arial, sans-serif;'>\" +\n" +
                "\"<h2 style='color: red;'>🚨 Patient has pressed Panic Button</h2>\" +\n" +
                "\"<p>Dear Doctor\" + doctor.getName() + \",</p>\" +\n" +
                "\"<p style='color: black;'>One of your patients with name \" + patient.getName() +\n" +
                "\" and email \" + patient.getEmail() + \" has been in<b>critical state</b>:</p>\" +\n" +
                "\"<ul>\" +\n" +
                "\"<li><b>\"" + message + "\"</b></li>\" +\n" +
                "\"</ul>\" +\n" +
                "\"<p style='color: darkred; font-weight: bold;'>Immediate action is recommended!</p>\" +\n" +
                "\"<p>Stay Safe, <br> Remote Patient Monitoring System</p>\" +\n" +
                "\"</body>\" +\n" +
                "\"</html>\"";

        nf.sendEmail(to, subject, content);
    }
}

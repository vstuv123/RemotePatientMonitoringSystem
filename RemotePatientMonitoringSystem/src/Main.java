
import HealthDataHandling.VitalsDatabase;
import NotificationsAndReminders.*;
import UserManagement.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Administrator ad = new Administrator();
        User user = new User();

        int choice;
        do {
            System.out.println("\n--- Remote Patient Monitoring System ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Displaying Doctor Records");
            System.out.println("3. Add Patient");
            System.out.println("4. Displaying Patient Records");
            System.out.println("5. Schedule Appointment");
            System.out.println("6. Doctor Feedback");
            System.out.println("7. Upload Vitals");
            System.out.println("8. Process Vitals");
            System.out.println("9. Press Panic Button Patient");
            System.out.println("10. Send Reminder");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    ad.addDoctor();
                    break;
                case 2:
                    ad.getAllDoctorRecords();
                    break;
                case 3:
                    ad.addPatient();
                    break;
                case 4:
                    ad.getAllPatientRecords();
                    break;
                case 5:
                    user.loginUser("Patient");
                    Patient p = (Patient) SessionStorage.loggedInUser;
                    p.scheduleAppointment();
                    break;
                case 6:
                    user.loginUser("Doctor");
                    Doctor d = (Doctor) SessionStorage.loggedInUser;
                    d.provideFeedback();
                    break;
                case 7:
                    user.loginUser("Patient");
                    Patient pt = (Patient) SessionStorage.loggedInUser;
                    pt.uploadVitals();
                    break;
                case 8:
                    VitalsDatabase vd = new VitalsDatabase();
                    vd.retrieveVitalRecords();
                    break;
                case 9:
                    user.loginUser("Patient");
                    Patient pts = (Patient) SessionStorage.loggedInUser;
                    pts.pressPanicButton();
                    break;
                case 10:
                    Notifiable email = new EmailNotification();
                    Notifiable sms = new SMSNotification();

                    ArrayList<Notifiable> notifiers = new ArrayList<>();
                    notifiers.add(email);
                    notifiers.add(sms);

                    ContactInfo contact = new ContactInfo("aashraf.bsds24seecs@seecs.edu.pk", "+923240055671");

                    ReminderService service = new ReminderService(notifiers);
                    service.sendReminder(contact, "Meeting Reminder", "Don't forget the meeting at 3 PM.");
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }
}
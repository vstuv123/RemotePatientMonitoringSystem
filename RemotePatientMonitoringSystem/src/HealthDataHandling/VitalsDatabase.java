package HealthDataHandling;

import Helper.Helper;

import java.util.ArrayList;
import java.util.Scanner;

public class VitalsDatabase {

    private ArrayList<VitalSign> vitals = new ArrayList<>();
    Helper helper = new Helper();

    public ArrayList<VitalSign> getVitals() {
        return vitals;
    }

    public void setVitals(ArrayList<VitalSign> vitals) {
        this.vitals = vitals;
    }

    public VitalSign storeVitalRecords(long patientID) {
        Scanner scanner = new Scanner(System.in);
        VitalSign vital = null;

        if (helper.isValidPatientID(patientID)) {
            System.out.print("Enter Heart Rate (BPM): ");
            int heartRate = scanner.nextInt();
            System.out.print("Enter Oxygen Level (%): ");
            int oxygenLevel = scanner.nextInt();

            scanner.nextLine();

            System.out.print("Enter Blood Pressure (Systolic): ");
            int bloodPressureSystolic = scanner.nextInt();
            System.out.print("Enter Blood Pressure (Diastolic): ");
            int bloodPressureDiastolic = scanner.nextInt();

            System.out.print("Enter Temperature (°F): ");
            double temperature = scanner.nextDouble();

            System.out.print("Enter Weight (kg): ");
            double weight = scanner.nextDouble();

            System.out.print("Enter Height (feet): ");
            double height = scanner.nextDouble();

            scanner.nextLine();

            String timestamp = Helper.currentTimestamp();

            vital = new VitalSign(patientID, heartRate, oxygenLevel, temperature, bloodPressureSystolic,
                    bloodPressureDiastolic, weight, height, timestamp);

            vitals.add(vital);
            System.out.println("\n\tVitals Recorded Successfully\n");
        }else {
            System.out.println("Invalid Patient Id error. Plz try again");
        }
        return vital;
    }

    public void retrieveVitalRecords() {
        System.out.println("\nTotal Vital Records Stored are " + vitals.size());
        int counter = 1;
        for (VitalSign vital: vitals) {
            System.out.println("\t" + (counter++) + ") " + vital.toString());
        }
        System.out.println();
    }
}

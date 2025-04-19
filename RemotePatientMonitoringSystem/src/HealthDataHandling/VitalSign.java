package HealthDataHandling;


public class VitalSign {
    private long patientID;
    private int heartRate;
    private int oxygenLevel;
    private int bloodPressureSystolic;
    private int bloodPressureDiastolic;
    private double temperature;
    private double weight;
    private double height;
    private String timestamp;  // date and time when vitals were recorded

    public VitalSign(long patientID, int heartRate, int oxygenLevel, double temperature, int
            bloodPressureSystolic, int bloodPressureDiastolic, double weight, double height,
                     String timestamp) {
        this.patientID = patientID;
        this.heartRate = heartRate;
        this.oxygenLevel = oxygenLevel;
        this.temperature = temperature;
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.bloodPressureDiastolic = bloodPressureDiastolic;
        this.weight = weight;
        this.height = height;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getPatientID() {
        return patientID;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public double getWeight() {
        return weight;
    }

    public int getBloodPressureSystolic() {
        return bloodPressureSystolic;
    }

    public int getBloodPressureDiastolic() {
        return bloodPressureDiastolic;
    }

    public double getHeight() {
        return height;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "PatientID: " + getPatientID() + ", HeartRate: " + getHeartRate() + ", OxygenLevel: " + getOxygenLevel() +
                ", BP: " + getBloodPressureSystolic() + "/" + getBloodPressureDiastolic() +
                ", Temp: " + getTemperature() + ", Weight: " + getWeight() + ", Height: "
                + getHeight() + ", Timestamp: " + getTimestamp();
    }
}

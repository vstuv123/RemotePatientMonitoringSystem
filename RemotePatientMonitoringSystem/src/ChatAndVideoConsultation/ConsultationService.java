package ChatAndVideoConsultation;

public abstract class ConsultationService {
    protected String doctorName;
    protected String patientName;

    public ConsultationService(String doctorName, String patientName) {
        this.doctorName = doctorName;
        this.patientName = patientName;
    }

    public abstract void start();
}

package ChatAndVideoConsultation;

public class VideoCall extends ConsultationService {
    private String meetLink;

    public VideoCall(String doctorName, String patientName, String meetLink) {
        super(doctorName, patientName);
        this.meetLink = meetLink;
    }

    public String getMeetLink() {
        return meetLink;
    }

    @Override
    public void start() {
        System.out.println("Starting video consultation for " + patientName + " with Dr. " + doctorName);
        System.out.println("Join the video call at: " + this.getMeetLink());
    }
}


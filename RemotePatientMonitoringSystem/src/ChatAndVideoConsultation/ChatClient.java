package ChatAndVideoConsultation;

public class ChatClient extends ConsultationService {
    private String whatsappNumber;

    public ChatClient(String doctorName, String patientName, String whatsappNumber) {
        super(doctorName, patientName);
        this.whatsappNumber = whatsappNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    @Override
    public void start() {
        System.out.println("Starting WhatsApp Chat between " + patientName + " and Dr. " + doctorName);
        String message = "Hello Dr. " + doctorName + ", this is " + patientName;
        String url = "https://wa.me/" + this.getWhatsappNumber() + "?text=" + message.replace(" ", "%20");
        System.out.println("Open this link to start chat: " + url);
    }
}


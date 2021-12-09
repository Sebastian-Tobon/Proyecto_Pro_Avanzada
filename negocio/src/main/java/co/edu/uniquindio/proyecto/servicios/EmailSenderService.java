package co.edu.uniquindio.proyecto.servicios;

public interface EmailSenderService {
    void sendMail(String from, String to, String subject, String body);
}

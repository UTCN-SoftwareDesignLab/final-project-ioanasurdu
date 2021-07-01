package project.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class Email {
    private final JavaMailSender javaMailSender;

    public void graded(String grade) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(grade);
        simpleMailMessage.setSubject("School- Grade");
        simpleMailMessage.setText("You received a new grade.");
        javaMailSender.send(simpleMailMessage);
    }
}

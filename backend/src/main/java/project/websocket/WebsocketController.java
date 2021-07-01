package project.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {
    @MessageMapping("/graded")
    @SendTo("/admin/graded")
    public Websocket graded(Message message) throws Exception {
        return new Websocket(message.getDate() + " | Student " + message.getStudent() + "received grade " + message.getGrade() + " at subject" + message.getSubject() + "!");

    }
}

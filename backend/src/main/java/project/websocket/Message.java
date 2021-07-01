package project.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Message {
    private String student;
    private String date;
    private String grade;
    private String subject;
}

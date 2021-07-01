package project.grade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long id;
    private String date;
    private int grade;
    private String subject;
    private String student;
}

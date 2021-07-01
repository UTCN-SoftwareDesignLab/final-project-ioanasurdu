package project.grade.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import project.grade.dto.GradeDTO;
import project.grade.model.Grade;
import project.grade.model.Subject;
import project.student.model.Student;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-01T15:31:07+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class GradeMapperImpl implements GradeMapper {

    @Override
    public GradeDTO fromGrade(Grade grade) {
        if ( grade == null ) {
            return null;
        }

        GradeDTO gradeDTO = new GradeDTO();

        gradeDTO.setId( grade.getId() );
        if ( grade.getDate() != null ) {
            gradeDTO.setDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( grade.getDate() ) );
        }
        gradeDTO.setGrade( grade.getGrade() );

        return gradeDTO;
    }

    @Override
    public Grade toGrade(GradeDTO gradeDTO) {
        if ( gradeDTO == null ) {
            return null;
        }

        Grade grade = new Grade();

        grade.setSubject( gradeDTOToSubject( gradeDTO ) );
        grade.setStudent( gradeDTOToStudent( gradeDTO ) );
        grade.setId( gradeDTO.getId() );
        if ( gradeDTO.getDate() != null ) {
            grade.setDate( LocalDateTime.parse( gradeDTO.getDate() ) );
        }
        grade.setGrade( gradeDTO.getGrade() );

        return grade;
    }

    protected Subject gradeDTOToSubject(GradeDTO gradeDTO) {
        if ( gradeDTO == null ) {
            return null;
        }

        Subject subject = new Subject();

        return subject;
    }

    protected Student gradeDTOToStudent(GradeDTO gradeDTO) {
        if ( gradeDTO == null ) {
            return null;
        }

        Student student = new Student();

        return student;
    }
}

package project.student.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import project.student.dto.StudentDTO;
import project.student.model.Student;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-01T15:31:07+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student toStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( studentDTO.getId() );
        student.setCnp( studentDTO.getCnp() );
        student.setEmail( studentDTO.getEmail() );
        student.setName( studentDTO.getName() );
        student.setAddress( studentDTO.getAddress() );

        return student;
    }

    @Override
    public StudentDTO fromStudent(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId( student.getId() );
        studentDTO.setCnp( student.getCnp() );
        studentDTO.setEmail( student.getEmail() );
        studentDTO.setName( student.getName() );
        studentDTO.setAddress( student.getAddress() );

        return studentDTO;
    }
}

package project.student.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import project.student.dto.StudentDTO;
import project.student.model.Student;
import project.user.dto.UserDTO;
import project.user.model.User;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(StudentDTO studentDTO);
    StudentDTO fromStudent(Student student);
}

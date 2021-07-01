package project.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.grade.service.GradeService;
import project.student.dto.StudentDTO;
import project.student.mapper.StudentMapper;
import project.student.model.Student;
import project.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GradeService gradeService;

    public List<StudentDTO> allStudents() {
        return studentRepository.findAll()
                .stream().map(studentMapper::fromStudent)
                .collect(toList());
    }

    public StudentDTO findById(Long id) {
        return studentRepository.findById(id).map(studentMapper::fromStudent)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + id));
    }

    public Student findByName(String name) {
        return studentRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + name));
    }

    public void create(StudentDTO studentDTO) {
        studentRepository.save(studentMapper.toStudent(studentDTO));
    }

    public void edit(StudentDTO studentDTO) {
        create(studentDTO);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}

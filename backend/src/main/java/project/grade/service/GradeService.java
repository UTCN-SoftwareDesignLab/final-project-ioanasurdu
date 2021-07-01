package project.grade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.Constants;
import project.grade.dto.GradeDTO;
import project.grade.mapper.GradeMapper;
import project.grade.model.ESubject;
import project.grade.model.Grade;
import project.grade.model.Subject;
import project.grade.repository.GradeRepository;
import project.grade.repository.SubjectRepository;
import project.student.model.Student;
import project.student.service.StudentService;
import project.user.dto.UserDTO;
import project.user.model.ERole;
import project.user.model.Role;
import project.user.model.User;
import project.user.repository.UserRepository;
import project.user.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class GradeService {
    private GradeRepository gradeRepository;
    private GradeMapper gradeMapper;
    private StudentService studentService;
    private UserService userService;
    private UserRepository userRepository;
    private SubjectRepository subjectRepository;

    public List<GradeDTO> allGrades() {
        return gradeRepository.findAll()
                .stream().map(gradeMapper::fromGrade)
                .collect(toList());
    }

    public GradeDTO findById(Long id) {
        return gradeRepository.findById(id).map(gradeMapper::fromGrade)
                .orElseThrow(() -> new EntityNotFoundException("Grade not found: " + id));
    }

    public GradeDTO create(GradeDTO gradeDTO) {
        DateTimeFormatter formatter = Constants.getDateTimeFormat();
        LocalDateTime formatted = LocalDateTime.parse(gradeDTO.getDate(), formatter);
        Student student = studentService.findByName(gradeDTO.getStudent());
        Grade grade = Grade.builder()
                .date(formatted)
                .grade(gradeDTO.getGrade())
                .student(student)
                .build();
        Subject subject;
        subject = subjectRepository.findByName(ESubject.valueOf(gradeDTO.getSubject()))
            .orElseThrow(() -> new RuntimeException("Cannot find subject: " + gradeDTO.getSubject()));
        grade.setSubject(subject);
        return gradeMapper.fromGrade(gradeRepository.save(grade));
    }

    public void edit(GradeDTO gradeDTO){
        create(gradeDTO);
    }

    public void delete(Long id) {
        gradeRepository.deleteById(id);
    }

}

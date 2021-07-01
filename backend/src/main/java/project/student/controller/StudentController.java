package project.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.student.dto.StudentDTO;
import project.student.service.StudentService;

import java.util.List;

import static project.UrlMapping.STUDENT;

@RestController
@RequestMapping(STUDENT)
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> allStudents() {
        return studentService.allStudents();
    }

    @PostMapping
    public void create(@RequestBody StudentDTO studentDTO) {
        studentService.create(studentDTO);
    }

    @PatchMapping
    public void edit(@RequestBody StudentDTO studentDTO) {
        studentService.edit(studentDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @GetMapping("/{id}")
    public StudentDTO findByStudentId(@PathVariable Long id) {
        return studentService.findById(id);
    }
}
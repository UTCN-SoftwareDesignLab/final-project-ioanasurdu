package project.grade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.Constants;
import project.grade.dto.GradeDTO;
import project.grade.model.Grade;
import project.grade.service.GradeService;
import java.util.List;

import static project.UrlMapping.GRADE;

@RestController
@RequestMapping(GRADE)
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @GetMapping
    public List<GradeDTO> allGrades() {
        return gradeService.allGrades();
    }

    @PostMapping
    public GradeDTO create(@RequestBody GradeDTO gradeDTO) {
        return gradeService.create(gradeDTO);
    }

    @PatchMapping
    public void edit(@RequestBody GradeDTO gradeDTO) {
        gradeService.edit(gradeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gradeService.delete(id);
    }

    @GetMapping("/{id}")
    public GradeDTO findById(@PathVariable Long id) {
        return gradeService.findById(id);
    }
}

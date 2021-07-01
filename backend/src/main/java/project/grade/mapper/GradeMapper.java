package project.grade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import project.grade.dto.GradeDTO;
import project.grade.model.Grade;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    @Mappings({
            @Mapping(source = "subject.name", target = "subject", ignore = true),
            @Mapping(source = "student.name", target = "student", ignore = true)
    })
    GradeDTO fromGrade(Grade grade);
    @Mappings({
            @Mapping(source = "subject", target = "subject.name", ignore = true),
            @Mapping(source = "student", target = "student.name", ignore = true)
    })
    Grade toGrade(GradeDTO gradeDTO);
}

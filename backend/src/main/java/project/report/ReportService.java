package project.report;

import project.grade.dto.GradeDTO;

import java.io.IOException;
import java.util.List;

public interface ReportService {
    String export(List<GradeDTO> grades) throws IOException;

    ReportType getType();
}

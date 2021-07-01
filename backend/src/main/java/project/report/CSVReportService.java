package project.report;

import org.springframework.stereotype.Service;
import project.grade.dto.GradeDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static project.report.ReportType.CSV;

@Service
public class CSVReportService implements ReportService {
    @Override
    public String export(List<GradeDTO> grades) {
        String fileName = "report.csv";
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            bw.write("GRADE, SUBJECT, DATE\n");
            for(GradeDTO grade: grades) {
                StringBuilder sb = new StringBuilder();
                sb.append(grade.getGrade());
                sb.append(",");
                sb.append(grade.getStudent());
                sb.append(",");
                sb.append(grade.getSubject());
                sb.append("\n");
                bw.write(sb.toString());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
    @Override
    public ReportType getType() {
        return CSV;
    }
}
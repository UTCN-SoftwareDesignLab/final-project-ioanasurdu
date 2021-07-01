package project.report;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import project.grade.dto.GradeDTO;

import java.io.IOException;
import java.util.List;
import static project.report.ReportType.PDF;

@Service
public class PdfReportService implements ReportService {
    @Override
    public String export(List<GradeDTO> grades) {
        String fileName = "report.pdf";

        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setLeading(18.5f);
            content.setFont(PDType1Font.TIMES_BOLD, 16);
            content.newLineAtOffset(25, 700);
            content.showText("Grades");
            content.newLine();

            for (GradeDTO gradeDTO : grades) {
                content.showText(gradeDTO.getGrade() + ", " + gradeDTO.getSubject() + ", " + gradeDTO.getDate());
                content.newLine();
            }
            content.endText();
            content.close();
            doc.save(fileName);

            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public ReportType getType() {
        return PDF;
    }
}
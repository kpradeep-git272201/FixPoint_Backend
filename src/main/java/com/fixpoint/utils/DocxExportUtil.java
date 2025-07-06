package com.fixpoint.utils;

import com.fixpoint.module.tracker.dtos.IssueResponseDTO;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DocxExportUtil {
    public static byte[] exportMPRDocx(List<IssueResponseDTO> issues, Map<String, String> data) throws IOException {
        XWPFDocument document = new XWPFDocument();

        // Heading
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = title.createRun();
        run.setText(data.get("header"));
        run.setBold(true);
        run.setFontSize(16);

        // Personal Info
        addSimpleParagraph(document, data.get("name"));
        addSimpleParagraph(document, "BAS ID: "+data.get("basId"));
        addSimpleParagraph(document, data.get("designation")+", "+data.get("vendorName"));
        document.createParagraph(); // spacing

        // Table Header
        XWPFTable table = document.createTable();
        XWPFTableRow header = table.getRow(0);
        header.getCell(0).setText("Sr. No");
        header.addNewTableCell().setText("Application worked upon");
        header.addNewTableCell().setText("Description");
        header.addNewTableCell().setText("Task Type");
        header.addNewTableCell().setText("Assigned date");
        header.addNewTableCell().setText("Completed date");
        header.addNewTableCell().setText("Remark");

        // Table Data
        int srNo = 1;
        for (IssueResponseDTO issue : issues) {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(String.valueOf(srNo++));
            row.getCell(1).setText(nonNull(issue.getProjectCode()));

            StringBuilder desc = new StringBuilder();
            if (issue.getTitle() != null) desc.append(issue.getTitle()).append("\n");
            if (issue.getDescription() != null) desc.append(issue.getDescription());
            row.getCell(2).setText(desc.toString());

            row.getCell(3).setText(nonNull(issue.getType()));
            row.getCell(4).setText(issue.getStartDate() != null ? issue.getStartDate().toString() : "-");
            row.getCell(5).setText(issue.getEndDate() != null ? issue.getEndDate().toString() : "-");
            row.getCell(6).setText(nonNull(issue.getRemarks()));
        }

        // Export
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        document.close();

        return out.toByteArray();
    }

    private static void addSimpleParagraph(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        p.createRun().setText(text);
    }

    private static String nonNull(String value) {
        return value != null ? value : "-";
    }
}

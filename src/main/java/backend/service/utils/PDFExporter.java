package backend.service.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import backend.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFExporter {

    private static Logger logger = LoggerFactory.getLogger(PDFExporter.class);

    public static ByteArrayInputStream createPDF(List<Product> products) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Products", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(3);
            Stream.of("Name", "Price", "Category").forEach(headerTitle ->
            {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });

            for (Product food : products) {
                PdfPCell idCell = new PdfPCell(new Phrase(food.getName()));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(food.getPrice().toString()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(food.getCategory()));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            logger.error(e.toString());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
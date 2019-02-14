package com.cmcglobal.utils;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cmcglobal.entity.Exam;
import com.cmcglobal.entity.ExamQuestion;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ExportExamPDF extends AbstractPdfView {

  Font font = new Font(Font.HELVETICA, 12, Font.BOLDITALIC);
  Font bold = new Font(Font.HELVETICA, 12, Font.BOLD);
  Font normal = new Font(Font.HELVETICA, 12, Font.NORMAL);

  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document document,
      PdfWriter writer, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    Exam exam = (Exam) model.get("exam");
    String title = exam.getTitle();
    String number = String.valueOf(exam.getNumberOfQuestion());
    String cate = exam.getCategory().getCategoryName();
    int duration = Math.round(exam.getDuration());

    document.setPageSize(PageSize.A4);
    Date date = new Date();

    response.setHeader("Content-Disposition", "attachment; filename=\"" + title
        + "_export_" + date.getTime() + ".pdf\"");

    // header
    Paragraph phN = new Paragraph(title,
        FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC));
    phN.setAlignment(Rectangle.ALIGN_CENTER);
    document.add(phN);

    PdfPTable tabletmp = new PdfPTable(1);
    tabletmp.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    tabletmp.setWidthPercentage(100);

    PdfPTable table = new PdfPTable(2);
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_JUSTIFIED);
    float[] colWidths1 = { 75, 25 };
    table.setWidths(colWidths1);

    // header left
    table.addCell(getHeaderLeft(cate, number));

    // header right
    table.addCell(getHeaderRight(duration));

    tabletmp.addCell(table);
    tabletmp.setSpacingAfter(10);
    document.add(tabletmp);

    // content question
    int i = 1;
    for (ExamQuestion ex : exam.getExamQuestions()) {
      String content = ex.getQuestion().getContent();
      Paragraph title1 = new Paragraph("Question " + i + ": " + content, font);
      document.add(title1);
      com.lowagie.text.List l = new com.lowagie.text.List(
          com.lowagie.text.List.ALPHABETICAL);
      String[] choiceOrder = ex.getChoiceOrder().split("\\s");
      for (int k = 0; k < choiceOrder.length; k++) {
        int j = Integer.valueOf(choiceOrder[k]);
        String s = ex.getQuestion().getAnswers().get(j - 1).getContent()
            .toString();
        l.add(new ListItem(s));
      }
      i++;
      document.add(l);
    }

  }

  public PdfPCell getHeaderLeft(String cate, String number) {
    Chunk chunkEmailLabal = new Chunk("\nCategory: ", normal);
    Phrase phEmailLabal = new Phrase(chunkEmailLabal);
    Chunk chunkEmail = new Chunk(cate, bold);
    Phrase phEmail = new Phrase(chunkEmail);
    Chunk chunkNumberLabal = new Chunk("\nNumber question: ", normal);
    Phrase phNumberLabal = new Phrase(chunkNumberLabal);
    Chunk chunkNumber = new Chunk(number, bold);
    Phrase phNumber = new Phrase(chunkNumber);
    Phrase phFullName = new Phrase(
        "\nFull Name: ..........................................", normal);
    Phrase phSubject = new Phrase(
        "\nSubject: ..............................................", normal);

    Paragraph phN = new Paragraph();
    phN.add(phEmailLabal);
    phN.add(phEmail);
    phN.add(phNumberLabal);
    phN.add(phNumber);
    phN.add(phFullName);
    phN.add(phSubject);

    PdfPCell cellLeft = new PdfPCell();
    cellLeft.setBorder(Rectangle.NO_BORDER);
    cellLeft.addElement(phN);
    return cellLeft;
  }

  public PdfPCell getHeaderRight(int duration) {
    Chunk chunkDurationLabal = new Chunk("\nDuration: ", normal);
    Phrase phDurationLabal = new Phrase(chunkDurationLabal);
    Chunk chunkDuration = new Chunk(duration + "p", bold);
    Phrase phDuration = new Phrase(chunkDuration);
    Chunk chunkDateLabal = new Chunk("\nDate: ........................",
        normal);
    Phrase phDateLabal = new Phrase(chunkDateLabal);

    Paragraph phN = new Paragraph();
    phN.add(phDurationLabal);
    phN.add(phDuration);
    phN.add(phDateLabal);

    PdfPCell cellRight = new PdfPCell();
    cellRight.setBorder(Rectangle.NO_BORDER);
    cellRight.addElement(phN);
    return cellRight;
  }

}

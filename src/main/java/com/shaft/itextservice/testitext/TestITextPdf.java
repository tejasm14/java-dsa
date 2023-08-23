package com.shaft.itextservice.testitext;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.net.URISyntaxException;

public class TestITextPdf {

    public static void main(String[] args) {

        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Test"+System.currentTimeMillis()+".pdf"));
            // step 2: we open the document
            document.open();
            document.add(new Paragraph("Until recently, the prevailing view assumed lorem ipsum was born as a"));
            Paragraph paragraph = new Paragraph("This is google link");

            // Creates a new anchor that link to external website
            // and add this anchor to the paragraph.

            Anchor anchor = new Anchor("Google");
            anchor.setReference("https://www.google.com/");
            paragraph.add(anchor);
            document.add(paragraph);
            document.newPage();

            Chunk chunk = new Chunk("Contact information");
            chunk.setLocalGoto("contact");
            chunk.setLocalDestination("abc");
            document.add(new Paragraph(chunk));
            document.newPage();

            Chunk chunk2 = new Chunk("Contact info");
            chunk2.setLocalDestination("contact");
            chunk2.setLocalGoto("abc");
            document.add(new Paragraph(chunk2));

            Chapter chapter = new Chapter(new Paragraph(chunk2),1);
            chapter.setNumberDepth(0);
            document.add(chapter);

            document.add(new Paragraph("Until recently, the prevailing view assumed lorem ipsum was born as a"));
            Chunk mail = new Chunk("xxxx@xx.com").setAnchor("mailto:xxxx@xx.com");
            document.add(mail);
            document.newPage();
            document.add(new Paragraph("Until recently, the prevailing view assumed lorem ipsum was born as a"));
            document.newPage();
            document.add(new Paragraph("Until recently, the prevailing view assumed lorem ipsum was born as a"));
            document.newPage();
            document.add(new Paragraph("Until recently, the prevailing view assumed lorem ipsum was born as a"));
            document.newPage();
            document.add(new Paragraph("Until recently, the prevailing view assumed lorem ipsum was born as a"));
            document.newPage();
            document.add(new Paragraph("Until recently, the prevailing view assumed lorem ipsum was born as a"));
            document.newPage();
            document.add(new Paragraph("Until recently, the prevailing view assumed lorem ipsum was born as a"));

            System.out.println("created");
            document.close();
        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }


    }
}
/*

try (Document document = new Document()) {
        PdfWriter.getInstance(document, new FileOutputStream("parseHelloWorld.pdf"));
        // step 2: we open the document
        document.open();*/

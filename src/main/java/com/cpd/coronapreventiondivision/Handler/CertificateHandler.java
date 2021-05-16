package com.cpd.coronapreventiondivision.Handler;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CertificateHandler {

    public static boolean generatePDF(){
        try {
            String fileName = "src/main/resources/certificateTemplates/certificate.html";
            Path path = Path.of(fileName);
            String content = Files.readString(path);

            HtmlConverter.convertToPdf(content, new FileOutputStream("certificate.pdf"));

            System.out.println("PDF generated");

            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

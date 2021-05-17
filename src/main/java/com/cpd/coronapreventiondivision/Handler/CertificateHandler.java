package com.cpd.coronapreventiondivision.Handler;

import com.cpd.coronapreventiondivision.Model.Appointment;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CertificateHandler {

    public static boolean generatePDF(Appointment appointment){
        try {
            String fileName = "src/main/resources/certificateTemplates/certificate.html";
            Path path = Path.of(fileName);
            String content = Files.readString(path);
            String description = "";
            switch (appointment.getResult()){
                case POSITIVE:
                    description = "Patients who have a positive COVID-19 test result may now have\n" +
                            " treatment options. Treatment options are available for patients\n" +
                            " with mild to moderate symptoms and for hospitalized patients.\n" +
                            " Visit our website at https://www.labcorp.com/COVID19 for\n" +
                            " resources and information.\n" +
                            " This nucleic acid amplification test was developed and its performance\n" +
                            " characteristics determined by LabCorp Laboratories. Nucleic acid\n" +
                            " amplification tests include RT-PCR and TMA. This test has not been\n" +
                            " FDA cleared or approved. This test has been authorized by FDA under\n" +
                            " an Emergency Use Authorization (EUA). This test is only authorized\n" +
                            " for the duration of time the declaration that circumstances exist justifying the authorization of the emergency use of in vitro diagnostic tests for detection of SARS-CoV-2 virus and/or diagnosis of COVID-19 infection under section 564(b)(1) of the Act, 21 U.S.C. 360bbb-3(b) (1), unless the authorization is terminated or revoked sooner. When diagnostic testing is negative, the possibility of a false negative result should be considered in the context of a patient's recent exposures and the presence of clinical signs and symptoms consistent with COVID-19. An individual without symptoms of COVID-19 and who is not shedding SARS-CoV-2 virus would expect to have a negative (not detected) result in this assay.";
                    break;
                case NEGATIVE:
                    description = "This nucleic acid amplification test was developed and its performance\n" +
                            " characteristics determined by LabCorp Laboratories. Nucleic acid\n" +
                            " amplification tests include RT-PCR and TMA. This test has not been\n" +
                            " FDA cleared or approved. This test has been authorized by FDA under\n" +
                            " an Emergency Use Authorization (EUA). This test is only authorized\n" +
                            " for the duration of time the declaration that circumstances exist\n" +
                            " justifying the authorization of the emergency use of in vitro\n" +
                            " diagnostic tests for detection of SARS-CoV-2 virus and/or diagnosis\n" +
                            " of COVID-19 infection under section 564(b)(1) of the Act, 21 U.S.C.\n" +
                            " 360bbb-3(b) (1), unless the authorization is terminated or revoked\n" +
                            " sooner.\n" +
                            " When diagnostic testing is negative, the possibility of a false\n" +
                            " negative result should be considered in the context of a patient's\n" +
                            " recent exposures and the presence of clinical signs and symptoms\n" +
                            " consistent with COVID-19. An individual without symptoms of COVID-19\n" +
                            " and who is not shedding SARS-CoV-2 virus would expect to have a\n" +
                            " negative (not detected) result in this assay.";
                    break;
                case INCONCLUSIVE:
                default:
                    description = "We are UNABLE to reliably determine a result for the specimen due to\n" +
                            " the inconsistent amplification of all of the required SARS-CoV-2\n" +
                            " components from the specimen submitted. If clinically indicated,\n" +
                            " please recollect an additional specimen for testing.\n" +
                            " This nucleic acid amplification test was developed and its performance\n" +
                            " characteristics determined by LabCorp Laboratories. Nucleic acid\n" +
                            " amplification tests include RT-PCR and TMA. This test has not been\n" +
                            " FDA cleared or approved. This test has been authorized by FDA under\n" +
                            " an Emergency Use Authorization (EUA). This test is only authorized\n" +
                            " for the duration of time the declaration that circumstances exist\n" +
                            " justifying the authorization of the emergency use of in vitro diagnostic tests for detection of SARS-CoV-2 virus and/or diagnosis of COVID-19 infection under section 564(b)(1) of the Act, 21 U.S.C. 360bbb-3(b) (1), unless the authorization is terminated or revoked sooner. When diagnostic testing is negative, the possibility of a false negative result should be considered in the context of a patient's recent exposures and the presence of clinical signs and symptoms consistent with COVID-19. An individual without symptoms of COVID-19 and who is not shedding SARS-CoV-2 virus would expect to have a negative (not detected) result in this assay.";
                    break;
            }

            //Replace the placeholders
            content = content.replace("{{result}}", appointment.getResult().toString());
            content = content.replace("{{description}}", description);
            content = content.replace("{{firstname}}", appointment.getPatient().getFirstName());
            content = content.replace("{{lastname}}", appointment.getPatient().getLastName());
            content = content.replace("{{cpr}}", String.valueOf(appointment.getPatient().getCPR()));
            content = content.replace("{{date}}", appointment.getDate().toString());
            content = content.replace("{{time}}", appointment.getTime().toString());
            content = content.replace("{{city}}", appointment.getCenter().getAddress().getCity());
            content = content.replace("{{postcode}}", String.valueOf(appointment.getCenter().getAddress().getPostCode()));
            content = content.replace("{{streetname}}", appointment.getCenter().getAddress().getStreetName());
            content = content.replace("{{streetnumber}}", appointment.getCenter().getAddress().getStreetNumber());
            content = content.replace("{{floor}}", appointment.getCenter().getAddress().getFloor());

            ConverterProperties converterProperties = new ConverterProperties();
            FontProvider fontProvider  = new FontProvider();
            fontProvider.addFont("src/main/resources/static/Oxanium.ttf");
            fontProvider.addFont("src/main/resources/static/RobotoSlab.ttf");
            fontProvider.addStandardPdfFonts();
            fontProvider.addSystemFonts();
            converterProperties.setFontProvider(fontProvider);

            HtmlConverter.convertToPdf(content, new FileOutputStream("certificate.pdf"), converterProperties);

            System.out.println("PDF generated");

            return true;
        }
        catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
}

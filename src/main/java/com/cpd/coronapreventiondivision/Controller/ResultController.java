package com.cpd.coronapreventiondivision.Controller;

import com.cpd.coronapreventiondivision.Handler.CertificateHandler;
import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Model.Patient;
import com.cpd.coronapreventiondivision.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Controller
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping("/results")
    public String viewResultForm(@RequestParam(value = "id") Integer id, Model model){
        model.addAttribute("id", id);

        return "resultform";
    }

    @PostMapping("/results")
    public String viewResults(@RequestParam(value = "id", required = false) Integer id,
                              @RequestParam(value = "cpr", required = false) Long cpr,
                              Model model) {
        System.out.println(id + " :id/cpr: " + cpr);
//        id = 6;
        String result = resultService.fetchResultByIdAndCpr(id, cpr);

        if(result != null){
            Patient patient = resultService.fetchPatientByCpr(cpr);
            String name = patient.getFirstName() + " " + patient.getLastName();

            model.addAttribute("id", id);
            model.addAttribute("name", name);
            model.addAttribute("result", result);

            return "results";
        }

        return viewResultForm(id, model);
    }

    @PostMapping("downloadPDF")
    public void downloadPDF(HttpServletResponse response,
                            @RequestParam(value = "id") Integer id,
                            @RequestParam(value = "cpr") Long cpr){
        System.out.println("Downloading a PDF!");
        Appointment appointment = resultService.fetchAppointmentByIdAndCpr(id, cpr);
        if (appointment != null){
            //Generate the PDF
            CertificateHandler.generatePDF(appointment);

            //Download the PDF
            try {
                File certificate = new File("certificate.pdf");
                if(certificate.exists()){
                    String mimeType = URLConnection.guessContentTypeFromName(certificate.getName());
                    if (mimeType == null) {
                        //unknown mimetype so set the mimetype to application/octet-stream
                        mimeType = "application/octet-stream";
                    }

                    response.setContentType(mimeType);
                    response.setHeader("Content-Disposition", String.format("inline; filename=\"" + certificate.getName() + "\""));
                    response.setContentLength((int) certificate.length());

                    InputStream inputStream = new BufferedInputStream(new FileInputStream(certificate));
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                }
            }
            catch(IOException e){ }
        }
    }
}

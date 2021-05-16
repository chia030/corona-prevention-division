package com.cpd.coronapreventiondivision;

import com.cpd.coronapreventiondivision.Handler.CertificateHandler;
import com.cpd.coronapreventiondivision.Handler.EmailHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronaPreventionDivisionApplication {

    public static final String domain = "http://localhost:9090";
    public static EmailHandler emailhandler;

    public static void main(String[] args){
        emailhandler = new EmailHandler();
//        CertificateHandler.generatePDF();
        SpringApplication.run(CoronaPreventionDivisionApplication.class, args);
    }
}

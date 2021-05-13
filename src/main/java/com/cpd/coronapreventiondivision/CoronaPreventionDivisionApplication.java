package com.cpd.coronapreventiondivision;

import com.cpd.coronapreventiondivision.Handler.EmailHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronaPreventionDivisionApplication {

    public static final String domain = "website.com";

    public static void main(String[] args) throws Exception{
        //SpringApplication.run(CoronaPreventionDivisionApplication.class, args);

        EmailHandler emailhandler = new EmailHandler();
        emailhandler.sendResults("chiaravisca@outlook.com", "123");
    }
}

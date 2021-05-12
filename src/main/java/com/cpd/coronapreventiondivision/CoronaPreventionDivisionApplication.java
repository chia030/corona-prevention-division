package com.cpd.coronapreventiondivision;

import Handler.EmailHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronaPreventionDivisionApplication {

    public static void main(String[] args) throws Exception{
        //SpringApplication.run(CoronaPreventionDivisionApplication.class, args);

        String content = "Hej, I've managed to set up email sending for our project :)\n" +
                "Here's a picture of a cat for you!<br/><ul>\n" +
                "<img src=\"https://icatcare.org/app/uploads/2018/06/Layer-1704-1200x630.jpg\">";

        EmailHandler emailhandler = new EmailHandler();
        emailhandler.sendEmail("chia0247@stud.kea.dk", "Hello from Corona Prevention Division!", content);
    }

}

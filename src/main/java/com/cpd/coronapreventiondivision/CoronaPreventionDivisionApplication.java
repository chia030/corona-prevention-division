package com.cpd.coronapreventiondivision;

import Handler.EmailHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronaPreventionDivisionApplication {

    public static void main(String[] args) throws Exception{
        //SpringApplication.run(CoronaPreventionDivisionApplication.class, args);

        EmailHandler emailhandler = new EmailHandler();
        emailhandler.sendResults("just1531@stud.kea.dk", "123");
    }

}

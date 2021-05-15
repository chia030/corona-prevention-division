package com.cpd.coronapreventiondivision;

import com.cpd.coronapreventiondivision.Handler.EmailHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronaPreventionDivisionApplication {

    public static final String domain = "localhost:9090";
    public static EmailHandler emailhandler;


    public static void main(String[] args) throws Exception{
        SpringApplication.run(CoronaPreventionDivisionApplication.class, args);
        emailhandler = new EmailHandler();

        //emailhandler.sendResults("chiaravisca@outlook.com", "123");
    }
}

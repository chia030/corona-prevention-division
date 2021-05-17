package com.cpd.coronapreventiondivision;

import com.cpd.coronapreventiondivision.Handler.CertificateHandler;
import com.cpd.coronapreventiondivision.Handler.EmailHandler;
import com.cpd.coronapreventiondivision.Model.Appointment;
import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.Patient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
public class CoronaPreventionDivisionApplication {

    public static final String domain = "http://localhost:9090";
    public static EmailHandler emailhandler;

    public static void main(String[] args){
        emailhandler = new EmailHandler();
//        Appointment appointment = new Appointment(
//                Appointment.Result.MISSED,
//                null,
//                null,
//                new Patient(1405017777, "just1531@stud.kea.dk", "Justas", "Zdanavicius", true, null),
//                new Center(4, Center.CenterType.COMIRNATY_VACCINE, null, null),
//                "just1531@stud.kea.dk");
//        System.out.println(CertificateHandler.generatePDF(appointment));
        SpringApplication.run(CoronaPreventionDivisionApplication.class, args);
    }
}

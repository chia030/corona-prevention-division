package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.CoronaPreventionDivisionApplication;
import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.Patient;
import com.cpd.coronapreventiondivision.Model.User;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import com.cpd.coronapreventiondivision.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    CenterRepo centerRepo;

    @Autowired
    PatientRepo patientRepo;

    public List<Center> fetchCenterByType(String type){
        return centerRepo.fetchByType(type);
    }

    public String fetchCenterGoogleMapsLinkById(int id){
        return centerRepo.fetchById(id).getAddress().getGoogleMapsLink();
    }

    public int sendConfirmation(Long cpr, String email, String firstName, String lastName){
        List<Patient> p = patientRepo.fetch(cpr, email, firstName, lastName);

        if(p.size() > 0){
            Patient patient = p.get(0);

            if (patient.isApproved()){
                return 0;
            }
            else {
                String toHash = email + new Date();
                String approvalID = User.hash(toHash);
                patient.setApprovalID(approvalID);
                boolean res = CoronaPreventionDivisionApplication.emailhandler.sendConfirmation(email, approvalID);
                if (res)
                    return 1;
                else return -1;
            }
        }

        return -1;
    }
}

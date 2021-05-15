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

    public boolean verifyEmail(String approvalID){
        try {
            patientRepo.verifyEmail(approvalID);

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public int sendConfirmation(Long cpr, String email, String firstName, String lastName){
        List<Patient> p = patientRepo.fetch(cpr, email, firstName, lastName);

        if(p.size() > 0){
            Patient patient = p.get(0);

            if (patient.isApproved() && patient.getEmailAddress() == email){
                return 0;
            }
            else {
                String toHash = email + new Date();
                String approvalID = User.hash(toHash);
                patient.setApprovalID(approvalID);
                patientRepo.setApproval(cpr, approvalID, email);
                boolean res = CoronaPreventionDivisionApplication.emailhandler.sendConfirmation(email, approvalID, firstName);
                if (res)
                    return 1;
                else return -1;
            }
        }

        return -2;
    }
}

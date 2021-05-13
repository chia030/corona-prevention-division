package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    CenterRepo centerRepo;

    public List<Center> fetchCenterByType(String type){
        return centerRepo.fetchByType(type);
    }
}

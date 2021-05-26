package com.cpd.coronapreventiondivision.Service;

import com.cpd.coronapreventiondivision.Model.Address;
import com.cpd.coronapreventiondivision.Model.Center;
import com.cpd.coronapreventiondivision.Model.WorkDay;
import com.cpd.coronapreventiondivision.Model.WorkWeek;
import com.cpd.coronapreventiondivision.Repository.AddressRepo;
import com.cpd.coronapreventiondivision.Repository.CenterRepo;
import com.cpd.coronapreventiondivision.Repository.WorkDayRepo;
import com.cpd.coronapreventiondivision.Repository.WorkWeekRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.List;

public class AdminService {

    @Autowired
    CenterRepo centerRepo;

    @Autowired
    WorkDayRepo workDayRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    WorkWeekRepo workWeekRepo;

    public List<Center> fetchCenterByType(String type){
        return centerRepo.fetchByType(type);
    }

    public Center fetchCenterById(int id){ return centerRepo.fetchById(id); }

    public Address fetchAddress(String city, int postCode, String streetName, String streetNumber, String floor, String googleMapsLink){
        return addressRepo.fetch(city, postCode, streetName, streetNumber, floor, googleMapsLink);
    }

    public WorkWeek getWorkWeek(String mOpen, String mClose, int mInterval, int mCapacity,
                                String tOpen, String tClose, int tInterval, int tCapacity,
                                String wOpen, String wClose, int wInterval, int wCapacity,
                                String thOpen, String thClose, int thInterval, int thCapacity,
                                String fOpen, String fClose, int fInterval, int fCapacity,
                                String sOpen, String sClose, int sInterval, int sCapacity,
                                String suOpen, String suClose, int suInterval, int suCapacity){
        WorkDay monday = getWorkDay(mOpen, mClose, mInterval, mCapacity);
        WorkDay tuesday = getWorkDay(tOpen, tClose, tInterval, tCapacity);
        WorkDay wednesday = getWorkDay(wOpen, wClose, wInterval, wCapacity);
        WorkDay thursday = getWorkDay(thOpen, thClose, thInterval, thCapacity);
        WorkDay friday = getWorkDay(fOpen, fClose, fInterval, fCapacity);
        WorkDay saturday = getWorkDay(sOpen, sClose, sInterval, sCapacity);
        WorkDay sunday = getWorkDay(suOpen, suClose, suInterval, suCapacity);

        WorkWeek ww = workWeekRepo.fetch(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        if (ww == null){
            //Create a new one and insert
            ww = new WorkWeek(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
            ww.setId(workWeekRepo.insert(ww));
        }

        return ww;
    }

    public WorkDay getWorkDay(String open, String close, int interval, int capacity){
        WorkDay day = workDayRepo.fetch(open, close, interval, capacity);
        if (day == null){
            //If it doesn't exist, create and insert a new one
            day = new WorkDay(LocalTime.parse(open), LocalTime.parse(close), interval, capacity);
            day.setId(workDayRepo.insert(day));
        }
        return day;
    }

    public int insertCenter(Center center){
        return centerRepo.insert(center);
    }

    public int insertAddress(Address address){ return addressRepo.insert(address); }

    public void removeCenter(int id){
        centerRepo.remove(id);
    }

    public void updateCenter(Center updatedCenter){
        int centerID = updatedCenter.getCenterID();
        String centerType = updatedCenter.getCenterType().toString();
        int addressID = updatedCenter.getAddress().getId();
        int workWeekID = updatedCenter.getWorkWeek().getId();

        centerRepo.update(centerID, centerType, addressID, workWeekID);
    }
}

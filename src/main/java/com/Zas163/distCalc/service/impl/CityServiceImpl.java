package com.Zas163.distCalc.service.impl;

import com.Zas163.distCalc.entity.City;
import com.Zas163.distCalc.repository.CityRepository;
import com.Zas163.distCalc.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    @Override
    public City getCityById(long id) {
        return cityRepository.findById(id).get();
    }

    //Расчет расстояния напрямую по Хаверсин
    @Override
    public double getDistanceByCrowFlight(City from, City to) {
        double fromLatitude = from.getLatitude();
        double fromLongitude = from.getLongitude();
        double toLatitude = to.getLatitude();
        double toLongitude = to.getLongitude();

        double dLat = Math.toRadians(fromLatitude - toLatitude);
        double dLon = Math.toRadians(fromLongitude - toLongitude);

        fromLatitude = Math.toRadians(fromLatitude);
        toLatitude = Math.toRadians(toLatitude);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(fromLatitude) *
                        Math.cos(toLatitude);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}

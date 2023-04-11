package com.Zas163.distCalc.service;

import com.Zas163.distCalc.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> getAllCities();

    City getCityById(long id);

    double getDistanceByCrowFlight(City from, City to);
}


package com.Zas163.distCalc.service;

import com.Zas163.distCalc.entity.City;
import com.Zas163.distCalc.service.impl.DistanceServiceImpl;

public interface DistanceService {
    double matrixDistance(City cityFrom, City cityTo);
}

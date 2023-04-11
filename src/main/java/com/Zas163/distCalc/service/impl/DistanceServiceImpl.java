package com.Zas163.distCalc.service.impl;

import com.Zas163.distCalc.entity.City;
import com.Zas163.distCalc.entity.Distance;
import com.Zas163.distCalc.entity.DistanceKey;
import com.Zas163.distCalc.repository.DistanceRepository;
import com.Zas163.distCalc.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

@Service
public class DistanceServiceImpl implements DistanceService {

    @Autowired
    DistanceRepository distanceRepository;


    @Override
    public double matrixDistance(City cityFrom, City cityTo) throws NoSuchElementException {
        long cityFromId = cityFrom.getId();
        long cityToId = cityTo.getId();
        DistanceKey distanceKey = new DistanceKey(cityFromId, cityToId);
        return distanceRepository.findById(distanceKey).get().getDistance();
    }

    public Distance unmarshall(Path p) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Distance.class);
        return (Distance) context.createUnmarshaller()
                .unmarshal(new FileReader(p.toString()));
    }
}

package com.Zas163.distCalc;

import com.Zas163.distCalc.entity.City;
import com.Zas163.distCalc.enums.CulcMode;
import com.Zas163.distCalc.service.impl.CityServiceImpl;
import com.Zas163.distCalc.service.impl.DistanceServiceImpl;
import com.Zas163.distCalc.util.DistanceRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static com.Zas163.distCalc.enums.CulcMode.ALL;
import static com.Zas163.distCalc.enums.CulcMode.MATRIX;

@RestController
@EntityScan("main.java.com.Zas163.distCalc.entity")
public class ApiGeneralController {
    @Autowired
    DistanceServiceImpl distanceService;

    @Autowired
    CityServiceImpl cityService;

    @GetMapping(value = "/cities")
    public ResponseEntity<List<City>> read() {
        final List<City> cities = cityService.getAllCities();

        return cities != null &&  !cities.isEmpty()
                ? new ResponseEntity<>(cities, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/distances")
    public ResponseEntity<List<CulcMode>> readEnums() {
        final List<CulcMode> culcModes = List.of(CulcMode.values());
        return culcModes != null &&  !culcModes.isEmpty()
                ? new ResponseEntity<>(culcModes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/distances")
    public ResponseEntity<?> countDistance(@Valid @RequestBody DistanceRequirements distanceRequirements, BindingResult bindingResult) {
        City fromCity = cityService.getCityById(distanceRequirements.getFrom().getId());
        City toCity = cityService.getCityById(distanceRequirements.getTo().getId());
        CulcMode mode = distanceRequirements.getMode();
        HashMap<String, Double> distCalc = new HashMap<>();
        double crowflightDistance = cityService.getDistanceByCrowFlight(fromCity, toCity);
        double matrixDistance = distanceService.matrixDistance(fromCity, toCity);
        if(mode.equals(CulcMode.CROWFLIGHT)) {
            distCalc.put("Distance by crowflight: ", crowflightDistance);
        }
        else if(mode.equals(MATRIX)) {
            distCalc.put("Distance by matrix: ", matrixDistance);
        }
        else if(mode.equals(ALL)) {
                distCalc.put("Distance by crowflight: ", crowflightDistance);
                distCalc.put("Distance by matrix: ", matrixDistance);
        }
        return new ResponseEntity<>(distCalc, HttpStatus.ACCEPTED);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws JAXBException {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(file.getOriginalFilename());
            Files.write(path, bytes);
            distanceService.unmarshall(path);
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

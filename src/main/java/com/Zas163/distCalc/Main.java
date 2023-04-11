package com.Zas163.distCalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.Zas163.distCalc.entity")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

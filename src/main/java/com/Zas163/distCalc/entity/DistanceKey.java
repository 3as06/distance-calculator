package com.Zas163.distCalc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class DistanceKey implements Serializable {

    private long fromCity;

    private long toCity;

    public DistanceKey(long fromCity, long toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
    }
}

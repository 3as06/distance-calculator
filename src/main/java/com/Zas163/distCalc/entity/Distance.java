package com.Zas163.distCalc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "distanceBtwCities")
@XmlType(propOrder = { "fromСity", "toСity", "distance" })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "distance")
public class Distance {
    @EmbeddedId
    DistanceKey key;

    @XmlAttribute
    @Access(AccessType.PROPERTY)
    @ManyToOne
    @JoinColumn(name = "fromСity", insertable = false, updatable = false)
    private City fromCity;

    @XmlAttribute
    @Access(AccessType.PROPERTY)
    @ManyToOne
    @JoinColumn(name = "toСity", insertable = false, updatable = false)
    private City toCity;

    @XmlAttribute
    private double distance;
}
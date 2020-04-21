package com.nicolrom.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "hole")
public class Hole implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int holeId;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNr;

    @Column(nullable = false)
    private String locality;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    @OneToMany(targetEntity = Phase.class, mappedBy = "hole", fetch = FetchType.EAGER)
    private List<Phase> phases = new ArrayList<>();

    @Column(nullable = false)
    private double holeLength;

    @Column(nullable = false)
    private double holeWidth;

    @Column(nullable = false)
    private double holeDepth;

    @Column(nullable = false)
    private double holeVolume;

    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public double getHoleLength() {
        return holeLength;
    }

    public void setHoleLength(double holeLength) {
        this.holeLength = holeLength;
    }

    public double getHoleWidth() {
        return holeWidth;
    }

    public void setHoleWidth(double holeWidth) {
        this.holeWidth = holeWidth;
    }

    public double getHoleDepth() {
        return holeDepth;
    }

    public void setHoleDepth(double holeDepth) {
        this.holeDepth = holeDepth;
    }

    public double getHoleVolume() {
        return holeVolume;
    }

    public void setHoleVolume(double holeVolume) {
        this.holeVolume = holeVolume;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

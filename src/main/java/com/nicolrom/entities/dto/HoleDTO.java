package com.nicolrom.entities.dto;

import javax.persistence.Column;
import java.util.Date;

public class HoleDTO {

    private int holeId;

    private Date date;

    private String street;

    private String streetNr;

    private String locality;

    private String district;

    private String phase;

    private double holeLength;

    private double holeWidth;

    private double holeDepth;

    private double holeSurface;

    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
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

    public double getHoleSurface() {
        return holeSurface;
    }

    public void setHoleSurface(double holeSurface) {
        this.holeSurface = holeSurface;
    }
}

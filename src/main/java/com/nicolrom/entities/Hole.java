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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pipeId", referencedColumnName = "idpipe")
    private Pipe pipe;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "areaId", referencedColumnName = "idArea")
    private Area area;

    @Column
    private Integer autoRouteDistance;

    @Column
    private Integer autoStationaryTime;

    @Column(nullable = false)
    private String executor;

    @Column
    private Integer holeNrAtSameAddress;

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

    public Pipe getPipe() {
        return pipe;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getAutoRouteDistance() {
        return autoRouteDistance;
    }

    public void setAutoRouteDistance(Integer autoRouteDistance) {
        this.autoRouteDistance = autoRouteDistance;
    }

    public Integer getAutoStationaryTime() {
        return autoStationaryTime;
    }

    public void setAutoStationaryTime(Integer autoStationaryTime) {
        this.autoStationaryTime = autoStationaryTime;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Integer getHoleNrAtSameAddress() {
        return holeNrAtSameAddress;
    }

    public void setHoleNrAtSameAddress(Integer holeNrAtSameAddress) {
        this.holeNrAtSameAddress = holeNrAtSameAddress;
    }
}

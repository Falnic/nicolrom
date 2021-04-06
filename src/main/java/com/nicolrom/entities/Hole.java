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

    @OneToOne(mappedBy = "hole", fetch = FetchType.EAGER)
    private HoleAddress holeAddress;

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
    private Double autoRouteDistance;

    @Column
    private Integer autoStationaryTime;

    @Column(nullable = false)
    private String executor;

    @ManyToOne(targetEntity = Volume.class)
    @JoinColumn(name = "volumeId", nullable = false)
    private Volume volume;

    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
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

    public Double getAutoRouteDistance() {
        return autoRouteDistance;
    }

    public void setAutoRouteDistance(Double autoRouteDistance) {
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

    public HoleAddress getHoleAddress() {
        return holeAddress;
    }

    public void setHoleAddress(HoleAddress holeAddress) {
        this.holeAddress = holeAddress;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }
}

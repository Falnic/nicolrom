package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pipe")
public class Pipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpipe", nullable = false)
    private int idpipe;

    @Column(nullable = false, unique = true)
    private String diameter;

    @Column(nullable = false)
    private double diameterValue;

    @OneToOne(mappedBy = "pipe")
    private Hole hole;

    public int getIdpipe() {
        return idpipe;
    }

    public void setIdpipe(int idpipe) {
        this.idpipe = idpipe;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public double getDiameterValue() {
        return diameterValue;
    }

    public void setDiameterValue(double diameterValue) {
        this.diameterValue = diameterValue;
    }

    public Hole getHole() {
        return hole;
    }

    public void setHole(Hole hole) {
        this.hole = hole;
    }
}

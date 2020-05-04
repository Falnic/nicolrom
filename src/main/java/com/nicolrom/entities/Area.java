package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "area")
public class Area implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idArea;

    @Column(nullable = false)
    private String type;

    @Column(nullable = true)
    private Double thickness;

    public Integer getAreaId() {
        return idArea;
    }

    public void setAreaId(Integer idArea) {
        this.idArea = idArea;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }
}

package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HoleAddress")
public class HoleAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "idAddress", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idHole", nullable = false, referencedColumnName = "holeId")
    private Hole hole;

    @Column(nullable = false)
    private String streetNr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Hole getHole() {
        return hole;
    }

    public void setHole(Hole hole) {
        this.hole = hole;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    @Override
    public String toString() {
        return "Strada " + address.getStreet() +
                " nr" + streetNr +
                " " + address.getCounty() +
                " " + address.getLocality();
    }
}

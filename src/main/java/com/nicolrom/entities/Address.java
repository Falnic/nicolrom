package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idAddress;

    @Column(nullable = false)
    private String locality;

    @Column(nullable = false)
    private String county;

    @Column(nullable = false)
    private String street;

    @Column
    private String district;

    @OneToMany(targetEntity = HoleAddress.class, mappedBy = "address")
    @Column(nullable = false)
    private Set<HoleAddress> holeAddressSet = new HashSet<>();

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Set<HoleAddress> getHoleAddressSet() {
        return holeAddressSet;
    }

    public void setHoleAddressSet(Set<HoleAddress> holeAddressSet) {
        this.holeAddressSet = holeAddressSet;
    }
}

package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "volume")
public class Volume implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idVolume;

    @Column(nullable = false)
    private String nr;

    @Column(nullable = false)
    private String regNr;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ManyToOne(targetEntity = Contract.class)
    @JoinColumn(name = "contractId")
    private Contract contract;

    @OneToMany(targetEntity = Hole.class, mappedBy = "volume")
    @Column(nullable = false)
    private List<Hole> holes = new ArrayList<>();

    @OneToMany(targetEntity = PaymentSituation.class, mappedBy = "volume")
    @Column(nullable = false)
    private List<PaymentSituation> paymentSituations;

    @Column(nullable = false)
    private Double total;

    public int getIdVolume() {
        return idVolume;
    }

    public void setIdVolume(int idVolume) {
        this.idVolume = idVolume;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getRegNr() {
        return regNr;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public void setHoles(List<Hole> holes) {
        this.holes = holes;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<PaymentSituation> getVolumeArticles() {
        return paymentSituations;
    }

    public void setVolumeArticles(List<PaymentSituation> paymentSituations) {
        this.paymentSituations = paymentSituations;
    }
}

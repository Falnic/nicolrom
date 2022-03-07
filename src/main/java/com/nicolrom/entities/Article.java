package com.nicolrom.entities;

import com.nicolrom.enums.ArticleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idArticle;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String unit;

    @ManyToOne(targetEntity = Contract.class)
    @JoinColumn(name = "idcontract", nullable = false)
    private Contract contract;

    @OneToMany(targetEntity = PaymentSituation.class ,mappedBy = "article")
    @Column(nullable = false)
    private List<PaymentSituation> paymentSituations;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleEnum type;

    @Column(nullable = false)
    private Double price;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ArticleEnum getType() {
        return type;
    }

    public void setType(ArticleEnum type) {
        this.type = type;
    }

    public List<PaymentSituation> getVolumeArticles() {
        return paymentSituations;
    }

    public void setVolumeArticles(List<PaymentSituation> paymentSituations) {
        this.paymentSituations = paymentSituations;
    }
}

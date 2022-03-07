package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "paymentSituation")
public class PaymentSituation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne(targetEntity = Volume.class)
    @JoinColumn(name = "volumeId", nullable = false)
    private Volume volume;

    @ManyToOne(targetEntity = Article.class)
    @JoinColumn(name = "articleId", nullable = false)
    private Article article;

    @ManyToOne(targetEntity = Hole.class)
    @JoinColumn(name = "hole_Id", nullable = false)
    private Hole hole;

    @Column(nullable = false)
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Hole getHole() {
        return hole;
    }

    public void setHole(Hole hole) {
        this.hole = hole;
    }
}

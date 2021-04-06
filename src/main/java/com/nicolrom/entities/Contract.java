package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idContract;

    @Column(nullable = false)
    private String nr;

    @Column(nullable = false)
    private Date date;

    @OneToMany(targetEntity = Article.class, mappedBy = "contract", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(targetEntity = Volume.class, mappedBy = "contract")
    @Column(nullable = false)
    private List<Volume> volumes = new ArrayList<>();

    @Column(nullable = true)
    private boolean inUse;

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isInUse() {
        return inUse;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}

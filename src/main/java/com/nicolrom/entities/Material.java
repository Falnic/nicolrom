package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "material")
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int materialId;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Phase_Material.class, mappedBy = "material")
    @Column(nullable = false)
    private Set<Phase_Material> phaseMaterialSet = new HashSet<>();

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Phase_Material> getPhaseMaterialSet() {
        return phaseMaterialSet;
    }

    public void setPhaseMaterialSet(Set<Phase_Material> phaseMaterialSet) {
        this.phaseMaterialSet = phaseMaterialSet;
    }
}

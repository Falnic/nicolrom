package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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

    @OneToMany(targetEntity = MaterialNotice.class, mappedBy = "material")
    @Column(nullable = false)
    private Set<MaterialNotice> materialNoticeSet = new HashSet<>();

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

    public Set<MaterialNotice> getMaterialNoticeSet() {
        return materialNoticeSet;
    }

    public void setMaterialNoticeSet(Set<MaterialNotice> phaseMaterialSet) {
        this.materialNoticeSet = phaseMaterialSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return materialId == material.materialId &&
                name.equals(material.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, name);
    }
}

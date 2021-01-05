package com.nicolrom.entities;

import com.nicolrom.enums.PhaseEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "phase")
public class Phase implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int phaseId;

    @ManyToOne(targetEntity = Hole.class)
    @JoinColumn(name = "holeId", nullable = false)
    private Hole hole;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PhaseEnum phaseType = PhaseEnum.SAPATURA;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date phaseDate;

    @OneToMany(targetEntity = MaterialNotice.class, mappedBy = "phase", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<MaterialNotice> materialNoticeSet = new HashSet<>();

    @OneToOne(mappedBy = "phase")
    private Team team;

    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
    }

    public Hole getHole() {
        return hole;
    }

    public void setHole(Hole hole) {
        this.hole = hole;
    }

    public PhaseEnum getPhaseType() {
        return phaseType;
    }

    public void setPhaseType(PhaseEnum phaseType) {
        this.phaseType = phaseType;
    }

    public Date getPhaseDate() {
        return phaseDate;
    }

    public void setPhaseDate(Date phaseDate) {
        this.phaseDate = phaseDate;
    }

    public Set<MaterialNotice> getMaterialNoticeSet() {
        return materialNoticeSet;
    }

    public void setMaterialNoticeSet(Set<MaterialNotice> phaseMaterialSet) {
        this.materialNoticeSet = phaseMaterialSet;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

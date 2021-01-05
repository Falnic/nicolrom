package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idTeam;

    @OneToMany(targetEntity = TeamDeploy.class, mappedBy = "team", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<TeamDeploy> teamDeploys = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phaseId", referencedColumnName = "phaseId")
    private Phase phase;

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int teamId) {
        this.idTeam = teamId;
    }

    public Set<TeamDeploy> getTeamDeploys() {
        return teamDeploys;
    }

    public void setTeamDeploys(Set<TeamDeploy> teamDeploys) {
        this.teamDeploys = teamDeploys;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}

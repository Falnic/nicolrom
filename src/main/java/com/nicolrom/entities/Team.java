package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int teamId;

    @OneToMany(targetEntity = Team_Employee.class, mappedBy = "team", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<Team_Employee> team_employees;

    @OneToMany(targetEntity = Phase.class, mappedBy = "team")
    @Column(nullable = false)
    private Set<Phase> phases;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Set<Team_Employee> getTeam_employees() {
        return team_employees;
    }

    public void setTeam_employees(Set<Team_Employee> team_employees) {
        this.team_employees = team_employees;
    }

    public Set<Phase> getPhases() {
        return phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }
}

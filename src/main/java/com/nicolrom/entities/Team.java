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

//    @OneToMany(targetEntity = Team_Employee.class, mappedBy = "team", fetch = FetchType.EAGER)
//    @Column(nullable = false)
//    private Set<Team_Employee> team_employees;

    @ManyToMany(mappedBy = "teams")
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(targetEntity = Phase.class, mappedBy = "team")
    @Column(nullable = false)
    private Set<Phase> phases = new HashSet<>();

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int teamId) {
        this.idTeam = teamId;
    }

    public Set<Phase> getPhases() {
        return phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}

package com.nicolrom.entities;

import com.nicolrom.enums.EmployeePositionEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idEmployee;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeePositionEnum position;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Team_Employee",
            joinColumns = {@JoinColumn(name = "idEmployee")},
            inverseJoinColumns = { @JoinColumn(name = "idTeam")}
    )
    private Set<Team> teams = new HashSet<>();

    @OneToMany(targetEntity = Machinery.class, mappedBy = "employee", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<Machinery> machines = new HashSet<>();

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeePositionEnum getPosition() {
        return position;
    }

    public void setPosition(EmployeePositionEnum positionEnum) {
        this.position = positionEnum;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Machinery> getMachines() {
        return machines;
    }

    public void setMachines(Set<Machinery> machines) {
        this.machines = machines;
    }


}

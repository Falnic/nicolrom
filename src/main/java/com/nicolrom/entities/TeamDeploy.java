package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teamdeploy")
public class TeamDeploy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "idteamdeploy")
    private int id;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "idteam", nullable = false)
    private Team team;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "idemployee", nullable = false)
    private Employee employee;

    @ManyToOne(targetEntity = Machinery.class)
    @JoinColumn(name = "idmachinery")
    private Machinery machinery;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Machinery getMachinery() {
        return machinery;
    }

    public void setMachinery(Machinery machinery) {
        this.machinery = machinery;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

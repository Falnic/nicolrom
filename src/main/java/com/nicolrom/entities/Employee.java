package com.nicolrom.entities;

import com.nicolrom.enums.EmployeePositionEnum;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToMany(targetEntity = Team_Employee.class, mappedBy = "employee", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<Team_Employee> team_employees;

    @OneToMany(targetEntity = Machinery.class, mappedBy = "employee", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<Machinery> machines;

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

    public Set<Team_Employee> getTeam_employees() {
        return team_employees;
    }

    public void setTeam_employees(Set<Team_Employee> team_employees) {
        this.team_employees = team_employees;
    }

    public Set<Machinery> getMachines() {
        return machines;
    }

    public void setMachines(Set<Machinery> machines) {
        this.machines = machines;
    }
}

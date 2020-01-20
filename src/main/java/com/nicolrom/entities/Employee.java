package com.nicolrom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int employeeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @OneToMany(targetEntity = Team_Employee.class, mappedBy = "employee", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<Team_Employee> team_employees;

    @OneToMany(targetEntity = Machinery.class, mappedBy = "employee", fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<Machinery> machines;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

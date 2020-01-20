package com.nicolrom.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team_employee")
public class Team_Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int team_employeeId;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    public int getTeam_employeeId() {
        return team_employeeId;
    }

    public void setTeam_employeeId(int team_employeeId) {
        this.team_employeeId = team_employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

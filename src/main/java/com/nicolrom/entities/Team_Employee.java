package com.nicolrom.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team_employee")
public class Team_Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idteam_employee;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "idEmployee", nullable = false)
    private Employee employee;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "idTeam", nullable = false)
    private Team team;

    public int getIdteam_employee() {
        return idteam_employee;
    }

    public void setIdteam_employee(int team_employeeId) {
        this.idteam_employee = team_employeeId;
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

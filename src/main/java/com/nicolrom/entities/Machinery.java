package com.nicolrom.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nicolrom.enums.MachineryEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "machinery")
public class Machinery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int machineryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    private String licensePlate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MachineryEnum machineryType;

    @Column
    private Integer capacity;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeId")
    @JsonBackReference
    private Employee employee;

    public int getMachineryId() {
        return machineryId;
    }

    public void setMachineryId(int machineryId) {
        this.machineryId = machineryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MachineryEnum getMachineryType() {
        return machineryType;
    }

    public void setMachineryType(MachineryEnum machineryType) {
        this.machineryType = machineryType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }
}

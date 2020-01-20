package com.nicolrom.entities;

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
    @Enumerated(EnumType.ORDINAL)
    private MachineryEnum machineryType;

    @Column
    private int transportCap;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeId")
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

    public int getTransportCap() {
        return transportCap;
    }

    public void setTransportCap(int transportCap) {
        this.transportCap = transportCap;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

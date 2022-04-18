package com.backend.finalProject.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Patients")
public class Patient {

    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Integer id;

    private String name;
    private String lastName;
    private Integer DNI;
    @Column(name = "discharge_date")
    private LocalDate dischargeDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointments;

    public Patient(){}

    public Patient(String name, String lastName, Integer DNI, LocalDate dischargeDate, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.DNI = DNI;
        this.dischargeDate = dischargeDate;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
        this.DNI = DNI;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}

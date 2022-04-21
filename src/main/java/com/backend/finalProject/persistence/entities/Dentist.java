package com.backend.finalProject.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dentists")
public class Dentist {

    @Id
    @SequenceGenerator(name = "dentist_sequence", sequenceName = "dentist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_sequence")
    private Integer id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "professional_registration_number", unique = true)
    private String professionalRegistrationNumber;

    //Bidireccional
    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    public Dentist() {}

    public Dentist(Integer id, String name, String lastName, String professionalRegistrationNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.professionalRegistrationNumber = professionalRegistrationNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
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

    public String getProfessionalRegistrationNumber() {
        return professionalRegistrationNumber;
    }

    public void setProfessionalRegistrationNumber(String professionalRegistrationNumber) {
        this.professionalRegistrationNumber = professionalRegistrationNumber;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}

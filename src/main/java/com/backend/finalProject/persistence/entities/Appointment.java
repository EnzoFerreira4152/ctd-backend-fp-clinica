package com.backend.finalProject.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Appointments")
public class Appointment {

    @Id
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "appointment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Integer id;

    private LocalDate date;

    //Bidireccional
    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;

    //Bidireccional
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Appointment() {}

    public Appointment(Integer id, LocalDate date, Dentist dentist, Patient patient) {
        this.id = id;
        this.date = date;
        this.dentist = dentist;
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

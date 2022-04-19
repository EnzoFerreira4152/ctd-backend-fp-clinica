package com.backend.finalProject.model;

import java.time.LocalDate;

public class AppointmentDTO {
    private Integer id;
    private LocalDate date;
    private DentistDTO dentist;
    private PatientDTO patient;

    public AppointmentDTO(){}

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DentistDTO getDentist() {
        return dentist;
    }

    public void setDentist(DentistDTO dentist) {
        this.dentist = dentist;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}

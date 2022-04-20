package com.backend.finalProject.model;

import java.time.LocalDate;

public class AppointmentDTO {
    private Integer id;
    private LocalDate date;
    private Integer dentist_id;
    private Integer patient_id;

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

    public Integer getDentist_id() {
        return dentist_id;
    }

    public void setDentist_id(Integer dentist_id) {
        this.dentist_id = dentist_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }
}

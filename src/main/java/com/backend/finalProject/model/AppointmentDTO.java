package com.backend.finalProject.model;

import java.time.LocalDate;

public class AppointmentDTO {
    private Integer id;
    private LocalDate date;
    private Integer dentistId;
    private Integer patientId;

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

    public Integer getDentistId() {
        return dentistId;
    }

    public void setDentistId(Integer dentistId) {
        this.dentistId = dentistId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}

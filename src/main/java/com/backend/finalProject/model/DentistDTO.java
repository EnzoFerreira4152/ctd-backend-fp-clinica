package com.backend.finalProject.model;

public class DentistDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String professionalRegistrationNumber;

    public DentistDTO() {
    }

    public DentistDTO(String name, String lastName, String professionalRegistrationNumber) {
        this.setName(name);
        this.setLastName(lastName);
        this.setProfessionalRegistrationNumber(professionalRegistrationNumber);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws Exception{
        if(this.id == null){
            this.id = id;
        }else{
            throw new Exception("The dentist already has an id: " + this.id);
        }
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

    @Override
    public String toString() {
        return "DentistDTO{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", professionalRegistrationNumber=" + professionalRegistrationNumber +
                '}';
    }


}

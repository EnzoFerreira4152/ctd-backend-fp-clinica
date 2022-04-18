package com.backend.finalProject.model;

public class AddressDTO {
    private String street;
    private Integer number;
    private String city;
    private String county;

    public AddressDTO() {
    }

    public AddressDTO(String street, Integer number, String city, String county) {
        this.setStreet(street);
        this.setNumber(number);
        this.setCity(city);
        this.setCounty(county);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}

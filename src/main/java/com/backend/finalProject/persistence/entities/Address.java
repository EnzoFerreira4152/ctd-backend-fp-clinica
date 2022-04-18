package com.backend.finalProject.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Addresses")
public class Address {

    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Integer id;

    private String street;
    private Integer number;
    private String city;
    private String county;

    public Address(){}

    public Address(String street, Integer number, String city, String county) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.county = county;
    }

    public Integer getId() {
        return id;
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

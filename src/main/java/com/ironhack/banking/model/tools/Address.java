package com.ironhack.banking.model.tools;

import com.ironhack.banking.model.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;
    private String street;
    private int streetNumber;
    private int floorNumber;
    @Digits(integer = 5, fraction = 0)
    private int postalCode;


    public Address() {
    }

    public Address(String city, String street, int streetNumber, int floorNumber, @Digits(integer = 5, fraction = 0) int postalCode) {
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.floorNumber = floorNumber;
        this.postalCode = postalCode;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }




}

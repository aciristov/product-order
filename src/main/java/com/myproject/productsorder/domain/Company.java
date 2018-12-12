package com.myproject.productsorder.domain;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    // JUST DEFAULT CONSTRUCTOR
    public Company(){

    }

    //CONSTRUCTOR WITH ARGUMENTS
    public Company(String name, String city, String phone, String address){
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.address = address;
    }

    // GETTERS AND SETTERS METHODS BELOW, auto generated

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

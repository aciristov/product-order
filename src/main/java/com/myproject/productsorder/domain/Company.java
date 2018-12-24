package com.myproject.productsorder.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(unique = true, name = "company_id")
    private String name;

    @NotNull
    @Size(max = 100)
    private String city;

    @NotNull
    @Size(max = 100)
    private String phone;

    @NotNull
    @Size(max = 200)
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

package com.myproject.productsorder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Product")
@Table(name = "product")
@NaturalIdCache
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NaturalId
    @Column(name = "name")
    private String name;

    @Column(name = "unit_price")
    private double unit_price;

    @Column(name = "description")
    private String description;

    @Column(name = "available")
    private boolean available;


    //OneToMany, "Product" AND "OrderProduct"
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orders = new ArrayList<>();

    //ManyToOne, "Product" AND "Company"
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE.CASCADE)
    @JsonIgnore
    private Company company;


    // JUST DEFAULT CONSTRUCTOR
    public Product(){

    }

    //CONSTRUCTOR WITH ARGUMENTS
    public Product(String name, double unit_price, String description, boolean available){
        this.name = name;
        this.unit_price = unit_price;
        this.description = description;
        this.available = available;
    }

    // OVERRIDEN METHODS FROM THE OBJECT

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }

    // GETTERS AND SETTERS BELOW

    public List<OrderProduct> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderProduct> orders) {
        this.orders = orders;
    }

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

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

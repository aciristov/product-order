package com.myproject.productsorder.domain;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
@NaturalIdCache
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    private String name;

    @NotNull
    private double unitprice;

    @Size(max = 200)
    private String description;

    @NotNull
    private boolean available;


    //OneToMany, "Product" AND "OrderProduct"
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orders = new ArrayList<>();

    //ManyToOne, "Product" AND "Company"
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("company_id")
    private Company company;


    // JUST DEFAULT CONSTRUCTOR
    public Product(){

    }

    //CONSTRUCTOR WITH ARGUMENTS
    public Product(String name, double unitprice, String description, boolean available){
        this.name = name;
        this.unitprice = unitprice;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
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

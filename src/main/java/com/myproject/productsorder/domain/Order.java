package com.myproject.productsorder.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double quantity;

    private Date orderDate;

    @Size(max = 200)
    private String description;


    // ONETOMANY BETWEEN "OrderProduct" and "Order"
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <OrderProduct> products = new ArrayList<>();

    //JUST DEFAULT CONSTRUCTOR
    public Order(){

    }

    //ONETOMANY "USER AND ORDER" , many orders for user
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private User user;


    //CONSTRUCTOR WITH ARGUMENTS
    public Order(Date orderDate, int quantity, String description){
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.description = description;
    }


//MANY YO MANY RELATION - ORDER AND PRODUCT, BETWEEN JOIN TABLE "OrderProduct", WHO HAS FOREIGN KEYS FROM TWO TABLES
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//        name = "OrderProduct",
//        joinColumns = {@JoinColumn(name = "orderId")},
//        inverseJoinColumns = {@JoinColumn(name = "productId")}
//    )
//    private Set<Product> products = new HashSet<>();


    // ADD PRODUCT TO PRODUCTS FROM THE LIST ABOVE, FOR ORDER. -> ONE ORDER MANY PRODUCTS
    public void addProduct(Product product) {
        OrderProduct orderProduct = new OrderProduct(this, product);
        products.add(orderProduct);
        //product.getOrders().add(orderProduct);
    }

    public void removeProduct(Product product){
        for (Iterator<OrderProduct> iterator = products.iterator(); iterator.hasNext(); ){
            OrderProduct orderProduct = iterator.next();
            if (orderProduct.getOrder().equals(this) && orderProduct.getProduct().equals(product)){
                iterator.remove();
                orderProduct.getProduct().getOrders().remove(orderProduct);
                orderProduct.setOrder(null);
                orderProduct.setProduct(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    //GETTERS AND SETTERS BELOW


    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

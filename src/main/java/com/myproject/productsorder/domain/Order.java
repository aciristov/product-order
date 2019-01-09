package com.myproject.productsorder.domain;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "order", schema = "order")
@NaturalIdCache
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    private Long quantity;

//    @NotNull
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private Date orderDate;

    @NotNull
    @Size(max = 200)
    private String description;

//   OneToMany -> "OrderProductService" , "Order"
    //@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List <OrderProduct> products = new ArrayList<>();

    //ManyToOne "User" , "Order"
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Order(){}
    public Order(Date orderDate, Long quantity, String description){
//        this.orderDate = orderDate;
        this.quantity = quantity;
        this.description = description;
    }

//    public void addProduct(Product product) {
//        OrderProduct orderProduct = new OrderProduct(this, product);
//        products.add(orderProduct);
//        product.getOrders().add(orderProduct);
//    }
//
//    public void removeProduct(Product product){
//        for (Iterator<OrderProduct> iterator = products.iterator();
//             iterator.hasNext();
//             ){
//            OrderProduct orderProduct = iterator.next();
//
//            if (orderProduct.getOrder().equals(this) && orderProduct.getProduct().equals(product)){
//                iterator.remove();
//                orderProduct.getProduct().getOrders().remove(orderProduct);
//                orderProduct.setOrder(null);
//                orderProduct.setProduct(null);
//            }
//        }
//    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

//    public Date getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }

    public String getDescription() {
        return description;
    }

//    public List<OrderProduct> getProducts() { return products; }
//
//    public void setProducts(List<OrderProduct> products) { this.products = products; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public void setDescription(String description) {
        this.description = description;
    }

}

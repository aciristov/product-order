package com.myproject.productsorder.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

// BETWEEN ORDER AND PRODUCT
@Entity
@Table(name = "order_product")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("orderId")
    private OrderTest order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("productId")
    private Product product;

    // TODO: MAYBE IN OTHER CLASSES, CHECK!
//    private double quantity;
//    private double unit_price;
//    public double totalPrice(){
//        return quantity * unit_price;
//    }

    private OrderProduct(){}

    public OrderProduct(OrderTest order, Product product){
        this.order = order;
        this.product = product;
        this.id = new OrderProductId(order.getId(), product.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderProduct that = (OrderProduct) o;
        return Objects.equals(order, that.order) &&
            Objects.equals(product, that.product);
    }

    @Override
    public int hashCode(){
        return Objects.hash(order, product);
    }

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) { this.id = id; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) {
        this.product = product;
    }

}

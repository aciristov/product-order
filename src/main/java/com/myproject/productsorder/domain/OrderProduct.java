package com.myproject.productsorder.domain;

import javax.persistence.*;
import java.util.Objects;

// BETWEEN ORDER AND PRODUCT
@Entity(name = "OrderProduct")
@Table(name = "order_product")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderid")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productid")
    private Product product;

    private double quantity;

    private OrderProduct(){}

    public OrderProduct(Order order, Product product){
        this.order = order;
        this.product = product;
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

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

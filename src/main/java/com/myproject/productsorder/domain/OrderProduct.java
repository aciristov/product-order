package com.myproject.productsorder.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;

    private double quantity;

    private double unit_price;

    public OrderProduct() {}

    public OrderProduct(long id, long orderId, long productId, double quantity, double unit_price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return orderId == that.orderId &&
            productId == that.productId &&
            Double.compare(that.quantity, quantity) == 0 &&
            Double.compare(that.unit_price, unit_price) == 0 &&
            Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, productId, quantity, unit_price);
    }

    public long getId() {
        return id;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getProductId() {
        return productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}

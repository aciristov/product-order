package com.myproject.productsorder.domain;

public class OrderProductPojo {

    private long productId;
    private String description;
    private String name;
    private double quantity;
    private double unit_price;

    public OrderProductPojo(long productId, String description, String name, double quantity, double unit_price) {
        this.productId = productId;
        this.description = description;
        this.name = name;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public long getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }
}

package com.myproject.productsorder.domain;

import java.util.List;

public class OrderPojo {

    private String description;
    private List<OrderProdPojo> products;

    public OrderPojo() {}
    public OrderPojo(String description, List<OrderProdPojo> products) {
        this.description = description;
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OrderProdPojo> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProdPojo> products) {
        this.products = products;
    }
}

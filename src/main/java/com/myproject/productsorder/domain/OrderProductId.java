package com.myproject.productsorder.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class OrderProductId implements Serializable {

    @Column(name = "order_id")
    private Long orderid;

    @Column(name = "product_id")
    private Long productid;

    private OrderProductId(){

    }

    private OrderProductId(Long orderid, Long productid){
        this.orderid = orderid;
        this.productid = productid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderProductId that = (OrderProductId) o;
        return Objects.equals(orderid, that.orderid) && Objects.equals(productid, that.productid);
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderid, productid);
    }

}

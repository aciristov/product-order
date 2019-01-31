package com.myproject.productsorder.domain;

import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order_test")
@NaturalIdCache
public class OrderTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    @Column(name = "date")
    private Date orderDate;

    /**
     * Many orders to one user.
     */
    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public OrderTest() {}

    public OrderTest(String description, @NotNull Date orderDate) {
        this.description = description;
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTest orderTest = (OrderTest) o;
        return Objects.equals(id, orderTest.id) &&
            Objects.equals(description, orderTest.description) &&
            Objects.equals(orderDate, orderTest.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, orderDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.ecommercep5.account_ms.models;

import java.util.Date;
import org.springframework.data.annotation.Id;

public class Order {

    @Id
    private String id;
    private Integer user_id;
    private Integer[] products_ids;
    private Date date;

    public Order(Integer user_id, Integer[] products_ids, Date date) {
        this.user_id = user_id;
        this.products_ids = products_ids;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer[] getProducts_ids() {
        return products_ids;
    }

    public void setProducts_ids(Integer[] products_ids) {
        this.products_ids = products_ids;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
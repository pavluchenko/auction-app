package com.auction.model.form;

import java.util.Date;

/**
 * Created by Helga on 13.11.2017.
 */
public class RateCreateForm extends BaseForm {
    private Long id;
    private Double price;
    private Date date;
    private Long lotId;
    private Long userId;
    private String userEmail;

    public RateCreateForm() {
    }

    public RateCreateForm(Long id, Double price, Date date, Long lotId, Long userId) {
        this.id = id;
        this.price = price;
        this.date = new Date(date.getTime());
        this.lotId = lotId;
        this.userId = userId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

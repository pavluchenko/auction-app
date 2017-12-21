package com.auction.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Helga on 26.10.17.
 */

@Entity
@Table(name = "rate")
public class Rate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "my_seq_gen")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "date")
    private Date date;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "lot_id", referencedColumnName = "id", insertable = true, updatable = false)
    private Lot lot;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = true, updatable = false)
    private User user;

    public Rate(Double price, Date date, Lot lot, User user) {
        this.price = price;
        this.date = new Date(date.getTime());
        this.lot = lot;
        this.user = user;
    }

    public Rate() {
    }

    public Long getId() {
        return id;
    }

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

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rate rate = (Rate) o;

        if (id != null ? !id.equals(rate.id) : rate.id != null) return false;
        if (price != null ? !price.equals(rate.price) : rate.price != null) return false;
        if (date != null ? !date.equals(rate.date) : rate.date != null) return false;
        if (lot != null ? !lot.equals(rate.lot) : rate.lot != null) return false;
        return user != null ? user.equals(rate.user) : rate.user == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (lot != null ? lot.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}

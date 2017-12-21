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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Helga on 21.10.17.
 */

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "my_seq_gen")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "password_hash_confirm")
    private String passwordConfirm;

    @Column(name = "rating")
    private String rating;

    @Column(name = "disable")
    private Boolean disable = false;

    @Fetch(FetchMode.SELECT)
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.MERGE})
    private List<Lot> lots;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role;

    @Fetch(FetchMode.SELECT)
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Subscription> subscriptionUser;

    @Fetch(FetchMode.SELECT)
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "creator", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Subscription> subscriptionCreator;

    @Fetch(FetchMode.SELECT)
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Rate> rates;

    public User(String email, String password, List<Lot> lots, List<Role> role, List<Subscription> subscriptionUser,
                List<Subscription> subscriptionCreator, String rating, List<Rate> rates, Boolean disable) {
        this.email = email;
        this.password = password;
        this.lots = lots;
        this.role = role;
        this.subscriptionUser = subscriptionUser;
        this.subscriptionCreator = subscriptionCreator;
        this.rating = rating;
        this.rates = rates;
        this.disable = disable;
    }

    public User(String email, String password, String rating) {
        this.email = email;
        this.password = password;
        this.rating = rating;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public List<Subscription> getSubscriptionUser() {
        return subscriptionUser;
    }

    public void setSubscriptionUser(List<Subscription> subscriptionUser) {
        this.subscriptionUser = subscriptionUser;
    }

    public List<Subscription> getSubscriptionCreator() {
        return subscriptionCreator;
    }

    public void setSubscriptionCreator(List<Subscription> subscriptionCreator) {
        this.subscriptionCreator = subscriptionCreator;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (passwordConfirm != null ? !passwordConfirm.equals(user.passwordConfirm) : user.passwordConfirm != null)
            return false;
        if (rating != null ? !rating.equals(user.rating) : user.rating != null) return false;
        if (disable != null ? !disable.equals(user.disable) : user.disable != null) return false;
        if (lots != null ? !lots.equals(user.lots) : user.lots != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (subscriptionUser != null ? !subscriptionUser.equals(user.subscriptionUser) : user.subscriptionUser != null)
            return false;
        if (subscriptionCreator != null ? !subscriptionCreator.equals(user.subscriptionCreator) : user.subscriptionCreator != null)
            return false;
        return rates != null ? rates.equals(user.rates) : user.rates == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passwordConfirm != null ? passwordConfirm.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (disable != null ? disable.hashCode() : 0);
        result = 31 * result + (lots != null ? lots.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (subscriptionUser != null ? subscriptionUser.hashCode() : 0);
        result = 31 * result + (subscriptionCreator != null ? subscriptionCreator.hashCode() : 0);
        result = 31 * result + (rates != null ? rates.hashCode() : 0);
        return result;
    }
}

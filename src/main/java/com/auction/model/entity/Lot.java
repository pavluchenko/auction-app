package com.auction.model.entity;

import org.apache.commons.collections4.CollectionUtils;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Helga on 26.10.17.
 */
@Entity
@Transactional
@Table(name = "lot")
public class Lot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "my_seq_gen")
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "bayout_price")
    private Double bayoutPrice;

    @Column(name = "photo")
    private String photo;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "disable")
    private Boolean disable;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = true, updatable = false)
    private Category category;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = true, updatable = false)
    private User user;

    @Fetch(FetchMode.SELECT)
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "lot", cascade = {CascadeType.MERGE})
    private List<Feature> features;

    @Fetch(FetchMode.SELECT)
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Rate> rates;

    public Lot(String name, String description, Double bayoutPrice, String photo, Double minPrice,
               Category category, User user, List<Feature> features, List<Rate> rates) {
        this.name = name;
        this.description = description;
        this.bayoutPrice = bayoutPrice;
        this.photo = photo;
        this.minPrice = minPrice;
        this.category = category;
        this.user = user;
        this.features = features;
        this.rates = rates;
        this.disable = false;
    }

    public Lot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        if (CollectionUtils.isNotEmpty(this.features)) {
            this.features = features;
        }
    }

    public Double getBayoutPrice() {
        return bayoutPrice;
    }

    public void setBayoutPrice(Double bayoutPrice) {
        this.bayoutPrice = bayoutPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lot lot = (Lot) o;

        if (id != null ? !id.equals(lot.id) : lot.id != null) return false;
        if (name != null ? !name.equals(lot.name) : lot.name != null) return false;
        if (description != null ? !description.equals(lot.description) : lot.description != null) return false;
        if (bayoutPrice != null ? !bayoutPrice.equals(lot.bayoutPrice) : lot.bayoutPrice != null) return false;
        if (photo != null ? !photo.equals(lot.photo) : lot.photo != null) return false;
        if (minPrice != null ? !minPrice.equals(lot.minPrice) : lot.minPrice != null) return false;
        if (category != null ? !category.equals(lot.category) : lot.category != null) return false;
        if (user != null ? !user.equals(lot.user) : lot.user != null) return false;
        if (features != null ? !features.equals(lot.features) : lot.features != null) return false;
        return rates != null ? rates.equals(lot.rates) : lot.rates == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (bayoutPrice != null ? bayoutPrice.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (minPrice != null ? minPrice.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (features != null ? features.hashCode() : 0);
        result = 31 * result + (rates != null ? rates.hashCode() : 0);
        return result;
    }
}

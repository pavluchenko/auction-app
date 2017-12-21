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

/**
 * Created by Helga on 26.10.17.
 */

@Entity
@Table(name = "feature")
public class Feature extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "my_seq_gen")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "lot_id", referencedColumnName = "id", insertable = true, nullable = false, updatable = false)
    private Lot lot;

    public Feature(String name, String description, Lot lot) {
        this.name = name;
        this.description = description;
        this.lot = lot;
    }

    public Feature() {
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

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;

        if (id != null ? !id.equals(feature.id) : feature.id != null) return false;
        if (name != null ? !name.equals(feature.name) : feature.name != null) return false;
        if (description != null ? !description.equals(feature.description) : feature.description != null) return false;
        return lot != null ? lot.equals(feature.lot) : feature.lot == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lot != null ? lot.hashCode() : 0);
        return result;
    }
}

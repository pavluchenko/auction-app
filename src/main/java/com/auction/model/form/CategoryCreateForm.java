package com.auction.model.form;

import java.util.List;

/**
 * Created by Helga on 03.11.17.
 */
public class CategoryCreateForm extends BaseForm {
    private Long id;
    private String name;
    private List<Long> lots;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getLots() {
        return lots;
    }

    public void setLots(List<Long> lots) {
        this.lots = lots;
    }
}

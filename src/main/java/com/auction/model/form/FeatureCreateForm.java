package com.auction.model.form;


/**
 * Created by Helga on 24.11.2017.
 */
public class FeatureCreateForm extends BaseForm {
    private Long id;
    private String name;
    private String description;
    private Long lotId;

    public FeatureCreateForm() {
    }

    public FeatureCreateForm(Long id, String name, String description, Long lotId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lotId = lotId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }
}

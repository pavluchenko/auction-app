package com.auction.model.form;

/**
 * Created by Helga on 01.11.17.
 */
public class LotRestForm extends BaseForm {

    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Long userId;

    public LotRestForm() {
    }

    public LotRestForm(Long id, String name, String description, Long categoryId, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.userId = userId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

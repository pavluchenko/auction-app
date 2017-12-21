package com.auction.model.form;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Helga on 01.11.17.
 */
public class LotCreateForm extends BaseForm {
    private Long id;
    private String name;
    private String description;
    private String categoryName;
    private Long categoryId;
    private List<Long> features;
    private Long userId;
    private Double bayoutPrice;
    private String photo;
    private Double minPrice;
    private Boolean disable;
    private MultipartFile file;
    private String path;


    public LotCreateForm() {
    }

    public LotCreateForm(Long id) {
        this.id = id;
    }


    public LotCreateForm(String name, String description, Long categoryId, List<Long> features, Long userId) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.features = features;
        this.userId = userId;
    }

    public LotCreateForm(Long id, String name, String description, String categoryName, Long categoryId,
                         List<Long> features, Long user, Double bayoutPrice, String photo, Double minPrice, Boolean disable,
                         MultipartFile file, String path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.features = features;
        this.userId = user;
        this.bayoutPrice = bayoutPrice;
        this.photo = photo;
        this.minPrice = minPrice;
        this.disable = disable;
        this.file = file;
        this.path = path;
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

    public List<Long> getFeatures() {
        return features;
    }

    public void setFeatures(List<Long> features) {
        this.features = features;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

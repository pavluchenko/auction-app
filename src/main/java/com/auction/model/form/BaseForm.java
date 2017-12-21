package com.auction.model.form;

import java.io.Serializable;

/**
 * Created by Helga on 03.11.17.
 */
public abstract class BaseForm implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

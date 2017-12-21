package com.auction.service;

import com.auction.model.entity.BaseEntity;
import com.auction.model.form.BaseForm;

import java.util.List;

/**
 * Created by Helga on 03.11.17.
 *
 * @param <T>
 * @param <TForm>
 */
public interface AuctionGenericService<T extends BaseEntity, TForm extends BaseForm> {
    /**
     * Generic method for get all entities
     *
     * @return
     */
    List<T> getAll();

    /**
     * Generic method for get entity by id
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * Generic method for create entity by form
     *
     * @param tForm
     * @return
     */
    T create(TForm tForm);

    /**
     * Generic method for delete entity by form
     *
     * @param tForm
     */
    void delete(TForm tForm);
}

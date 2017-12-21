package com.auction.service.impl;

import com.auction.model.entity.BaseEntity;
import com.auction.model.form.BaseForm;
import com.auction.repository.AuctionGenericRepository;
import com.auction.service.AuctionGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * Created by Helga on 03.11.17.
 *
 * @param <T>
 * @param <TForm>
 * @param <TConvert>
 */
public abstract class AuctionGenericServiceImpl<T extends BaseEntity, TForm extends BaseForm,
        TConvert extends Converter> implements
        AuctionGenericService<T, TForm> {

    @Autowired
    private AuctionGenericRepository<T> auctionGenericRepository;

    @Autowired
    private TConvert tConvert;

    /**
     * Generic method for get all entities
     *
     * @return
     */
    public List<T> getAll() {
        return auctionGenericRepository.findAll();
    }


    /**
     * Generic method for create entity by form
     *
     * @param tForm
     * @return
     */
    public T create(TForm tForm) {
        T t = (T) tConvert.convert(tForm);
        return auctionGenericRepository.saveAndFlush(t);
    }

    /**
     * Generic method for delete entity by form
     *
     * @param tForm
     */
    public void delete(TForm tForm) {
        T t = (T) tConvert.convert(tForm);
        auctionGenericRepository.delete(t);
    }

    /**
     * Generic method for get entity by id
     *
     * @param id
     * @return
     */
    public T getById(Long id) {
        if (id != null) {
            return auctionGenericRepository.findOne(id);
        }
        return null;
    }
}

package com.auction.service;

import com.auction.model.entity.Feature;
import com.auction.model.entity.Lot;
import com.auction.model.form.FeatureCreateForm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 24.11.2017.
 */
@Service
public interface FeatureService extends AuctionGenericService<Feature, FeatureCreateForm> {

    /**
     * This method for get feature by id. If feature id is null, this method return null.
     *
     * @param id
     * @return
     */
    Feature getFeatureById(Long id);

    /**
     * This method for get list features by name and lot
     *
     * @param name
     * @param lot
     * @return
     */
    List<Feature> getFeatureByName(String name, Lot lot);
}

package com.auction.service.impl;

import com.auction.converter.entity.FeatureConvert;
import com.auction.model.entity.Feature;
import com.auction.model.entity.Lot;
import com.auction.model.form.FeatureCreateForm;
import com.auction.repository.FeatureRepository;
import com.auction.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 24.11.2017.
 */
@Service
public class FeatureServiceImpl extends AuctionGenericServiceImpl<Feature, FeatureCreateForm, FeatureConvert>
        implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    /**
     * This method for get feature by id. If feature id is null, this method return null.
     *
     * @param id
     * @return
     */
    @Override
    public Feature getFeatureById(Long id) {
        if (id != null) {
            return featureRepository.findOne(id);
        } else {
            return null;
        }
    }

    /**
     * This method for get list features by name and lot
     *
     * @param name
     * @param lot
     * @return
     */
    @Override
    public List<Feature> getFeatureByName(String name, Lot lot) {
        return featureRepository.getFeatureByName(name, lot);
    }
}

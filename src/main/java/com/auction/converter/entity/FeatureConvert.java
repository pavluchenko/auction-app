package com.auction.converter.entity;

import com.auction.model.entity.Feature;
import com.auction.model.form.FeatureCreateForm;
import com.auction.service.FeatureService;
import com.auction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 24.11.2017.
 */
@Component
public class FeatureConvert implements Converter<FeatureCreateForm, Feature> {

    @Autowired
    private FeatureService featureService;

    @Autowired
    private LotService lotService;

    @Override
    public Feature convert(FeatureCreateForm featureCreateForm) {
        Feature feature = featureService.getFeatureById(featureCreateForm.getId());
        if (feature == null) {
            feature = new Feature();
        }
        feature.setLot(lotService.getLotById(featureCreateForm.getLotId()));
        feature.setDescription(featureCreateForm.getDescription());
        feature.setName(featureCreateForm.getName());
        return feature;
    }
}

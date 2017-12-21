package com.auction.converter.entity;

import com.auction.model.entity.Feature;
import com.auction.model.entity.Lot;
import com.auction.model.form.LotCreateForm;
import com.auction.service.CategoryService;
import com.auction.service.FeatureService;
import com.auction.service.LotService;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helga on 02.11.17.
 */
@Component
public class LotConverter implements Converter<LotCreateForm, Lot> {
    @Autowired
    private LotService lotService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private UserService userService;

    public Lot convert(LotCreateForm lotCreateForm) {
        Lot lot = lotService.getLotById(lotCreateForm.getId());
        if (lot == null) {
            lot = new Lot();
        }
        lot.setName(lotCreateForm.getName());
        lot.setBayoutPrice(lotCreateForm.getBayoutPrice());
        lot.setMinPrice(lotCreateForm.getMinPrice());
        lot.setPhoto(lotCreateForm.getPhoto());
        lot.setCategory(categoryService.getCategoryByName(lotCreateForm.getCategoryName()));
        lot.setDescription(lotCreateForm.getDescription());
        if (lotCreateForm.getFeatures() != null) {
            List<Feature> features = new ArrayList<>();
            for (Long id : lotCreateForm.getFeatures()) {
                features.add(featureService.getFeatureById(id));
            }
            lot.setFeatures(features);
        }
        lot.setUser(userService.getUserById(lotCreateForm.getUserId()));
        return lot;
    }
}

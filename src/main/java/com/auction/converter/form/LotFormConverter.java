package com.auction.converter.form;

import com.auction.model.entity.Feature;
import com.auction.model.entity.Lot;
import com.auction.model.form.LotCreateForm;
import com.auction.service.LotService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helga on 16.11.2017.
 */
@Component
public class LotFormConverter implements Converter<Lot, LotCreateForm> {

    @Autowired
    private LotService lotService;

    public LotCreateForm convert(Lot lot) {
        if (lot == null) {
            return null;
        }
        LotCreateForm lotCreateForm = new LotCreateForm();
        lotCreateForm.setId(lot.getId());
        lotCreateForm.setName(lot.getName());
        lotCreateForm.setDescription(lot.getDescription());
        lotCreateForm.setBayoutPrice(lot.getBayoutPrice());
        lotCreateForm.setMinPrice(lot.getMinPrice());
        lotCreateForm.setPhoto(lot.getPhoto());
        lotCreateForm.setDisable(lot.getDisable());
        lotCreateForm.setPhoto(lot.getPhoto());
        if (lot.getCategory() != null) {
            lotCreateForm.setCategoryId(lot.getCategory().getId());
        }
        if (CollectionUtils.isNotEmpty(lot.getFeatures())) {
            List<Long> featureIds = new ArrayList<>();
            for (Feature feature : lot.getFeatures()) {
                featureIds.add(feature.getId());
            }
            lotCreateForm.setFeatures(featureIds);
        }
        if (lot.getUser() != null) {
            lotCreateForm.setUserId(lot.getUser().getId());
        }
        return lotCreateForm;
    }
}

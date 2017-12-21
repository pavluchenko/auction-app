package com.auction.converter.form;

import com.auction.model.entity.Rate;
import com.auction.model.form.RateCreateForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 12.12.2017.
 */
@Component
public class RateFormConverter implements Converter<Rate, RateCreateForm> {
    @Override
    public RateCreateForm convert(Rate rate) {
        RateCreateForm rateCreateForm = new RateCreateForm();
        rateCreateForm.setPrice(rate.getPrice());
        rateCreateForm.setUserId(rate.getUser().getId());
        rateCreateForm.setLotId(rate.getLot().getId());
        rateCreateForm.setDate(rate.getDate());
        rateCreateForm.setId(rate.getId());
        return rateCreateForm;
    }
}

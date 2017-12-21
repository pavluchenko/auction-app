package com.auction.converter.entity;

import com.auction.model.entity.Rate;
import com.auction.model.form.RateCreateForm;
import com.auction.service.LotService;
import com.auction.service.RateService;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Helga on 13.11.2017.
 */
@Component
public class RateConvert implements Converter<RateCreateForm, Rate> {

    @Autowired
    private RateService rateService;

    @Autowired
    private UserService userService;

    @Autowired
    private LotService lotService;

    public Rate convert(RateCreateForm rateCreateForm) {
        Rate rate = rateService.getRateById(rateCreateForm.getId());
        if (rate == null) {
            rate = new Rate();
        }
        rate.setId(rateCreateForm.getId());
        rate.setPrice(rateCreateForm.getPrice());
        rate.setUser(userService.getUserById(rateCreateForm.getUserId()));
        rate.setLot(lotService.getLotById(rateCreateForm.getLotId()));
        rate.setDate(new Date());
        return rate;
    }
}
